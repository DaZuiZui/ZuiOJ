package com.dazuizui.business.service.proctor.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
import com.dazuizui.business.domain.vo.ProctorAnalysisVo;
import com.dazuizui.business.domain.vo.ProctorGetFutureEventsInfoVo;
import com.dazuizui.business.mapper.ProctorAttributeMapper;
import com.dazuizui.business.mapper.ProctorMapper;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  面试官业务实现层
 */
@Service
public class ProctorServiceImpl implements ProctorService {
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private ProctorMapper proctorMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private ProctorAttributeMapper proctorAttributeMapper;
    /**
     * 添加一个面试官
     * @param addProctorBo
     * @return
     */
    @Override
    public String addProctor(AddProctorBo addProctorBo){
        //查询该用户的主键
        User user = userService.queryUserByUsername(addProctorBo.getUsername());

        //用户名不存在
        if (user == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.ThisUsernameDoesNotExist,null, StatusCode.ThisUsernameDoesNotExist));
        }

        //封装
        Proctor proctor  =new Proctor();
        proctor.setContestId(addProctorBo.getContestId());
        proctor.setUserId(user.getId());
        proctor.setCreateBy(Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""));
        proctor.setCreateTime(new Date());

        //查看该用户是否已经是当前竞赛的监考人员
        Proctor byContestIdAndUserId = this.findByContestIdAndUserId(proctor.getContestId(), proctor.getUserId());
        if (byContestIdAndUserId != null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsProctor,null, StatusCode.IsProctor));
        }

        //添加到mysql
        Long aLong = proctorMapper.addProctor(proctor);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //增加监考数量
        aLong = proctorAttributeMapper.increaseTheNumberOfProctors(addProctorBo.getContestId(),1l);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        //添加Redis
        redisUtil.setStringInRedis(RedisKey.ZuiBlogInvigilatorUserId+":"+proctor.getContestId()+":"+proctor.getUserId(),RedisKey.OutTime,proctor);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 通过userId和contestId查找监考人员
     * @param contestId
     * @param userId
     * @return
     */
    @Override
    public Proctor findByContestIdAndUserId(Long contestId, Long userId) {
        Proctor proctor = (Proctor) redisUtil.getStringInRedis(RedisKey.ZuiBlogInvigilatorUserId+":"+contestId+":"+userId);
        if (proctor == null){
            proctor = proctorMapper.findByContestIdAndUserId(contestId,userId);

            if(proctor == null){
                return null;
            }
            //添加Redis
            redisUtil.setStringInRedis(RedisKey.ZuiBlogInvigilatorUserId+proctor.getUserId(),RedisKey.OutTime,proctor);
        }
        return proctor;
    }

    /**
     * 分页获取现在进行时和未来进行时的数据
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    @Override
    public String proctorGetFutureEventsInfo(ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo) {
        List<Contest> contests = proctorMapper.proctorGetFutureEvents(proctorGetFutureEventsInfoBo);
        Long count = proctorMapper.proctorGetFutureEventsNumber(Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id") + ""));
        ProctorGetFutureEventsInfoVo proctorGetFutureEventsInfoVo  = new ProctorGetFutureEventsInfoVo();
        proctorGetFutureEventsInfoVo.setContests(contests);
        proctorGetFutureEventsInfoVo.setCount(count);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,proctorGetFutureEventsInfoVo, StatusCode.OK));
    }

    /**
     * 通过userid 查询一个监考人员
     */
    @Override
    public Proctor findByIdLimit1(@Param("userId")Long userId) {
        return proctorMapper.findByIdLimit1(userId);
    }

    /**
     * 解析监考身份
     * @param token
     * @return
     */
    @Override
    public String proctorAnalysis(String token) {
        User userInfoByTokenForUserEntry = userService.getUserInfoByTokenForUserEntry(token);
        //该账号不存在
        if (userInfoByTokenForUserEntry == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.UserIsNull,null, StatusCode.ThisUsernameDoesNotExist));
        }
        //判断是否为监考信息

        Proctor proctor = this.findByIdLimit1(userInfoByTokenForUserEntry.getId());
        //不为监考人员
        if (proctor == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.NotProctor,null, StatusCode.NotProctor));
        }

        //封装返回信息
        ProctorAnalysisVo proctorAnalysisVo = new ProctorAnalysisVo();
        proctorAnalysisVo.setProctor(true);
        proctorAnalysisVo.setUser(userInfoByTokenForUserEntry);

        System.out.println(proctorAnalysisVo);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,proctorAnalysisVo, StatusCode.OK));
    }
}
