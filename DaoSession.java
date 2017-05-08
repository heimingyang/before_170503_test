package com.example.before_170503_test;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.before_170503_test.Model;

import com.example.before_170503_test.ModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig modelDaoConfig;

    private final ModelDao modelDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        modelDaoConfig = daoConfigMap.get(ModelDao.class).clone();
        modelDaoConfig.initIdentityScope(type);

        modelDao = new ModelDao(modelDaoConfig, this);

        registerDao(Model.class, modelDao);
    }
    
    public void clear() {
        modelDaoConfig.getIdentityScope().clear();
    }

    public ModelDao getModelDao() {
        return modelDao;
    }

}
