package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2019/3/8.
 */
@Entity
public class ShanData {

    @Id(autoincrement = true)
    Long id;
    int cid;
    String originId;
    String title;
    @Generated(hash = 387158059)
    public ShanData(Long id, int cid, String originId, String title) {
        this.id = id;
        this.cid = cid;
        this.originId = originId;
        this.title = title;
    }
    @Generated(hash = 1824204964)
    public ShanData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCid() {
        return this.cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getOriginId() {
        return this.originId;
    }
    public void setOriginId(String originId) {
        this.originId = originId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
