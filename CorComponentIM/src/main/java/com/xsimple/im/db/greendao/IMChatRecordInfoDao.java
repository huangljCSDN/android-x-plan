package com.xsimple.im.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xsimple.im.db.StringConverter;
import java.util.ArrayList;

import com.xsimple.im.db.datatable.IMChatRecordInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "im_msg_chat_record_info_new".
*/
public class IMChatRecordInfoDao extends AbstractDao<IMChatRecordInfo, Long> {

    public static final String TABLENAME = "im_msg_chat_record_info_new";

    /**
     * Properties of entity IMChatRecordInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property RId = new Property(0, Long.class, "rId", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public final static Property ReceiverId = new Property(3, String.class, "receiverId", false, "RECEIVER_ID");
        public final static Property MsgIds = new Property(4, String.class, "msgIds", false, "MSG_IDS");
    }

    private final StringConverter msgIdsConverter = new StringConverter();

    public IMChatRecordInfoDao(DaoConfig config) {
        super(config);
    }
    
    public IMChatRecordInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"im_msg_chat_record_info_new\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: rId
                "\"TITLE\" TEXT," + // 1: title
                "\"CONTENT\" TEXT," + // 2: content
                "\"RECEIVER_ID\" TEXT," + // 3: receiverId
                "\"MSG_IDS\" TEXT);"); // 4: msgIds
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"im_msg_chat_record_info_new\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IMChatRecordInfo entity) {
        stmt.clearBindings();
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(1, rId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String receiverId = entity.getReceiverId();
        if (receiverId != null) {
            stmt.bindString(4, receiverId);
        }
 
        ArrayList msgIds = entity.getMsgIds();
        if (msgIds != null) {
            stmt.bindString(5, msgIdsConverter.convertToDatabaseValue(msgIds));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IMChatRecordInfo entity) {
        stmt.clearBindings();
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(1, rId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
 
        String receiverId = entity.getReceiverId();
        if (receiverId != null) {
            stmt.bindString(4, receiverId);
        }
 
        ArrayList msgIds = entity.getMsgIds();
        if (msgIds != null) {
            stmt.bindString(5, msgIdsConverter.convertToDatabaseValue(msgIds));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IMChatRecordInfo readEntity(Cursor cursor, int offset) {
        IMChatRecordInfo entity = new IMChatRecordInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // rId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // content
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // receiverId
            cursor.isNull(offset + 4) ? null : msgIdsConverter.convertToEntityProperty(cursor.getString(offset + 4)) // msgIds
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IMChatRecordInfo entity, int offset) {
        entity.setRId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setReceiverId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMsgIds(cursor.isNull(offset + 4) ? null : msgIdsConverter.convertToEntityProperty(cursor.getString(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IMChatRecordInfo entity, long rowId) {
        entity.setRId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IMChatRecordInfo entity) {
        if(entity != null) {
            return entity.getRId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IMChatRecordInfo entity) {
        return entity.getRId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
