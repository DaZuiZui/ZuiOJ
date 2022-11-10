package com.dazuizui.basicapi.entry.bo;

import com.dazuizui.basicapi.entry.ProblemLimit;

import java.io.Serializable;
import java.util.List;

/**
 * 用户提交的代码控制器
 * https://blog.csdn.net/mryang125/article/details/80955558
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
    private ProblemLimit problemLimit;
    private String input;
    //题目类型 1为普通题目，2为比赛题目
    private Integer questionType;
    //赛事id
    private Long contestId;

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
                ", problemLimit=" + problemLimit +
                ", input='" + input + '\'' +
                ", questionType=" + questionType +
                ", contestId=" + contestId +
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

    public ProblemLimit getProblemLimit() {
        return problemLimit;
    }

    public void setProblemLimit(ProblemLimit problemLimit) {
        this.problemLimit = problemLimit;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public ProgramBo() {
    }

    public ProgramBo(long topicId, long userID, String code, Long languageId, List<String> parseCodeArgs, List<String> runCommandArgs, List<String> env, List<String> copyOutCached, ProblemLimit problemLimit, String input, Integer questionType, Long contestId) {

        this.topicId = topicId;
        this.userID = userID;
        this.code = code;
        this.languageId = languageId;
        ParseCodeArgs = parseCodeArgs;
        RunCommandArgs = runCommandArgs;
        this.env = env;
        this.copyOutCached = copyOutCached;
        this.problemLimit = problemLimit;
        this.input = input;
        this.questionType = questionType;
        this.contestId = contestId;
    }
}
