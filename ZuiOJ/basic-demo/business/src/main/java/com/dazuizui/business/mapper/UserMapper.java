package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.UpdateUserInfoByIdBo;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询网站管理人员
     * @return
     */
    public List<String> queryListOfAdmin();

    /**
     * 用户登入
     * @param user
     * @return
     */
    public User userLogin(User user);

    /**
     * 校验用户名和学号是否唯一
     * @param user
     * @return
     */
    public User checkUsernameAndStudentId(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    public Long register(User user);

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    public User queryUserById(@Param("id") Long id);

    /**
     * 管理员分页获取用户数据
     * @param pagingToGetUserDateBo
     * @return
     */
    public List<User> pagingToGetUserDate(PagingToGetUserDateBo pagingToGetUserDateBo);


    /**
     * 查询用户数量
     * @return Long
     */
    public Long queryCountOfUser();

    /**
     * 批量逻辑删除用户数据
     * @param delArr
     * @return·
     */
    public Long tombstoneUsersInBatches(@Param("delArr") long[] delArr);

    /**
     * 批量获取数据
     * @param delArr
     * @return
     */
    public List<String> getDateInBatches(@Param("delArr") long[] delArr);

    /**
     * 通过id物理删除用户
     * @param id
     * @return
     */
    public Long deleteUserById(@Param("id") Long id);

    /**
     * 更改用户信息
     * @param user
     * @return
     */
    public Long updateUserInfoById(User user);
}
