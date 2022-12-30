package com.dazuizui.business.service.user;

import com.dazuizui.basicapi.entry.User;

import com.dazuizui.basicapi.entry.bo.DeleteUserByIdBo;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import com.dazuizui.basicapi.entry.bo.TombstoneUserByIdBo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UserService {

    /**
     * 通过id物理删除用户
     * @param deleteUserByIdBo
     * @return
     */
    public String deleteUserById(DeleteUserByIdBo deleteUserByIdBo);

    /**
     * 管理员分页获取用户数据
     * @param pagingToGetUserDateBo
     * @return
     */
    public String pagingToGetUserDate(PagingToGetUserDateBo pagingToGetUserDateBo);

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    public User queryUserById(@Param("id") Long id);

    /**
     * 用户登入
     * @param user
     * @return
     */
    public String userLogin(@RequestBody User user);


    /**
     * 解析token
     * @param token
     * @return
     */
    public String analysis(@RequestParam("token")String token);


    /**
     * 用户注册
     * @param user
     * @return
     */
    public String register( User user);


    /**
     * 批量删除用户
     * @return
     */
    public String deleteUsersInBulk(DeleteUsersInBulkBo deleteUsersInBulkBo);

    /**
     * 通过id逻辑删除用户
     * @param tombstoneUserByIdBo
     * @return
     */
    public String tombstoneUserById(TombstoneUserByIdBo tombstoneUserByIdBo);

    /**
     * 通过token获取登入者的详细信息
     * @param token
     * @return String.class
     */
    public String getUserInfoByToken(@RequestParam("token")String token);
}
