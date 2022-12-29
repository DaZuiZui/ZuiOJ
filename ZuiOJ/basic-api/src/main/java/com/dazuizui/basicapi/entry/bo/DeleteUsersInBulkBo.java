package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;
import java.util.Arrays;

/***
 * 批量删除用户BO
 */
public class DeleteUsersInBulkBo implements Serializable {
    private String token;
    private long[] delArr;

    public DeleteUsersInBulkBo() {
    }

    @Override
    public String toString() {
        return "DeleteUsersInBulkBo{" +
                "token='" + token + '\'' +
                ", delArr=" + Arrays.toString(delArr) +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long[] getDelArr() {
        return delArr;
    }

    public void setDelArr(long[] delArr) {
        this.delArr = delArr;
    }

    public DeleteUsersInBulkBo(String token, long[] delArr) {
        this.token = token;
        this.delArr = delArr;
    }
}
