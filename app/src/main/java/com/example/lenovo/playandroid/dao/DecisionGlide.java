package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2019/3/6.
 */

@Entity
public class DecisionGlide {

    @Id(autoincrement = true)
    Long id;
    Boolean isbo;
    @Generated(hash = 1897427343)
    public DecisionGlide(Long id, Boolean isbo) {
        this.id = id;
        this.isbo = isbo;
    }
    @Generated(hash = 1687400197)
    public DecisionGlide() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getIsbo() {
        return this.isbo;
    }
    public void setIsbo(Boolean isbo) {
        this.isbo = isbo;
    }
    
}
