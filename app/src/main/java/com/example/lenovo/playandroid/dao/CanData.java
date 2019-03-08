package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2019/3/8.
 */
@Entity
public class CanData {
    @Id(autoincrement = true)
    Long id;
    String title;
    String author;
    String link;
    Boolean isbo;
    @Generated(hash = 902057032)
    public CanData(Long id, String title, String author, String link,
            Boolean isbo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.link = link;
        this.isbo = isbo;
    }
    @Generated(hash = 165222764)
    public CanData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Boolean getIsbo() {
        return this.isbo;
    }
    public void setIsbo(Boolean isbo) {
        this.isbo = isbo;
    }
}
