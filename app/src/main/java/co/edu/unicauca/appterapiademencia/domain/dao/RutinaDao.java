package co.edu.unicauca.appterapiademencia.domain.dao;

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

import co.edu.unicauca.appterapiademencia.domain.Rutina;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RUTINA".
*/
public class RutinaDao extends AbstractDao<Rutina, Long> {

    public static final String TABLENAME = "RUTINA";

    /**
     * Properties of entity Rutina.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatientId = new Property(1, long.class, "patientId", false, "PATIENT_ID");
        public final static Property State = new Property(2, Integer.class, "state", false, "STATE");
        public final static Property Startername = new Property(3, String.class, "startername", false, "STARTERNAME");
        public final static Property Datestart = new Property(4, String.class, "datestart", false, "DATESTART");
        public final static Property Mecinicial = new Property(5, Integer.class, "mecinicial", false, "MECINICIAL");
        public final static Property Mecinicialcomentario = new Property(6, String.class, "mecinicialcomentario", false, "MECINICIALCOMENTARIO");
        public final static Property Mecfinal = new Property(7, Integer.class, "mecfinal", false, "MECFINAL");
        public final static Property Mecfinalcomentario = new Property(8, String.class, "mecfinalcomentario", false, "MECFINALCOMENTARIO");
    }

    private DaoSession daoSession;

    private Query<Rutina> patient_RutinaListQuery;

    public RutinaDao(DaoConfig config) {
        super(config);
    }
    
    public RutinaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RUTINA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PATIENT_ID\" INTEGER NOT NULL ," + // 1: patientId
                "\"STATE\" INTEGER," + // 2: state
                "\"STARTERNAME\" TEXT," + // 3: startername
                "\"DATESTART\" TEXT," + // 4: datestart
                "\"MECINICIAL\" INTEGER," + // 5: mecinicial
                "\"MECINICIALCOMENTARIO\" TEXT," + // 6: mecinicialcomentario
                "\"MECFINAL\" INTEGER," + // 7: mecfinal
                "\"MECFINALCOMENTARIO\" TEXT);"); // 8: mecfinalcomentario
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RUTINA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Rutina entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(3, state);
        }
 
        String startername = entity.getStartername();
        if (startername != null) {
            stmt.bindString(4, startername);
        }
 
        String datestart = entity.getDatestart();
        if (datestart != null) {
            stmt.bindString(5, datestart);
        }
 
        Integer mecinicial = entity.getMecinicial();
        if (mecinicial != null) {
            stmt.bindLong(6, mecinicial);
        }
 
        String mecinicialcomentario = entity.getMecinicialcomentario();
        if (mecinicialcomentario != null) {
            stmt.bindString(7, mecinicialcomentario);
        }
 
        Integer mecfinal = entity.getMecfinal();
        if (mecfinal != null) {
            stmt.bindLong(8, mecfinal);
        }
 
        String mecfinalcomentario = entity.getMecfinalcomentario();
        if (mecfinalcomentario != null) {
            stmt.bindString(9, mecfinalcomentario);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Rutina entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(3, state);
        }
 
        String startername = entity.getStartername();
        if (startername != null) {
            stmt.bindString(4, startername);
        }
 
        String datestart = entity.getDatestart();
        if (datestart != null) {
            stmt.bindString(5, datestart);
        }
 
        Integer mecinicial = entity.getMecinicial();
        if (mecinicial != null) {
            stmt.bindLong(6, mecinicial);
        }
 
        String mecinicialcomentario = entity.getMecinicialcomentario();
        if (mecinicialcomentario != null) {
            stmt.bindString(7, mecinicialcomentario);
        }
 
        Integer mecfinal = entity.getMecfinal();
        if (mecfinal != null) {
            stmt.bindLong(8, mecfinal);
        }
 
        String mecfinalcomentario = entity.getMecfinalcomentario();
        if (mecfinalcomentario != null) {
            stmt.bindString(9, mecfinalcomentario);
        }
    }

    @Override
    protected final void attachEntity(Rutina entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Rutina readEntity(Cursor cursor, int offset) {
        Rutina entity = new Rutina( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // patientId
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // state
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // startername
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // datestart
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // mecinicial
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // mecinicialcomentario
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // mecfinal
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // mecfinalcomentario
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Rutina entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatientId(cursor.getLong(offset + 1));
        entity.setState(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setStartername(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDatestart(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMecinicial(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setMecinicialcomentario(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMecfinal(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setMecfinalcomentario(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Rutina entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Rutina entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Rutina entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "rutinaList" to-many relationship of Patient. */
    public List<Rutina> _queryPatient_RutinaList(long patientId) {
        synchronized (this) {
            if (patient_RutinaListQuery == null) {
                QueryBuilder<Rutina> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PatientId.eq(null));
                patient_RutinaListQuery = queryBuilder.build();
            }
        }
        Query<Rutina> query = patient_RutinaListQuery.forCurrentThread();
        query.setParameter(0, patientId);
        return query.list();
    }

}
