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
    private Long languageId;     //语言ID

    private List<String> ParseCodeArgs;  //args命令
    private List<String> RunCommandArgs;  //args命令
    private List<String> env;   //env
    private List<String> copyOutCached;     //输入输出文件
    private String input;       //输入样例

    @Override
    public String toString() {
        return "ProgramBo{" +
                "topicId=" + topicId +
                ", userID=" + userID +
                ", code='" + code + '\'' +
                ", languageId=" + languageId +
                ", ParseCodeArgs=" + ParseCodeArgs +
                ", RunCommandArgs=" + RunCommandArgs +
                ", env=" + env +
                ", copyOutCached=" + copyOutCached +
                ", input='" + input + '\'' +
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

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public List<String> getParseCodeArgs() {
        return ParseCodeArgs;
    }

    public void setParseCodeArgs(List<String> parseCodeArgs) {
        ParseCodeArgs = parseCodeArgs;
    }

    public List<String> getRunCommandArgs() {
        return RunCommandArgs;
    }

    public void setRunCommandArgs(List<String> runCommandArgs) {
        RunCommandArgs = runCommandArgs;
    }

    public List<String> getEnv() {
        return env;
    }

    public void setEnv(List<String> env) {
        this.env = env;
    }

    public List<String> getCopyOutCached() {
        return copyOutCached;
    }

    public void setCopyOutCached(List<String> copyOutCached) {
        this.copyOutCached = copyOutCached;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ProgramBo() {
    }

    public ProgramBo(long topicId, long userID, String code, Long languageId, List<String> parseCodeArgs, List<String> runCommandArgs, List<String> env, List<String> copyOutCached, String input) {
        this.topicId = topicId;
        this.userID = userID;
        this.code = code;
        this.languageId = languageId;
        ParseCodeArgs = parseCodeArgs;
        RunCommandArgs = runCommandArgs;
        this.env = env;
        this.copyOutCached = copyOutCached;
        this.input = input;
    }
}
