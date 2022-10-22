package com.dazuizui.basicapi.entry;

public class Program {
    private long topicId;       //题目ID
    private long userID;        //答题者的ID
    private String code;        //用户代码
    private int languageId;     //语言ID

    @Override
    public String toString() {
        return "Program{" +
                "topicId=" + topicId +
                ", userID=" + userID +
                ", code='" + code + '\'' +
                ", languageId=" + languageId +
                '}';
    }

    public long getTopicId() {
        return topicId;
    }

    public Program setTopicId(long topicId) {
        this.topicId = topicId;
        return this;
    }

    public long getUserID() {
        return userID;
    }

    public Program setUserID(long userID) {
        this.userID = userID;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Program setCode(String code) {
        this.code = code;
        return this;
    }

    public int getLanguageId() {
        return languageId;
    }

    public Program setLanguageId(int languageId) {
        this.languageId = languageId;
        return this;
    }

    public Program(long topicId, long userID, String code, int languageId) {
        this.topicId = topicId;
        this.userID = userID;
        this.code = code;
        this.languageId = languageId;
    }

    public Program() {
    }
}
