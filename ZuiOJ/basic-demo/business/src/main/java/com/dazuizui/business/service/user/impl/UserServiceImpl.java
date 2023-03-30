package com.dazuizui.business.service.user.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.bo.DeleteUserByIdBo;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import com.dazuizui.basicapi.entry.bo.TombstoneUserByIdBo;
import com.dazuizui.basicapi.entry.vo.PagingToGetUserDateVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.AttributeMapper;
import com.dazuizui.business.mapper.UserArticleAttributeMapper;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * 用户登入接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserArticleAttributeMapper userArticleAttributeMapper;
    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 查询网站管理人员
     * todo 缓存层优化
     * @return
     */
    public String queryListOfAdmin(){
        List<String> list = userMapper.queryListOfAdmin();
        System.out.println(list);
        return  JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,list,StatusCode.OK));
    }
    /**
     * 通过id物理删除用户
     * @param deleteUserByIdBo
     * @return
     */
    @Transactional
    @Override
    public String deleteUserById(DeleteUserByIdBo deleteUserByIdBo){
        /**
         * 获取这个用户信息
         */
        User user = (User) redisUtil.getStringInRedis(RedisKey.ZuiBlogUserId + deleteUserByIdBo.getIdOfUser());
        if (user == null){
            //去数据库查询
            user = userMapper.queryUserById(deleteUserByIdBo.getIdOfUser());
        }

        try {
            /**
             * 删除用户在mysql
             */
            userMapper.deleteUserById(deleteUserByIdBo.getIdOfUser());

            /**
             * 删除用户在redis中
             */
            List<String> keys = new ArrayList<>();
            keys.add(RedisKey.ZuiBlogUserId+user.getId());
            keys.add(RedisKey.ZuiBlogUserUsername+user.getUsername());
            redisUtil.batchDeletion(keys);

            /**
             * redis用户数据减1
             */
            redisUtil.increment(RedisKey.ZuiBlogUserCount,RedisKey.OutTime,-1);
        } catch (Exception e) {
           // e.printStackTrace();
            return  JSONArray.toJSONString(new ResponseVo<>("物理删除失败",null,StatusCode.Error));
        }

        return  JSONArray.toJSONString(new ResponseVo<>("物理删除成功",null,StatusCode.OK));
    }

    /**
     * 管理员分页获取用户数据
     * @param pagingToGetUserDateBo
     * @return
     */
    @Override
    public String pagingToGetUserDate(PagingToGetUserDateBo pagingToGetUserDateBo) {
        //获取用户个数
        Long coungOfUser =  redisUtil.getLongOfStringInRedis(RedisKey.ZuiBlogUserCount);

        //如果redis没有数据就去mysql查询
        if (coungOfUser == null || coungOfUser == 0){
            coungOfUser = userMapper.queryCountOfUser();
            //写入redis
            redisUtil.setLongOfStringInRedis(RedisKey.ZuiBlogUserCount,RedisKey.OutTime, coungOfUser);

            System.out.println(redisUtil.getLongOfStringInRedis(RedisKey.ZuiBlogUserCount));
        }

        //获取用户数据
        List<User> users = userMapper.pagingToGetUserDate(pagingToGetUserDateBo);

        PagingToGetUserDateVo pagingToGetUserDateVo = new PagingToGetUserDateVo();
        pagingToGetUserDateVo.setUsers(users);
        pagingToGetUserDateVo.setCoungOfUser(coungOfUser);

        return  JSONArray.toJSONString(new ResponseVo<>("查询成功",pagingToGetUserDateVo,"0x200"));
    }

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    @Override
    public User queryUserById(@Param("id") Long id){
        User user = (User) redisUtil.getStringInRedis(RedisKey.ZuiBlogUserId+id);

        if (user == null){
            user = userMapper.queryUserById(id);
            if (user != null){
                redisUtil.setStringInRedis("zuiblog:user:"+id,60*60*24*15,user);
            }else{
                return null;
            }
        }

        return user;
    }

    /**
     * 用户登入
     * @param user
     * @return
     */
    @Override
    public String userLogin(User user) {
        //查看账号密码是否正确
        User userInDB = null; //(User) redisUtil.getStringInRedis("DaZuiOj:user:info:"+user.getUsername());
        if (userInDB == null){
            userInDB = userMapper.userLogin(user);
            if (userInDB == null){
                return JSONArray.toJSONString(new ResponseVo<>("账号不存在",null,"0x0002"));
            }
        }

        //查看是否密码正确
        if (!userInDB.getPassword().equals(user.getPassword())){
            return JSONArray.toJSONString(new ResponseVo<>("密码错误",null,"0x0003"));
        }
        //生成JWT
        String jwt = JwtUtil.createJWT(userInDB);

        //将token存入redis用来做过期验证和修改密码token的可使用性
        System.err.println(userInDB);
        redisUtil.setStringInRedis(RedisKey.ZuiBlogUserToken+userInDB.getId(),RedisKey.UserTokenOutTime,jwt);

        //封装返回
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("userinfo",userInDB);
        return JSONArray.toJSONString(new ResponseVo<>("登入成功",map,"0x0001"));
    }

    /**
     * 解析token
     *      如果token已经过期在redis中，那么说明身份验证过期
     *      如果在redis存放的token和我们目前接受的token不一致，那么就说明要不修改了密码要不在其他客户端有第二个人登入，
     *      这样就保证了同一时刻只有一个人在登入此账号
     * @param token
     * @return
     */
    @Override
    public String analysis(String token) {
        Map<String, Object> map = null;
        try {
            map = JwtUtil.analysis(token);

            Long id = Long.valueOf(map.get("id")+"");

            //当前登入验证过期
            long expire = redisUtil.expire(RedisKey.ZuiBlogUserToken+id);
            if (expire <= 0){
                return JSONArray.toJSONString(new ResponseVo<>("身份验证已过期",null,StatusCode.authenticationExpired));
            }
            //当token不一致
            String tokenOfString = (String) redisUtil.getStringInRedis(RedisKey.ZuiBlogUserToken + id);

            if (!tokenOfString.equals(token)){
                return JSONArray.toJSONString(new ResponseVo<>("身份验证已过期",null,StatusCode.authenticationExpired));
            }

        } catch (Exception e) {
            return JSONArray.toJSONString(new ResponseVo<>("身份验证已过期",null,StatusCode.authenticationExpired));
        }

        User user = new User();

        Long id = Long.valueOf((String)map.get("id"));
        user = this.queryUserById(id);
        //System.err.println(user);

        return JSONArray.toJSONString(new ResponseVo<>("null",user,StatusCode.OK));
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public String register(User user) {
        if (user == null){
            return JSONArray.toJSONString(new ResponseVo<>("注册失败",null,StatusCode.Error));
        }
        //校验学号还有用户名是否唯一
        User userInDB = userMapper.checkUsernameAndStudentId(user);
        if (userInDB != null){
            return JSONArray.toJSONString(new ResponseVo<>("用户名必须唯一,请更换用户名。",null,StatusCode.Error));
        }

        TransactionStatus transactionStatus =  transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //写入mysql
            user.setCreateTime(new Date());
            Long aLong = userMapper.register(user);
            if (aLong <= 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>("注册失败",null,StatusCode.Error));
            }

            //创建用户博文数量
            userArticleAttributeMapper.AddUserArticleAttribute(user.getId());
            //写入用户表的用户个数
            attributeMapper.IncreaseTheNumberOfTable(AttributeKey.user,1L);
        } catch (Exception e) {
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>("注册失败",null,StatusCode.Error));
            //e.printStackTrace();
        }

        transactionUtils.commit(transactionStatus);

        /**
         * 写入用户数量
         *     如果key过期了，那么就不做处理，如果没过期则
         */
        Long longOfStringInRedis = redisUtil.getLongOfStringInRedis(RedisKey.ZuiBlogUserCount);
        if (longOfStringInRedis != null){
            redisUtil.increment(RedisKey.ZuiBlogUserCount,RedisKey.OutTime,1);
        }

        return JSONArray.toJSONString(new ResponseVo<>("注册成功",null,StatusCode.OK));
    }

    /**
     * 批量删除用户
     * @return
     */
    @Override
    @Transactional
    public String deleteUsersInBulk(DeleteUsersInBulkBo deleteUsersInBulkBo){
        long[] delArr = deleteUsersInBulkBo.getDelArr();

        /**
         * 前去redis删除
         */
        //获取他们的usernae索引在username
        List<String> batDeletionInRedis = new ArrayList<>();
        for (long i : delArr) {
            batDeletionInRedis.add(RedisKey.ZuiBlogUserId+""+i);
        }
        redisUtil.batchDeletion(batDeletionInRedis);

        batDeletionInRedis.clear();

        //获取要删除的用户的UserName去数据库
        List<String> usernameOfUserInMysql = userMapper.getDateInBatches(deleteUsersInBulkBo.getDelArr());
        for (String username : usernameOfUserInMysql) {
            batDeletionInRedis.add(RedisKey.ZuiBlogUserUsername+username);
        }

        //删除索引是username的用户在Redis
        redisUtil.batchDeletion(batDeletionInRedis);

        //去数据库逻辑删除此数据
        userMapper.tombstoneUsersInBatches(deleteUsersInBulkBo.getDelArr());

        return JSONArray.toJSONString(new ResponseVo<>("逻辑删除成功",null,StatusCode.OK));
    }

    /**
     * 通过id逻辑删除用户
     * @param tombstoneUserByIdBo
     * @return
     */
    @Override
    public String tombstoneUserById(TombstoneUserByIdBo tombstoneUserByIdBo) {
        DeleteUsersInBulkBo deleteUsersInBulkBo = new DeleteUsersInBulkBo();
        deleteUsersInBulkBo.setDelArr(new long[]{tombstoneUserByIdBo.getIdOfUser()});

        //逻辑删除
        this.deleteUsersInBulk(deleteUsersInBulkBo);

        return JSONArray.toJSONString(new ResponseVo<>("逻辑删除成功",null, StatusCode.OK));
    }


    /**
     * 通过token获取登入者的详细信息
     * @param token
     * @return String.class
     */
    @Override
    public String getUserInfoByToken(@RequestParam("token")String token){
        //解析token获取id
        Map<String, Object> analysis = null;
        try {
            analysis = JwtUtil.analysis(token);
        } catch (Exception e) {
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.authenticationExpired,null, StatusCode.authenticationExpired));
        }
        Long id = Long.valueOf(analysis.get("id")+"");

        //查询redis是否有该用户数据
        User userInDB = (User) redisUtil.getStringInRedis(RedisKey.ZuiBlogUserId + id);
        //如果redis没获取到就去数据库
        if (userInDB == null){
            userInDB = userMapper.queryUserById(id);
            //如果数据库获取到了就存入redis
            if (userInDB != null){
                redisUtil.setStringInRedis(RedisKey.ZuiBlogUserId,RedisKey.OutTime,userInDB);
                redisUtil.setStringInRedis(RedisKey.ZuiBlogUserId,RedisKey.OutTime,userInDB);
            }

            //没有获取到数据 in DB
            else{
                return JSONArray.toJSONString(new ResponseVo<>("没有获取到用户信息",null, StatusCode.Error));
            }
        }

        return JSONArray.toJSONString(new ResponseVo<>("获取成功",userInDB, StatusCode.OK));
    }

    /**
     * 修改id修改用户接口
     * @param updateUserInfoByIdBo
     * @return
     */
    @Override
    @Transactional
    public String updateUserInfoById(UpdateUserInfoByIdBo updateUserInfoByIdBo) {
        User user = updateUserInfoByIdBo.getUser();
       // System.err.println(0/10);
        //进行修改数据库
        Long aLong = userMapper.updateUserInfoById(user);

        //修改失败
        if(aLong <= 0){
            return JSONArray.toJSONString(new ResponseVo<>("修改失败",null, StatusCode.Error));
        }
        //设置数据库
        user = userMapper.queryUserById(user.getId());
        redisUtil.setStringInRedis(RedisKey.ZuiBlogUserId+user.getId(),RedisKey.OutTime,user);
        redisUtil.setStringInRedis(RedisKey.ZuiBlogUserUsername+user.getUsername(), RedisKey.OutTime,user);

        return JSONArray.toJSONString(new ResponseVo<>("修改成功",null, StatusCode.OK));
    }


}
