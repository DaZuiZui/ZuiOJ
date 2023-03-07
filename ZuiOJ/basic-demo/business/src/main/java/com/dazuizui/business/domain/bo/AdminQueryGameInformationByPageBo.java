package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员分页获取比赛信息Bo
 */
public class AdminQueryGameInformationByPageBo implements Serializable {
    private String token;
    private Long page;
    private Long size;

    @Override
    public String toString() {
        return "AdminQueryGameInformationByPageBo{" +
                "token='" + token + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public AdminQueryGameInformationByPageBo() {
    }

    public AdminQueryGameInformationByPageBo(String token, Long page, Long size) {
        this.token = token;
        this.page = page;
        this.size = size;
    }
}
