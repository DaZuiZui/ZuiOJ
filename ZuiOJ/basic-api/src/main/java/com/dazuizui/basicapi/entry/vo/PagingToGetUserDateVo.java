package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.User;

import java.io.Serializable;
import java.util.List;

/**
 * 用户分页查询vo
 */
public class PagingToGetUserDateVo implements Serializable {
    private Long coungOfUser ;
    private List<User> users;

    @Override
    public String toString() {
        return "PagingToGetUserDateVo{" +
                "coungOfUser=" + coungOfUser +
                ", users=" + users +
                '}';
    }

    public Long getCoungOfUser() {
        return coungOfUser;
    }

    public void setCoungOfUser(Long coungOfUser) {
        this.coungOfUser = coungOfUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public PagingToGetUserDateVo() {
    }

    public PagingToGetUserDateVo(Long coungOfUser, List<User> users) {
        this.coungOfUser = coungOfUser;
        this.users = users;
    }
}
