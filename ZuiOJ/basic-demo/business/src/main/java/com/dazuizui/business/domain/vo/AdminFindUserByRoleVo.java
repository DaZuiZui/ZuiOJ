package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.User;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员查询用户实体返回
 */
public class AdminFindUserByRoleVo implements Serializable {
    private Long numberOfUsersByRole;
    private List<User> users;

    @Override
    public String toString() {
        return "AdminFindUserByRoleVo{" +
                "numberOfUsersByRole=" + numberOfUsersByRole +
                ", users=" + users +
                '}';
    }

    public Long getNumberOfUsersByRole() {
        return numberOfUsersByRole;
    }

    public void setNumberOfUsersByRole(Long numberOfUsersByRole) {
        this.numberOfUsersByRole = numberOfUsersByRole;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public AdminFindUserByRoleVo() {
    }

    public AdminFindUserByRoleVo(Long numberOfUsersByRole, List<User> users) {
        this.numberOfUsersByRole = numberOfUsersByRole;
        this.users = users;
    }
}
