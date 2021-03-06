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

import com.xsimple.im.db.datatable.IMGroup;

import com.xsimple.im.db.datatable.IMGroupUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "im_group_user_new".
*/
public class IMGroupUserDao extends AbstractDao<IMGroupUser, Long> {

    public static final String TABLENAME = "im_group_user_new";

    /**
     * Properties of entity IMGroupUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GId = new Property(1, String.class, "gId", false, "G_ID");
        public final static Property Job = new Property(2, int.class, "job", false, "JOB");
        public final static Property JoinTime = new Property(3, long.class, "joinTime", false, "JOIN_TIME");
        public final static Property ImageAddress = new Property(4, String.class, "imageAddress", false, "IMAGE_ADDRESS");
        public final static Property Initial = new Property(5, String.class, "initial", false, "INITIAL");
        public final static Property Pinying = new Property(6, String.class, "pinying", false, "PINYING");
        public final static Property UserId = new Property(7, String.class, "userId", false, "USER_ID");
        public final static Property UserName = new Property(8, String.class, "userName", false, "USER_NAME");
    }

    private DaoSession daoSession;

    private Query<IMGroupUser> iMGroup_GroupUsersQuery;

    public IMGroupUserDao(DaoConfig config) {
        super(config);
    }
    
    public IMGroupUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"im_group_user_new\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"G_ID\" TEXT," + // 1: gId
                "\"JOB\" INTEGER NOT NULL ," + // 2: job
                "\"JOIN_TIME\" INTEGER NOT NULL ," + // 3: joinTime
                "\"IMAGE_ADDRESS\" TEXT," + // 4: imageAddress
                "\"INITIAL\" TEXT," + // 5: initial
                "\"PINYING\" TEXT," + // 6: pinying
                "\"USER_ID\" TEXT," + // 7: userId
                "\"USER_NAME\" TEXT);"); // 8: userName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"im_group_user_new\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IMGroupUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String gId = entity.getGId();
        if (gId != null) {
            stmt.bindString(2, gId);
        }
        stmt.bindLong(3, entity.getJob());
        stmt.bindLong(4, entity.getJoinTime());
 
        String imageAddress = entity.getImageAddress();
        if (imageAddress != null) {
            stmt.bindString(5, imageAddress);
        }
 
        String initial = entity.getInitial();
        if (initial != null) {
            stmt.bindString(6, initial);
        }
 
        String pinying = entity.getPinying();
        if (pinying != null) {
            stmt.bindString(7, pinying);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(8, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(9, userName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IMGroupUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String gId = entity.getGId();
        if (gId != null) {
            stmt.bindString(2, gId);
        }
        stmt.bindLong(3, entity.getJob());
        stmt.bindLong(4, entity.getJoinTime());
 
        String imageAddress = entity.getImageAddress();
        if (imageAddress != null) {
            stmt.bindString(5, imageAddress);
        }
 
        String initial = entity.getInitial();
        if (initial != null) {
            stmt.bindString(6, initial);
        }
 
        String pinying = entity.getPinying();
        if (pinying != null) {
            stmt.bindString(7, pinying);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(8, userId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(9, userName);
        }
    }

    @Override
    protected final void attachEntity(IMGroupUser entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IMGroupUser readEntity(Cursor cursor, int offset) {
        IMGroupUser entity = new IMGroupUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // gId
            cursor.getInt(offset + 2), // job
            cursor.getLong(offset + 3), // joinTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // imageAddress
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // initial
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // pinying
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // userId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // userName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IMGroupUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setJob(cursor.getInt(offset + 2));
        entity.setJoinTime(cursor.getLong(offset + 3));
        entity.setImageAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInitial(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPinying(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUserName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IMGroupUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IMGroupUser entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IMGroupUser entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "groupUsers" to-many relationship of IMGroup. */
    public List<IMGroupUser> _queryIMGroup_GroupUsers(String gId) {
        synchronized (this) {
            if (iMGroup_GroupUsersQuery == null) {
                QueryBuilder<IMGroupUser> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.GId.eq(null));
                iMGroup_GroupUsersQuery = queryBuilder.build();
            }
        }
        Query<IMGroupUser> query = iMGroup_GroupUsersQuery.forCurrentThread();
        query.setParameter(0, gId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getIMGroupDao().getAllColumns());
            builder.append(" FROM im_group_user_new T");
            builder.append(" LEFT JOIN im_group_new T0 ON T.\"G_ID\"=T0.\"ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected IMGroupUser loadCurrentDeep(Cursor cursor, boolean lock) {
        IMGroupUser entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        IMGroup group = loadCurrentOther(daoSession.getIMGroupDao(), cursor, offset);
        entity.setGroup(group);

        return entity;    
    }

    public IMGroupUser loadDeep(Long key) {
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
    public List<IMGroupUser> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<IMGroupUser> list = new ArrayList<IMGroupUser>(count);
        
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
    
    protected List<IMGroupUser> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<IMGroupUser> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
