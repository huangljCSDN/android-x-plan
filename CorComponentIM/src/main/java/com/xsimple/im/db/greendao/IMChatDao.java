package com.xsimple.im.db.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.xsimple.im.db.datatable.IMChat;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "im_chat_new".
*/
public class IMChatDao extends AbstractDao<IMChat, Long> {

    public static final String TABLENAME = "im_chat_new";

    /**
     * Properties of entity IMChat.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UId = new Property(1, String.class, "uId", false, "U_ID");
        public final static Property SenderOrTarget1 = new Property(2, String.class, "senderOrTarget1", false, "SENDER_OR_TARGET1");
        public final static Property ReceiverName = new Property(3, String.class, "receiverName", false, "RECEIVER_NAME");
        public final static Property SenderOrTarget2 = new Property(4, String.class, "senderOrTarget2", false, "SENDER_OR_TARGET2");
        public final static Property ChatId = new Property(5, String.class, "chatId", false, "CHAT_ID");
        public final static Property DataId = new Property(6, String.class, "dataId", false, "DATA_ID");
        public final static Property Type = new Property(7, int.class, "type", false, "TYPE");
        public final static Property Name = new Property(8, String.class, "name", false, "NAME");
        public final static Property UnReadCount = new Property(9, int.class, "UnReadCount", false, "UN_READ_COUNT");
        public final static Property Time = new Property(10, Long.class, "time", false, "TIME");
        public final static Property IsStick = new Property(11, boolean.class, "isStick", false, "IS_STICK");
        public final static Property IsNotDisturb = new Property(12, boolean.class, "isNotDisturb", false, "IS_NOT_DISTURB");
        public final static Property Drafts = new Property(13, String.class, "drafts", false, "DRAFTS");
        public final static Property RefreshTime = new Property(14, long.class, "refreshTime", false, "REFRESH_TIME");
        public final static Property FunKey = new Property(15, String.class, "funKey", false, "FUN_KEY");
    }

    private DaoSession daoSession;

    private Query<IMChat> iMUser_IMChatsQuery;

    public IMChatDao(DaoConfig config) {
        super(config);
    }
    
    public IMChatDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"im_chat_new\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"U_ID\" TEXT," + // 1: uId
                "\"SENDER_OR_TARGET1\" TEXT," + // 2: senderOrTarget1
                "\"RECEIVER_NAME\" TEXT," + // 3: receiverName
                "\"SENDER_OR_TARGET2\" TEXT," + // 4: senderOrTarget2
                "\"CHAT_ID\" TEXT," + // 5: chatId
                "\"DATA_ID\" TEXT," + // 6: dataId
                "\"TYPE\" INTEGER NOT NULL ," + // 7: type
                "\"NAME\" TEXT," + // 8: name
                "\"UN_READ_COUNT\" INTEGER NOT NULL ," + // 9: UnReadCount
                "\"TIME\" INTEGER," + // 10: time
                "\"IS_STICK\" INTEGER NOT NULL ," + // 11: isStick
                "\"IS_NOT_DISTURB\" INTEGER NOT NULL ," + // 12: isNotDisturb
                "\"DRAFTS\" TEXT," + // 13: drafts
                "\"REFRESH_TIME\" INTEGER NOT NULL ," + // 14: refreshTime
                "\"FUN_KEY\" TEXT);"); // 15: funKey
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"im_chat_new\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, IMChat entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String uId = entity.getUId();
        if (uId != null) {
            stmt.bindString(2, uId);
        }
 
        String senderOrTarget1 = entity.getSenderOrTarget1();
        if (senderOrTarget1 != null) {
            stmt.bindString(3, senderOrTarget1);
        }
 
        String receiverName = entity.getReceiverName();
        if (receiverName != null) {
            stmt.bindString(4, receiverName);
        }
 
        String senderOrTarget2 = entity.getSenderOrTarget2();
        if (senderOrTarget2 != null) {
            stmt.bindString(5, senderOrTarget2);
        }
 
        String chatId = entity.getChatId();
        if (chatId != null) {
            stmt.bindString(6, chatId);
        }
 
        String dataId = entity.getDataId();
        if (dataId != null) {
            stmt.bindString(7, dataId);
        }
        stmt.bindLong(8, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(9, name);
        }
        stmt.bindLong(10, entity.getUnReadCount());
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(11, time);
        }
        stmt.bindLong(12, entity.getIsStick() ? 1L: 0L);
        stmt.bindLong(13, entity.getIsNotDisturb() ? 1L: 0L);
 
        String drafts = entity.getDrafts();
        if (drafts != null) {
            stmt.bindString(14, drafts);
        }
        stmt.bindLong(15, entity.getRefreshTime());
 
        String funKey = entity.getFunKey();
        if (funKey != null) {
            stmt.bindString(16, funKey);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, IMChat entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String uId = entity.getUId();
        if (uId != null) {
            stmt.bindString(2, uId);
        }
 
        String senderOrTarget1 = entity.getSenderOrTarget1();
        if (senderOrTarget1 != null) {
            stmt.bindString(3, senderOrTarget1);
        }
 
        String receiverName = entity.getReceiverName();
        if (receiverName != null) {
            stmt.bindString(4, receiverName);
        }
 
        String senderOrTarget2 = entity.getSenderOrTarget2();
        if (senderOrTarget2 != null) {
            stmt.bindString(5, senderOrTarget2);
        }
 
        String chatId = entity.getChatId();
        if (chatId != null) {
            stmt.bindString(6, chatId);
        }
 
        String dataId = entity.getDataId();
        if (dataId != null) {
            stmt.bindString(7, dataId);
        }
        stmt.bindLong(8, entity.getType());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(9, name);
        }
        stmt.bindLong(10, entity.getUnReadCount());
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(11, time);
        }
        stmt.bindLong(12, entity.getIsStick() ? 1L: 0L);
        stmt.bindLong(13, entity.getIsNotDisturb() ? 1L: 0L);
 
        String drafts = entity.getDrafts();
        if (drafts != null) {
            stmt.bindString(14, drafts);
        }
        stmt.bindLong(15, entity.getRefreshTime());
 
        String funKey = entity.getFunKey();
        if (funKey != null) {
            stmt.bindString(16, funKey);
        }
    }

    @Override
    protected final void attachEntity(IMChat entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public IMChat readEntity(Cursor cursor, int offset) {
        IMChat entity = new IMChat( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // uId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // senderOrTarget1
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // receiverName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // senderOrTarget2
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // chatId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // dataId
            cursor.getInt(offset + 7), // type
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // name
            cursor.getInt(offset + 9), // UnReadCount
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // time
            cursor.getShort(offset + 11) != 0, // isStick
            cursor.getShort(offset + 12) != 0, // isNotDisturb
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // drafts
            cursor.getLong(offset + 14), // refreshTime
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // funKey
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, IMChat entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSenderOrTarget1(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setReceiverName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSenderOrTarget2(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setChatId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDataId(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setType(cursor.getInt(offset + 7));
        entity.setName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUnReadCount(cursor.getInt(offset + 9));
        entity.setTime(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setIsStick(cursor.getShort(offset + 11) != 0);
        entity.setIsNotDisturb(cursor.getShort(offset + 12) != 0);
        entity.setDrafts(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setRefreshTime(cursor.getLong(offset + 14));
        entity.setFunKey(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(IMChat entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(IMChat entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(IMChat entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "IMChats" to-many relationship of IMUser. */
    public List<IMChat> _queryIMUser_IMChats(String uId) {
        synchronized (this) {
            if (iMUser_IMChatsQuery == null) {
                QueryBuilder<IMChat> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UId.eq(null));
                iMUser_IMChatsQuery = queryBuilder.build();
            }
        }
        Query<IMChat> query = iMUser_IMChatsQuery.forCurrentThread();
        query.setParameter(0, uId);
        return query.list();
    }

}
