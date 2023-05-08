package com.dazuizui.business.domain;

import java.io.Serializable;

/**
 * 博文属性实体
 */
public class BlogAttribute implements Serializable {
    private Long id;                //主键
    private Long publicArticle;     //公开文章数量
    private Long privateArticle;    //私有文章数量
    private Long bannedArticle;     //封禁文章数量
    private Long number;            //总数量

    @Override
    public String toString() {
        return "BlogAttribute{" +
                "id=" + id +
                ", publicArticle=" + publicArticle +
                ", privateArticle=" + privateArticle +
                ", bannedArticle=" + bannedArticle +
                ", number=" + number +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublicArticle() {
        return publicArticle;
    }

    public void setPublicArticle(Long publicArticle) {
        this.publicArticle = publicArticle;
    }

    public Long getPrivateArticle() {
        return privateArticle;
    }

    public void setPrivateArticle(Long privateArticle) {
        this.privateArticle = privateArticle;
    }

    public Long getBannedArticle() {
        return bannedArticle;
    }

    public void setBannedArticle(Long bannedArticle) {
        this.bannedArticle = bannedArticle;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BlogAttribute() {
    }

    public BlogAttribute(Long id, Long publicArticle, Long privateArticle, Long bannedArticle, Long number) {
        this.id = id;
        this.publicArticle = publicArticle;
        this.privateArticle = privateArticle;
        this.bannedArticle = bannedArticle;
        this.number = number;
    }
}
