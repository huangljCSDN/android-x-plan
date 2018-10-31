package com.xsimple.im.db.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.xsimple.im.db.datatable.IMMessage;
import com.xsimple.im.db.datatable.IMChat;
import com.xsimple.im.db.datatable.IMGroupUser;
import com.xsimple.im.db.datatable.IMChatRecordInfo;
import com.xsimple.im.db.datatable.IMGroupRemark;
import com.xsimple.im.db.datatable.IMFileInfoPice;
import com.xsimple.im.db.datatable.IMFileInfo;
import com.xsimple.im.db.datatable.IMSysMessage;
import com.xsimple.im.db.datatable.IMReplyInfo;
import com.xsimple.im.db.datatable.IMUser;
import com.xsimple.im.db.datatable.IMShareInfo;
import com.xsimple.im.db.datatable.IMGroup;
import com.xsimple.im.db.datatable.IMCallInfo;
import com.xsimple.im.db.datatable.IMLocationInfo;

import com.xsimple.im.db.greendao.IMMessageDao;
import com.xsimple.im.db.greendao.IMChatDao;
import com.xsimple.im.db.greendao.IMGroupUserDao;
import com.xsimple.im.db.greendao.IMChatRecordInfoDao;
import com.xsimple.im.db.greendao.IMGroupRemarkDao;
import com.xsimple.im.db.greendao.IMFileInfoPiceDao;
import com.xsimple.im.db.greendao.IMFileInfoDao;
import com.xsimple.im.db.greendao.IMSysMessageDao;
import com.xsimple.im.db.greendao.IMReplyInfoDao;
import com.xsimple.im.db.greendao.IMUserDao;
import com.xsimple.im.db.greendao.IMShareInfoDao;
import com.xsimple.im.db.greendao.IMGroupDao;
import com.xsimple.im.db.greendao.IMCallInfoDao;
import com.xsimple.im.db.greendao.IMLocationInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig iMMessageDaoConfig;
    private final DaoConfig iMChatDaoConfig;
    private final DaoConfig iMGroupUserDaoConfig;
    private final DaoConfig iMChatRecordInfoDaoConfig;
    private final DaoConfig iMGroupRemarkDaoConfig;
    private final DaoConfig iMFileInfoPiceDaoConfig;
    private final DaoConfig iMFileInfoDaoConfig;
    private final DaoConfig iMSysMessageDaoConfig;
    private final DaoConfig iMReplyInfoDaoConfig;
    private final DaoConfig iMUserDaoConfig;
    private final DaoConfig iMShareInfoDaoConfig;
    private final DaoConfig iMGroupDaoConfig;
    private final DaoConfig iMCallInfoDaoConfig;
    private final DaoConfig iMLocationInfoDaoConfig;

    private final IMMessageDao iMMessageDao;
    private final IMChatDao iMChatDao;
    private final IMGroupUserDao iMGroupUserDao;
    private final IMChatRecordInfoDao iMChatRecordInfoDao;
    private final IMGroupRemarkDao iMGroupRemarkDao;
    private final IMFileInfoPiceDao iMFileInfoPiceDao;
    private final IMFileInfoDao iMFileInfoDao;
    private final IMSysMessageDao iMSysMessageDao;
    private final IMReplyInfoDao iMReplyInfoDao;
    private final IMUserDao iMUserDao;
    private final IMShareInfoDao iMShareInfoDao;
    private final IMGroupDao iMGroupDao;
    private final IMCallInfoDao iMCallInfoDao;
    private final IMLocationInfoDao iMLocationInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        iMMessageDaoConfig = daoConfigMap.get(IMMessageDao.class).clone();
        iMMessageDaoConfig.initIdentityScope(type);

        iMChatDaoConfig = daoConfigMap.get(IMChatDao.class).clone();
        iMChatDaoConfig.initIdentityScope(type);

        iMGroupUserDaoConfig = daoConfigMap.get(IMGroupUserDao.class).clone();
        iMGroupUserDaoConfig.initIdentityScope(type);

        iMChatRecordInfoDaoConfig = daoConfigMap.get(IMChatRecordInfoDao.class).clone();
        iMChatRecordInfoDaoConfig.initIdentityScope(type);

        iMGroupRemarkDaoConfig = daoConfigMap.get(IMGroupRemarkDao.class).clone();
        iMGroupRemarkDaoConfig.initIdentityScope(type);

        iMFileInfoPiceDaoConfig = daoConfigMap.get(IMFileInfoPiceDao.class).clone();
        iMFileInfoPiceDaoConfig.initIdentityScope(type);

        iMFileInfoDaoConfig = daoConfigMap.get(IMFileInfoDao.class).clone();
        iMFileInfoDaoConfig.initIdentityScope(type);

        iMSysMessageDaoConfig = daoConfigMap.get(IMSysMessageDao.class).clone();
        iMSysMessageDaoConfig.initIdentityScope(type);

        iMReplyInfoDaoConfig = daoConfigMap.get(IMReplyInfoDao.class).clone();
        iMReplyInfoDaoConfig.initIdentityScope(type);

        iMUserDaoConfig = daoConfigMap.get(IMUserDao.class).clone();
        iMUserDaoConfig.initIdentityScope(type);

        iMShareInfoDaoConfig = daoConfigMap.get(IMShareInfoDao.class).clone();
        iMShareInfoDaoConfig.initIdentityScope(type);

        iMGroupDaoConfig = daoConfigMap.get(IMGroupDao.class).clone();
        iMGroupDaoConfig.initIdentityScope(type);

        iMCallInfoDaoConfig = daoConfigMap.get(IMCallInfoDao.class).clone();
        iMCallInfoDaoConfig.initIdentityScope(type);

        iMLocationInfoDaoConfig = daoConfigMap.get(IMLocationInfoDao.class).clone();
        iMLocationInfoDaoConfig.initIdentityScope(type);

        iMMessageDao = new IMMessageDao(iMMessageDaoConfig, this);
        iMChatDao = new IMChatDao(iMChatDaoConfig, this);
        iMGroupUserDao = new IMGroupUserDao(iMGroupUserDaoConfig, this);
        iMChatRecordInfoDao = new IMChatRecordInfoDao(iMChatRecordInfoDaoConfig, this);
        iMGroupRemarkDao = new IMGroupRemarkDao(iMGroupRemarkDaoConfig, this);
        iMFileInfoPiceDao = new IMFileInfoPiceDao(iMFileInfoPiceDaoConfig, this);
        iMFileInfoDao = new IMFileInfoDao(iMFileInfoDaoConfig, this);
        iMSysMessageDao = new IMSysMessageDao(iMSysMessageDaoConfig, this);
        iMReplyInfoDao = new IMReplyInfoDao(iMReplyInfoDaoConfig, this);
        iMUserDao = new IMUserDao(iMUserDaoConfig, this);
        iMShareInfoDao = new IMShareInfoDao(iMShareInfoDaoConfig, this);
        iMGroupDao = new IMGroupDao(iMGroupDaoConfig, this);
        iMCallInfoDao = new IMCallInfoDao(iMCallInfoDaoConfig, this);
        iMLocationInfoDao = new IMLocationInfoDao(iMLocationInfoDaoConfig, this);

        registerDao(IMMessage.class, iMMessageDao);
        registerDao(IMChat.class, iMChatDao);
        registerDao(IMGroupUser.class, iMGroupUserDao);
        registerDao(IMChatRecordInfo.class, iMChatRecordInfoDao);
        registerDao(IMGroupRemark.class, iMGroupRemarkDao);
        registerDao(IMFileInfoPice.class, iMFileInfoPiceDao);
        registerDao(IMFileInfo.class, iMFileInfoDao);
        registerDao(IMSysMessage.class, iMSysMessageDao);
        registerDao(IMReplyInfo.class, iMReplyInfoDao);
        registerDao(IMUser.class, iMUserDao);
        registerDao(IMShareInfo.class, iMShareInfoDao);
        registerDao(IMGroup.class, iMGroupDao);
        registerDao(IMCallInfo.class, iMCallInfoDao);
        registerDao(IMLocationInfo.class, iMLocationInfoDao);
    }
    
    public void clear() {
        iMMessageDaoConfig.clearIdentityScope();
        iMChatDaoConfig.clearIdentityScope();
        iMGroupUserDaoConfig.clearIdentityScope();
        iMChatRecordInfoDaoConfig.clearIdentityScope();
        iMGroupRemarkDaoConfig.clearIdentityScope();
        iMFileInfoPiceDaoConfig.clearIdentityScope();
        iMFileInfoDaoConfig.clearIdentityScope();
        iMSysMessageDaoConfig.clearIdentityScope();
        iMReplyInfoDaoConfig.clearIdentityScope();
        iMUserDaoConfig.clearIdentityScope();
        iMShareInfoDaoConfig.clearIdentityScope();
        iMGroupDaoConfig.clearIdentityScope();
        iMCallInfoDaoConfig.clearIdentityScope();
        iMLocationInfoDaoConfig.clearIdentityScope();
    }

    public IMMessageDao getIMMessageDao() {
        return iMMessageDao;
    }

    public IMChatDao getIMChatDao() {
        return iMChatDao;
    }

    public IMGroupUserDao getIMGroupUserDao() {
        return iMGroupUserDao;
    }

    public IMChatRecordInfoDao getIMChatRecordInfoDao() {
        return iMChatRecordInfoDao;
    }

    public IMGroupRemarkDao getIMGroupRemarkDao() {
        return iMGroupRemarkDao;
    }

    public IMFileInfoPiceDao getIMFileInfoPiceDao() {
        return iMFileInfoPiceDao;
    }

    public IMFileInfoDao getIMFileInfoDao() {
        return iMFileInfoDao;
    }

    public IMSysMessageDao getIMSysMessageDao() {
        return iMSysMessageDao;
    }

    public IMReplyInfoDao getIMReplyInfoDao() {
        return iMReplyInfoDao;
    }

    public IMUserDao getIMUserDao() {
        return iMUserDao;
    }

    public IMShareInfoDao getIMShareInfoDao() {
        return iMShareInfoDao;
    }

    public IMGroupDao getIMGroupDao() {
        return iMGroupDao;
    }

    public IMCallInfoDao getIMCallInfoDao() {
        return iMCallInfoDao;
    }

    public IMLocationInfoDao getIMLocationInfoDao() {
        return iMLocationInfoDao;
    }

}
