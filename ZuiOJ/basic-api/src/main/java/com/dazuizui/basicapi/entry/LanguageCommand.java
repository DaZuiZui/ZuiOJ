package com.dazuizui.basicapi.entry;

import java.util.Date;

public class LanguageCommand {
    private long id;//
    private int languageId;//语言ID
    private String command;//计算机命令
    private int sequence;//次序
    private String createById; //创建者ID
    private String createByName; //创建者name
    private Date createTime; //创建时间
    private String updateById; //修改者ID
    private String updateByName; //修改者name
    private Date updateTime; //修改时间

    @Override
    public String toString() {
        return "LanguageCommand{" +
                "id=" + id +
                ", languageId=" + languageId +
                ", command='" + command + '\'' +
                ", sequence=" + sequence +
                ", createById='" + createById + '\'' +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", updateById='" + updateById + '\'' +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public long getId() {
        return id;
    }

    public LanguageCommand setId(long id) {
        this.id = id;
        return this;
    }

    public int getLanguageId() {
        return languageId;
    }

    public LanguageCommand setLanguageId(int languageId) {
        this.languageId = languageId;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public LanguageCommand setCommand(String command) {
        this.command = command;
        return this;
    }

    public int getSequence() {
        return sequence;
    }

    public LanguageCommand setSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    public String getCreateById() {
        return createById;
    }

    public LanguageCommand setCreateById(String createById) {
        this.createById = createById;
        return this;
    }

    public String getCreateByName() {
        return createByName;
    }

    public LanguageCommand setCreateByName(String createByName) {
        this.createByName = createByName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LanguageCommand setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateById() {
        return updateById;
    }

    public LanguageCommand setUpdateById(String updateById) {
        this.updateById = updateById;
        return this;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public LanguageCommand setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public LanguageCommand setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public LanguageCommand(long id, int languageId, String command, int sequence, String createById, String createByName, Date createTime, String updateById, String updateByName, Date updateTime) {
        this.id = id;
        this.languageId = languageId;
        this.command = command;
        this.sequence = sequence;
        this.createById = createById;
        this.createByName = createByName;
        this.createTime = createTime;
        this.updateById = updateById;
        this.updateByName = updateByName;
        this.updateTime = updateTime;
    }

    public LanguageCommand() {
    }
}
