package com.example.lenovo.playandroid.dao;

import android.util.Log;

import com.example.lenovo.playandroid.app.App;
import com.example.lenovo.playandroid.beans.yyj.Bean;
import com.example.lenovo.playandroid.beans.zl.LoginData;

import java.util.List;

/**
 * Created by 18 on 2019/3/6.
 */

public class LoginManager {

    public static LoginManager mySqlHelper;
    private LogDaoBeanDao mLogDaoBeanDao;


    public LoginManager() {
        //创建表
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.sApp, "isLog.db");
        //读取方法
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取操作的表
        mLogDaoBeanDao = daoSession.getLogDaoBeanDao();
    }

    public static LoginManager mMySqlHelper() {
        if (mySqlHelper == null) {
            synchronized (LoginManager.class) {
                mySqlHelper = new LoginManager();
            }
        }
        return mySqlHelper;
    }

    public void insert(LogDaoBean list) {
        mLogDaoBeanDao.insert(list);
    }

    public void updata(LogDaoBean logDaoBean) {
        mLogDaoBeanDao.update(logDaoBean);
    }

    public List<LogDaoBean> select() {
        List<LogDaoBean> list = mLogDaoBeanDao.queryBuilder().list();
        return list;
    }
}
