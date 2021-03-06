package com.xsimple.im.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xsimple.im.db.datatable.IMGroupRemark;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "im_msg_group_remark_new".
*/
public class IMGroupRemarkDao extends AbstractDao<IMGroupRemark, Long> {

    public static final String TABLENAME = "im_msg_group_remark_new";

    /**
     * Properties of entity IMGroupRemark.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property RId = new Property(0, Long.class, "rId", true, "_id");
        public final static Property GroupId = new Property(1, String.class, "groupId", false, "GROUP_ID");
        public final static Property GroupName = new Property(2, String.class, "groupName", false, "GROUP_NAME");
        public final static Property UId = new Property(3, String.class, "uId", false, "U_ID");
        public final static Property Id = new Property(4, String.class, "id", false, "ID");
        public final static Property UserId = new Property(5, String.class, "userId", false, "USER_ID");
        public final static Property CreateName = new Property(6, String.class, "createName", false, "CREATE_NAME");
        public final static Property Title = new Property(7, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(8, String.class, "content", false, "CONTENT");
        public final static Property CreateDatetime = new Property(9, String.class, "createDatetime", false, "CREATE_DATETIME");
        public final static Property SendTime = new Property(10, long.class, "sendTime", false, "SEND_TIME");
        public final static Property Read = new Property(11, boolean.class, "read", false, "READ");
    }


    public IMGroupRemarkDao(DaoConfig config) {
        super(config);
    }
    
    public IMGroupRemarkDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"im_msg_group_remark_new\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: rId
                "\"GROUP_ID\" TEXT," + // 1: groupId
                "\"GROUP_NAME\" TEXT," + // 2: groupName
                "\"U_ID\" TEXT," + // 3: uId
                "\"ID\" TEXT," + // 4: id
                "\"USER_ID\" TEXT," + // 5: userId
                "\"CREATE_NAME\" TEXT," + // 6: createName
                "\"TITLE\" TEXT," + // 7: title
                "\"CONTENT\" TEXT," + // 8: content
                "\"CREATE_DATETIME\" TEXT," + // 9: createDatetime
                "\"SEND_TIME\" INTEGER NOT NULL ," + // 10: sendTime
                "\"READ\" INTEGER NOT NULL );"); // 11: read
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"im_msg_group_remark_new\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IMGroupRemark entity) {
        stmt.clearBindings();
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(1, rId);
        }
 
        String groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindString(2, groupId);
        }
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(3, groupName);
        }
 
        String uId = entity.getUId();
        if (uId != null) {
            stmt.bindString(4, uId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(6, userId);
        }
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(7, createName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
 
        String createDatetime = entity.getCreateDatetime();
        if (createDatetime != null) {
            stmt.bindString(10, createDatetime);
        }
        stmt.bindLong(11, entity.getSendTime());
        stmt.bindLong(12, entity.getRead() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IMGroupRemark entity) {
        stmt.clearBindings();
 
        Long rId = entity.getRId();
        if (rId != null) {
            stmt.bindLong(1, rId);
        }
 
        String groupId = entity.getGroupId();
        if (groupId != null) {
            stmt.bindString(2, groupId);
        }
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(3, groupName);
        }
 
        String uId = entity.getUId();
        if (uId != null) {
            stmt.bindString(4, uId);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(5, id);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(6, userId);
        }
 
        String createName = entity.getCreateName();
        if (createName != null) {
            stmt.bindString(7, createName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(8, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(9, content);
        }
 
        String createDatetime = entity.getCreateDatetime();
        if (createDatetime != null) {
            stmt.bindString(10, createDatetime);
        }
        stmt.bindLong(11, entity.getSendTime());
        stmt.bindLong(12, entity.getRead() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IMGroupRemark readEntity(Cursor cursor, int offset) {
        IMGroupRemark entity = new IMGroupRemark( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // rId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // groupId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // groupName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // uId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // id
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // createName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // title
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // content
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // createDatetime
            cursor.getLong(offset + 10), // sendTime
            cursor.getShort(offset + 11) != 0 // read
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IMGroupRemark entity, int offset) {
        entity.setRId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGroupId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setGroupName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCreateName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTitle(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setContent(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCreateDatetime(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSendTime(cursor.getLong(offset + 10));
        entity.setRead(cursor.getShort(offset + 11) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IMGroupRemark entity, long rowId) {
        entity.setRId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IMGroupRemark entity) {
        if(entity != null) {
            return entity.getRId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IMGroupRemark entity) {
        return entity.getRId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
