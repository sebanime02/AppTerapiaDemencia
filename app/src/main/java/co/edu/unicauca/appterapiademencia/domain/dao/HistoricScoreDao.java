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

import co.edu.unicauca.appterapiademencia.domain.HistoricScore;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HISTORIC_SCORE".
*/
public class HistoricScoreDao extends AbstractDao<HistoricScore, Long> {

    public static final String TABLENAME = "HISTORIC_SCORE";

    /**
     * Properties of entity HistoricScore.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatientId = new Property(1, long.class, "patientId", false, "PATIENT_ID");
        public final static Property Scale = new Property(2, String.class, "scale", false, "SCALE");
        public final static Property Value = new Property(3, Double.class, "value", false, "VALUE");
        public final static Property Year = new Property(4, Integer.class, "year", false, "YEAR");
        public final static Property Month = new Property(5, Integer.class, "month", false, "MONTH");
        public final static Property Day = new Property(6, Integer.class, "day", false, "DAY");
    }

    private Query<HistoricScore> patient_HistoricScoreListQuery;

    public HistoricScoreDao(DaoConfig config) {
        super(config);
    }
    
    public HistoricScoreDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HISTORIC_SCORE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PATIENT_ID\" INTEGER NOT NULL ," + // 1: patientId
                "\"SCALE\" TEXT," + // 2: scale
                "\"VALUE\" REAL," + // 3: value
                "\"YEAR\" INTEGER," + // 4: year
                "\"MONTH\" INTEGER," + // 5: month
                "\"DAY\" INTEGER);"); // 6: day
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HISTORIC_SCORE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HistoricScore entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        String scale = entity.getScale();
        if (scale != null) {
            stmt.bindString(3, scale);
        }
 
        Double value = entity.getValue();
        if (value != null) {
            stmt.bindDouble(4, value);
        }
 
        Integer year = entity.getYear();
        if (year != null) {
            stmt.bindLong(5, year);
        }
 
        Integer month = entity.getMonth();
        if (month != null) {
            stmt.bindLong(6, month);
        }
 
        Integer day = entity.getDay();
        if (day != null) {
            stmt.bindLong(7, day);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HistoricScore entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        String scale = entity.getScale();
        if (scale != null) {
            stmt.bindString(3, scale);
        }
 
        Double value = entity.getValue();
        if (value != null) {
            stmt.bindDouble(4, value);
        }
 
        Integer year = entity.getYear();
        if (year != null) {
            stmt.bindLong(5, year);
        }
 
        Integer month = entity.getMonth();
        if (month != null) {
            stmt.bindLong(6, month);
        }
 
        Integer day = entity.getDay();
        if (day != null) {
            stmt.bindLong(7, day);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HistoricScore readEntity(Cursor cursor, int offset) {
        HistoricScore entity = new HistoricScore( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // patientId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // scale
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // value
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // year
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // month
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // day
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HistoricScore entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatientId(cursor.getLong(offset + 1));
        entity.setScale(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setValue(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setYear(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setMonth(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setDay(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HistoricScore entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HistoricScore entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HistoricScore entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "historicScoreList" to-many relationship of Patient. */
    public List<HistoricScore> _queryPatient_HistoricScoreList(long patientId) {
        synchronized (this) {
            if (patient_HistoricScoreListQuery == null) {
                QueryBuilder<HistoricScore> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PatientId.eq(null));
                patient_HistoricScoreListQuery = queryBuilder.build();
            }
        }
        Query<HistoricScore> query = patient_HistoricScoreListQuery.forCurrentThread();
        query.setParameter(0, patientId);
        return query.list();
    }

}
