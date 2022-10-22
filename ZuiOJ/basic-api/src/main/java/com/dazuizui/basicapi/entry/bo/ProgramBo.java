package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户提交的代码控制器
 */
public class ProgramBo implements Serializable {
    private long topicId;       //题目ID
    private long userID;        //答题者的ID
    private String code;        //用户代码
    private int languageId;     //语言ID

    private List<String> args;  //args命令
    private List<String> env;   //env

    @Override
    public String toString() {
        return "ProgramBo{" +
                "topicId=" + topicId +
                ", userID=" + userID +
                ", code='" + code + '\'' +
                ", languageId=" + languageId +
                '}';
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public ProgramBo() {
    }

    public ProgramBo(long topicId, long userID, String code, int languageId) {
        this.topicId = topicId;
        this.userID = userID;
        this.code = code;
        this.languageId = languageId;
    }
}
