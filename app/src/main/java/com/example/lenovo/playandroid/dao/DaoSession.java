package com.example.lenovo.playandroid.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.lenovo.playandroid.dao.HistoryData;
import com.example.lenovo.playandroid.dao.LogDaoBean;

import com.example.lenovo.playandroid.dao.HistoryDataDao;
import com.example.lenovo.playandroid.dao.LogDaoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyDataDaoConfig;
    private final DaoConfig logDaoBeanDaoConfig;

    private final HistoryDataDao historyDataDao;
    private final LogDaoBeanDao logDaoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyDataDaoConfig = daoConfigMap.get(HistoryDataDao.class).clone();
        historyDataDaoConfig.initIdentityScope(type);

        logDaoBeanDaoConfig = daoConfigMap.get(LogDaoBeanDao.class).clone();
        logDaoBeanDaoConfig.initIdentityScope(type);

        historyDataDao = new HistoryDataDao(historyDataDaoConfig, this);
        logDaoBeanDao = new LogDaoBeanDao(logDaoBeanDaoConfig, this);

        registerDao(HistoryData.class, historyDataDao);
        registerDao(LogDaoBean.class, logDaoBeanDao);
    }
    
    public void clear() {
        historyDataDaoConfig.clearIdentityScope();
        logDaoBeanDaoConfig.clearIdentityScope();
    }

    public HistoryDataDao getHistoryDataDao() {
        return historyDataDao;
    }

    public LogDaoBeanDao getLogDaoBeanDao() {
        return logDaoBeanDao;
    }

}
