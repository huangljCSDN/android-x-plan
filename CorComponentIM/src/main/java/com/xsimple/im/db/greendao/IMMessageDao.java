package com.xsimple.im.db.greendao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.xsimple.im.db.datatable.IMCallInfo;
import com.xsimple.im.db.datatable.IMChatRecordInfo;
import com.xsimple.im.db.datatable.IMFileInfo;
import com.xsimple.im.db.datatable.IMGroupRemark;
import com.xsimple.im.db.datatable.IMLocationInfo;
import com.xsimple.im.db.datatable.IMReplyInfo;
import com.xsimple.im.db.datatable.IMShareInfo;

import com.xsimple.im.db.datatable.IMMessage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "im_msg_his_new".
*/
public class IMMessageDao extends AbstractDao<IMMessage, Long> {

    public static final String TABLENAME = "im_msg_his_new";

    /**
     * Properties of entity IMMessage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property LocalId = new Property(0, Long.class, "localId", true, "_id");
        public final static Property MsgID = new Property(1, String.class, "msgID", false, "msg_id");
        public final static Property ReceiverName = new Property(2, String.class, "receiverName", false, "receiverName");
        public final static Property VId = new Property(3, String.class, "vId", false, "v_id");
        public final static Property CId = new Property(4, Long.class, "cId", false, "chat_id");
        public final static Property TagertId = new Property(5, String.class, "tagertId", false, "tagert_id");
        public final static Property SendOrReceive = new Property(6, int.class, "sendOrReceive", false, "sendOrReceive");
        public final static Property GroupName = new Property(7, String.class, "groupName", false, "group_name");
        public final static Property SenderId = new Property(8, String.class, "senderId", false, "sender_id");
        public final static Property ApplyId = new Property(9, String.class, "applyId", false, "apply_id");
        public final static Property SenderName = new Property(10, String.class, "senderName", false, "sender_name");
        public final static Property Time = new Property(11, long.class, "time", false, "time");
        public final static Property Type = new Property(12, int.class, "type", false, "type");
        public final static Property ContentType = new Property(13, String.class, "contentType", false, "content_type");
        public final static Property Content = new Property(14, String.class, "content", false, "content");
        public final static Property Status = new Property(15, int.class, "status", false, "status");
        public final static Property IsRead = new Property(16, boolean.class, "isRead", false, "read");
        public final static Property IsAgree = new Property(17, boolean.class, "isAgree", false, "agree");
        public final static Property ReadCount = new Property(18, int.class, "readCount", false, "read_count");
        public final static Property UnReadCount = new Property(19, int.class, "unReadCount", false, "un_read_count");
        public final static Property Mk = new Property(20, String.class, "mk", false, "mk");
        public final static Property IsDisturb = new Property(21, String.class, "isDisturb", false, "IS_DISTURB");
        public final static Property IsAiteMe = new Property(22, boolean.class, "isAiteMe", false, "IS_AITE_ME");
        public final static Property FId = new Property(23, Long.class, "fId", false, "F_ID");
        public final static Property CallId = new Property(24, Long.class, "callId", false, "CALL_ID");
        public final static Property LId = new Property(25, Long.class, "lId", false, "L_ID");
        public final static Property SId = new Property(26, Long.class, "sId", false, "S_ID");
        public final static Property RId = new Property(27, Long.class, "rId", false, "R_ID");
        public final static Property ReplyId = new Property(28, Long.class, "replyId", false, "REPLY_ID");
        public final static Property RecordId = new Property(29, Long.class, "recordId", false, "RECORD_ID");
        public final static Property AtInfo = new Property(30, String.class, "AtInfo", false, "AT_INFO");
    }

    private DaoSession daoSession;

    private Query<IMMessage> iMChat_IMMessagesQuery;

    public IMMessageDao(DaoConfig config) {
        super(config);
    }
    
    public IMMessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"im_msg_his_new\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: localId
                "\"msg_id\" TEXT," + // 1: msgID
                "\"receiverName\" TEXT," + // 2: receiverName
                "\"v_id\" TEXT," + // 3: vId
                "\"chat_id\" INTEGER," + // 4: cId
                "\"tagert_id\" TEXT," + // 5: tagertId
                "\"sendOrReceive\" INTEGER NOT NULL ," + // 6: sendOrReceive
                "\"group_name\" TEXT," + // 7: groupName
                "\"sender_id\" TEXT," + // 8: senderId
                "\"apply_id\" TEXT," + // 9: applyId
                "\"sender_name\" TEXT," + // 10: senderName
                "\"time\" INTEGER NOT NULL ," + // 11: time
                "\"type\" INTEGER NOT NULL ," + // 12: type
                "\"content_type\" TEXT," + // 13: contentType
                "\"content\" TEXT," + // 14: content
                "\"status\" INTEGER NOT NULL ," + // 15: status
                "\"read\" INTEGER NOT NULL ," + // 16: isRead
                "\"agree\" INTEGER NOT NULL ," + // 17: isAgree
                "\"read_count\" INTEGER NOT NULL ," + // 18: readCount
                "\"un_read_count\" INTEGER NOT NULL ," + // 19: unReadCount
                "\"mk\" TEXT," + // 20: mk
                "\"IS_DISTURB\" TEXT," + // 21: isDisturb
                "\"IS_AITE_ME\" INTEGER NOT NULL ," + // 22: isAiteMe
                "\"F_ID\" INTEGER," + // 23: fId
                "\"CALL_ID\" INTEGER," + // 24: callId
                "\"L_ID\" INTEGER," + // 25: lId
                "\"S_ID\" INTEGER," + // 26: sId
                "\"R_ID\" INTEGER," + // 27: rId
                "\"REPLY_ID\" INTEGER," + // 28: replyId
                "\"RECORD_ID\" INTEGER," + // 29: recordId
                "\"AT_INFO\" TEXT);"); // 30: AtInfo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"im_msg_his_new\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IMMessage entity) {
        stmt.clearBindings();
 
        Long localId = entity.getLocalId();
        if (localId != null) {
            stmt.bindLong(1, localId);
        }
 
        String msgID = entity.getMsgID();
        if (msgID != null) {
            stmt.bindString(2, msgID);
        }
 
        String receiverName = entity.getReceiverName();
        if (receiverName != null) {
            stmt.bindString(3, receiverName);
        }
 
        String vId = entity.getVId();
        if (vId != null) {
            stmt.bindString(4, vId);
        }
 
        Long cId = entity.getCId();
        if (cId != null) {
            stmt.bindLong(5, cId);
        }
 
        String tagertId = entity.getTagertId();
        if (tagertId != null) {
            stmt.bindString(6, tagertId);
        }
        stmt.bindLong(7, entity.getSendOrReceive());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(8, groupName);
        }
 
        String senderId = entity.getSenderId();
        if (senderId != null) {
            stmt.bindString(9, senderId);
        }
 
        String applyId = entity.getApplyId();
        if (applyId != null) {
            stmt.bindString(10, applyId);
        }
 
        String senderName = entity.getSenderName();
        if (senderName != null) {
            stmt.bindString(11, senderName);
        }
        stmt.bindLong(12, entity.getTime());
        stmt.bindLong(13, entity.getType());
 
        String contentType = entity.getContentType();
        if (contentType != null) {
            stmt.bindString(14, contentType);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
        stmt.bindLong(16, entity.getStatus());
        stmt.bindLong(17, entity.getIsRead() ? 1L: 0L);
        stmt.bindLong(18, entity.getIsAgree() ? 1L: 0L);
        stmt.bindLong(19, entity.getReadCount());
        stmt.bindLong(20, entity.getUnReadCount());
 
        String mk = entity.getMk();
        if (mk != null) {
            stmt.bindString(21, mk);
        }
 
        String isDisturb = entity.getIsDisturb();
        if (isDisturb != null) {
            stmt.bindString(22, isDisturb);
        }
        stmt.bindLong(23, entity.getIsAiteMe() ? 1L: 0L);
 
        Long fId = entity.getFId();
        if (fId != null) {
            stmt.bindLong(24, fId);
        }
 
        Long callId = entity.getCallId();
        if (callId != null) {
            stmt.bindLong(25, callId);
        }
 
        Long lId = entity.getLId();
        if (lId != null) {
            stmt.bindLong(26, lId);
        }
 
        Long sId = entity.getSId();
        if (sId != null) {
            stmt.bindLong(27, sId);
        }
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(28, rId);
        }
 
        Long replyId = entity.getReplyId();
        if (replyId != null) {
            stmt.bindLong(29, replyId);
        }
 
        Long recordId = entity.getRecordId();
        if (recordId != null) {
            stmt.bindLong(30, recordId);
        }
 
        String AtInfo = entity.getAtInfo();
        if (AtInfo != null) {
            stmt.bindString(31, AtInfo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IMMessage entity) {
        stmt.clearBindings();
 
        Long localId = entity.getLocalId();
        if (localId != null) {
            stmt.bindLong(1, localId);
        }
 
        String msgID = entity.getMsgID();
        if (msgID != null) {
            stmt.bindString(2, msgID);
        }
 
        String receiverName = entity.getReceiverName();
        if (receiverName != null) {
            stmt.bindString(3, receiverName);
        }
 
        String vId = entity.getVId();
        if (vId != null) {
            stmt.bindString(4, vId);
        }
 
        Long cId = entity.getCId();
        if (cId != null) {
            stmt.bindLong(5, cId);
        }
 
        String tagertId = entity.getTagertId();
        if (tagertId != null) {
            stmt.bindString(6, tagertId);
        }
        stmt.bindLong(7, entity.getSendOrReceive());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(8, groupName);
        }
 
        String senderId = entity.getSenderId();
        if (senderId != null) {
            stmt.bindString(9, senderId);
        }
 
        String applyId = entity.getApplyId();
        if (applyId != null) {
            stmt.bindString(10, applyId);
        }
 
        String senderName = entity.getSenderName();
        if (senderName != null) {
            stmt.bindString(11, senderName);
        }
        stmt.bindLong(12, entity.getTime());
        stmt.bindLong(13, entity.getType());
 
        String contentType = entity.getContentType();
        if (contentType != null) {
            stmt.bindString(14, contentType);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(15, content);
        }
        stmt.bindLong(16, entity.getStatus());
        stmt.bindLong(17, entity.getIsRead() ? 1L: 0L);
        stmt.bindLong(18, entity.getIsAgree() ? 1L: 0L);
        stmt.bindLong(19, entity.getReadCount());
        stmt.bindLong(20, entity.getUnReadCount());
 
        String mk = entity.getMk();
        if (mk != null) {
            stmt.bindString(21, mk);
        }
 
        String isDisturb = entity.getIsDisturb();
        if (isDisturb != null) {
            stmt.bindString(22, isDisturb);
        }
        stmt.bindLong(23, entity.getIsAiteMe() ? 1L: 0L);
 
        Long fId = entity.getFId();
        if (fId != null) {
            stmt.bindLong(24, fId);
        }
 
        Long callId = entity.getCallId();
        if (callId != null) {
            stmt.bindLong(25, callId);
        }
 
        Long lId = entity.getLId();
        if (lId != null) {
            stmt.bindLong(26, lId);
        }
 
        Long sId = entity.getSId();
        if (sId != null) {
            stmt.bindLong(27, sId);
        }
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(28, rId);
        }
 
        Long replyId = entity.getReplyId();
        if (replyId != null) {
            stmt.bindLong(29, replyId);
        }
 
        Long recordId = entity.getRecordId();
        if (recordId != null) {
            stmt.bindLong(30, recordId);
        }
 
        String AtInfo = entity.getAtInfo();
        if (AtInfo != null) {
            stmt.bindString(31, AtInfo);
        }
    }

    @Override
    protected final void attachEntity(IMMessage entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IMMessage readEntity(Cursor cursor, int offset) {
        IMMessage entity = new IMMessage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // localId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // msgID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // receiverName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // vId
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // cId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // tagertId
            cursor.getInt(offset + 6), // sendOrReceive
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // groupName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // senderId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // applyId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // senderName
            cursor.getLong(offset + 11), // time
            cursor.getInt(offset + 12), // type
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // contentType
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // content
            cursor.getInt(offset + 15), // status
            cursor.getShort(offset + 16) != 0, // isRead
            cursor.getShort(offset + 17) != 0, // isAgree
            cursor.getInt(offset + 18), // readCount
            cursor.getInt(offset + 19), // unReadCount
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // mk
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // isDisturb
            cursor.getShort(offset + 22) != 0, // isAiteMe
            cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23), // fId
            cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24), // callId
            cursor.isNull(offset + 25) ? null : cursor.getLong(offset + 25), // lId
            cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26), // sId
            cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27), // rId
            cursor.isNull(offset + 28) ? null : cursor.getLong(offset + 28), // replyId
            cursor.isNull(offset + 29) ? null : cursor.getLong(offset + 29), // recordId
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30) // AtInfo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IMMessage entity, int offset) {
        entity.setLocalId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMsgID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setReceiverName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setVId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setTagertId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSendOrReceive(cursor.getInt(offset + 6));
        entity.setGroupName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSenderId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setApplyId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSenderName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTime(cursor.getLong(offset + 11));
        entity.setType(cursor.getInt(offset + 12));
        entity.setContentType(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setContent(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setStatus(cursor.getInt(offset + 15));
        entity.setIsRead(cursor.getShort(offset + 16) != 0);
        entity.setIsAgree(cursor.getShort(offset + 17) != 0);
        entity.setReadCount(cursor.getInt(offset + 18));
        entity.setUnReadCount(cursor.getInt(offset + 19));
        entity.setMk(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setIsDisturb(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setIsAiteMe(cursor.getShort(offset + 22) != 0);
        entity.setFId(cursor.isNull(offset + 23) ? null : cursor.getLong(offset + 23));
        entity.setCallId(cursor.isNull(offset + 24) ? null : cursor.getLong(offset + 24));
        entity.setLId(cursor.isNull(offset + 25) ? null : cursor.getLong(offset + 25));
        entity.setSId(cursor.isNull(offset + 26) ? null : cursor.getLong(offset + 26));
        entity.setRId(cursor.isNull(offset + 27) ? null : cursor.getLong(offset + 27));
        entity.setReplyId(cursor.isNull(offset + 28) ? null : cursor.getLong(offset + 28));
        entity.setRecordId(cursor.isNull(offset + 29) ? null : cursor.getLong(offset + 29));
        entity.setAtInfo(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IMMessage entity, long rowId) {
        entity.setLocalId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IMMessage entity) {
        if(entity != null) {
            return entity.getLocalId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IMMessage entity) {
        return entity.getLocalId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "IMMessages" to-many relationship of IMChat. */
    public List<IMMessage> _queryIMChat_IMMessages(Long cId) {
        synchronized (this) {
            if (iMChat_IMMessagesQuery == null) {
                QueryBuilder<IMMessage> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CId.eq(null));
                iMChat_IMMessagesQuery = queryBuilder.build();
            }
        }
        Query<IMMessage> query = iMChat_IMMessagesQuery.forCurrentThread();
        query.setParameter(0, cId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getIMFileInfoDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getIMCallInfoDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getIMLocationInfoDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T3", daoSession.getIMShareInfoDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T4", daoSession.getIMGroupRemarkDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T5", daoSession.getIMReplyInfoDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T6", daoSession.getIMChatRecordInfoDao().getAllColumns());
            builder.append(" FROM im_msg_his_new T");
            builder.append(" LEFT JOIN im_msg_file_new T0 ON T.\"F_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN im_msg_call_new T1 ON T.\"CALL_ID\"=T1.\"_id\"");
            builder.append(" LEFT JOIN im_msg_location_new T2 ON T.\"L_ID\"=T2.\"_id\"");
            builder.append(" LEFT JOIN im_msg_shareinfo_new T3 ON T.\"S_ID\"=T3.\"_id\"");
            builder.append(" LEFT JOIN im_msg_group_remark_new T4 ON T.\"R_ID\"=T4.\"_id\"");
            builder.append(" LEFT JOIN im_msg_reply_info_new T5 ON T.\"REPLY_ID\"=T5.\"_id\"");
            builder.append(" LEFT JOIN im_msg_chat_record_info_new T6 ON T.\"RECORD_ID\"=T6.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected IMMessage loadCurrentDeep(Cursor cursor, boolean lock) {
        IMMessage entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        IMFileInfo IMFileInfo = loadCurrentOther(daoSession.getIMFileInfoDao(), cursor, offset);
        entity.setIMFileInfo(IMFileInfo);
        offset += daoSession.getIMFileInfoDao().getAllColumns().length;

        IMCallInfo IMCallInfo = loadCurrentOther(daoSession.getIMCallInfoDao(), cursor, offset);
        entity.setIMCallInfo(IMCallInfo);
        offset += daoSession.getIMCallInfoDao().getAllColumns().length;

        IMLocationInfo IMLocationInfo = loadCurrentOther(daoSession.getIMLocationInfoDao(), cursor, offset);
        entity.setIMLocationInfo(IMLocationInfo);
        offset += daoSession.getIMLocationInfoDao().getAllColumns().length;

        IMShareInfo IMShareInfo = loadCurrentOther(daoSession.getIMShareInfoDao(), cursor, offset);
        entity.setIMShareInfo(IMShareInfo);
        offset += daoSession.getIMShareInfoDao().getAllColumns().length;

        IMGroupRemark IMGroupRemark = loadCurrentOther(daoSession.getIMGroupRemarkDao(), cursor, offset);
        entity.setIMGroupRemark(IMGroupRemark);
        offset += daoSession.getIMGroupRemarkDao().getAllColumns().length;

        IMReplyInfo IMReplyInfo = loadCurrentOther(daoSession.getIMReplyInfoDao(), cursor, offset);
        entity.setIMReplyInfo(IMReplyInfo);
        offset += daoSession.getIMReplyInfoDao().getAllColumns().length;

        IMChatRecordInfo IMChatRecordInfo = loadCurrentOther(daoSession.getIMChatRecordInfoDao(), cursor, offset);
        entity.setIMChatRecordInfo(IMChatRecordInfo);

        return entity;    
    }

    public IMMessage loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<IMMessage> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<IMMessage> list = new ArrayList<IMMessage>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<IMMessage> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<IMMessage> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
