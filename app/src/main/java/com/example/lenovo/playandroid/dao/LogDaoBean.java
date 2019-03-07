package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 18 on 2019/3/6.
 */

@Entity
public class LogDaoBean {

    @Id(autoincrement = true)
    private Long id ;
    private String name ;
    private boolean isLogin;
    @Generated(hash = 265239317)
    public LogDaoBean(Long id, String name, boolean isLogin) {
        this.id = id;
        this.name = name;
        this.isLogin = isLogin;
    }
    @Generated(hash = 292139906)
    public LogDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsLogin() {
        return this.isLogin;
    }
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }


}
