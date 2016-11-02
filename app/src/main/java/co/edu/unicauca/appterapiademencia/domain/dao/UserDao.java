package co.edu.unicauca.appterapiademencia.domain.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import co.edu.unicauca.appterapiademencia.domain.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property CompleteName = new Property(3, String.class, "completeName", false, "COMPLETE_NAME");
        public final static Property AccessType = new Property(4, Boolean.class, "accessType", false, "ACCESS_TYPE");
        public final static Property Photopath = new Property(5, String.class, "photopath", false, "PHOTOPATH");
    }

    private DaoSession daoSession;


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERNAME\" TEXT NOT NULL UNIQUE ," + // 1: username
                "\"PASSWORD\" TEXT NOT NULL ," + // 2: password
                "\"COMPLETE_NAME\" TEXT NOT NULL ," + // 3: completeName
                "\"ACCESS_TYPE\" INTEGER," + // 4: accessType
                "\"PHOTOPATH\" TEXT);"); // 5: photopath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUsername());
        stmt.bindString(3, entity.getPassword());
        stmt.bindString(4, entity.getCompleteName());
 
        Boolean accessType = entity.getAccessType();
        if (accessType != null) {
            stmt.bindLong(5, accessType ? 1L: 0L);
        }
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(6, photopath);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUsername());
        stmt.bindString(3, entity.getPassword());
        stmt.bindString(4, entity.getCompleteName());
 
        Boolean accessType = entity.getAccessType();
        if (accessType != null) {
            stmt.bindLong(5, accessType ? 1L: 0L);
        }
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(6, photopath);
        }
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // username
            cursor.getString(offset + 2), // password
            cursor.getString(offset + 3), // completeName
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // accessType
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // photopath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.getString(offset + 1));
        entity.setPassword(cursor.getString(offset + 2));
        entity.setCompleteName(cursor.getString(offset + 3));
        entity.setAccessType(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setPhotopath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
