package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 竞赛html和md文件
 */
public class ContestDetailed implements Serializable {
    private Long id;
    private Long contestId;
    private String mdText;
    private String htmlText;

    @Override
    public String toString() {
        return "ContestDetailed{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", mdText='" + mdText + '\'' +
                ", htmlText='" + htmlText + '\'' +
                '}';
    }

    public ContestDetailed() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getMdText() {
        return mdText;
    }

    public void setMdText(String mdText) {
        this.mdText = mdText;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public ContestDetailed(Long id, Long contestId, String mdText, String htmlText) {
        this.id = id;
        this.contestId = contestId;
        this.mdText = mdText;
        this.htmlText = htmlText;
    }
}
