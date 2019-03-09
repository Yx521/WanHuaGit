package com.example.lenovo.playandroid.dao;

import com.example.lenovo.playandroid.app.App;

import java.util.List;

/**
 * Created by lenovo on 2019/3/5.
 */

public class DataBaseMannger {
    public static DataBaseMannger sDataBaseMannger;
    private final HistoryDataDao mHistoryDataDao;
    private final DecisionGlideDao mDecisionGlideDao;

    //单例模式
    public static DataBaseMannger getIntrance() {
        if (sDataBaseMannger == null) {
            synchronized (DataBaseMannger.class) {
                if (sDataBaseMannger == null) {
                    sDataBaseMannger = new DataBaseMannger();
                }
            }
        }
        return sDataBaseMannger;
    }

    DataBaseMannger() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApplication(), "state.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        mHistoryDataDao = daoSession.getHistoryDataDao();
        mDecisionGlideDao = daoSession.getDecisionGlideDao();
    }

    //查询
    public List<HistoryData> select() {
        return mHistoryDataDao.queryBuilder().list();
    }

    //查询
    public List<DecisionGlide> selectGlide() {
        return mDecisionGlideDao.queryBuilder().list();
    }

    //添加
    public void insert(HistoryData list) {
        mHistoryDataDao.insertInTx(list);
    }

    //添加
    public void insertGlide(DecisionGlide list) {
        mDecisionGlideDao.insertInTx(list);
    }

    //删除
    public void deleteAll() {
        mHistoryDataDao.deleteAll();
    }

    //修改
    public void updateGlide(DecisionGlide bean) {
        mDecisionGlideDao.update(bean);
    }

}
