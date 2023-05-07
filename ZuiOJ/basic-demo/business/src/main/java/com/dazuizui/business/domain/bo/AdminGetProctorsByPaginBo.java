package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员分页获取监考人员Bo
 */
public class AdminGetProctorsByPaginBo implements Serializable {
    private String token;   //token
    private Long size;      //一张多少页
    private Long start;     //开始位置
    private Long contestId; //竞赛id
    private Integer status; //状态
    private Integer delFLag;//逻辑删除

    @Override
    public String toString() {
        return "AdminGetProctorsByPaginBo{" +
                "token='" + token + '\'' +
                ", size=" + size +
                ", start=" + start +
                ", contestId=" + contestId +
                ", status=" + status +
                ", delFLag=" + delFLag +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFLag() {
        return delFLag;
    }

    public void setDelFLag(Integer delFLag) {
        this.delFLag = delFLag;
    }

    public AdminGetProctorsByPaginBo() {
    }

    public AdminGetProctorsByPaginBo(String token, Long size, Long start, Long contestId, Integer status, Integer delFLag) {
        this.token = token;
        this.size = size;
        this.start = start;
        this.contestId = contestId;
        this.status = status;
        this.delFLag = delFLag;
    }
}
