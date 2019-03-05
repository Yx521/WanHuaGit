package com.example.lenovo.playandroid.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2019/3/5.
 */
@Entity
public class HistoryData {

    @Id(autoincrement = true)
    Long id;

    String history;

    @Generated(hash = 565162702)
    public HistoryData(Long id, String history) {
        this.id = id;
        this.history = history;
    }

    @Generated(hash = 422767273)
    public HistoryData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
