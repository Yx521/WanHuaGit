package com.example.lenovo.playandroid.dao;

import com.example.lenovo.playandroid.app.App;
import com.example.lenovo.playandroid.beans.yyj.Bean;

import java.util.List;

/**
 * Created by 18 on 2019/3/8.
 */

public class IsLikeManager {

    public static IsLikeManager mySqlHelper;
    private IsLikeDaoBeanDao mBeanDao;

    public IsLikeManager() {
        //创建表
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.sApp, "isLike.db");
        //读取方法
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取操作的表
        mBeanDao = daoSession.getIsLikeDaoBeanDao();
    }

    public static IsLikeManager mMySqlHelper() {
        if (mySqlHelper == null) {
            synchronized (IsLikeManager.class) {
                mySqlHelper = new IsLikeManager();
            }
        }
        return mySqlHelper;
    }

    public void insert(IsLikeDaoBean likeDaoBean) {
        mBeanDao.insert(likeDaoBean);
    }

    public void delete(IsLikeDaoBean isLikeDaoBean) {
        mBeanDao.delete(isLikeDaoBean);
    }

    public List<IsLikeDaoBean> select() {

        List<IsLikeDaoBean> list = mBeanDao.queryBuilder().list();
        return list;
    }

}
