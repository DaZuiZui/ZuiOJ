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

    private int status;

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
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateById() {
        return updateById;
    }

    public void setUpdateById(String updateById) {
        this.updateById = updateById;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LanguageCommand() {
    }

    public LanguageCommand(long id, int languageId, String command, int sequence, String createById, String createByName, Date createTime, String updateById, String updateByName, Date updateTime, int status) {
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
        this.status = status;
    }
}
