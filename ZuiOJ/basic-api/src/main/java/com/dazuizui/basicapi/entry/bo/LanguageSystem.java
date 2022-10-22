package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 语言运行环境
 */
public class LanguageSystem implements Serializable {
    //args命令
    private List<String> args;
    //env
    private List<String> env;
    //copyOutCached
    private List<String> copyOutCached;

    @Override
    public String toString() {
        return "LanguageSystem{" +
                "args=" + args +
                ", env=" + env +
                ", copyOutCached=" + copyOutCached +
                '}';
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
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

    public LanguageSystem() {
    }

    public LanguageSystem(List<String> args, List<String> env, List<String> copyOutCached) {
        this.args = args;
        this.env = env;
        this.copyOutCached = copyOutCached;
    }
}
