package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 18 on 2019/3/8.
 */

@Entity
public class IsLikeDaoBean {
    @Id(autoincrement = true)
    private Long id;
    private String url;
    @Generated(hash = 760983330)
    public IsLikeDaoBean(Long id, String url) {
        this.id = id;
        this.url = url;
    }
    @Generated(hash = 667840078)
    public IsLikeDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
