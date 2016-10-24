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

import co.edu.unicauca.appterapiademencia.domain.Recommendation;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RECOMMENDATION".
*/
public class RecommendationDao extends AbstractDao<Recommendation, Long> {

    public static final String TABLENAME = "RECOMMENDATION";

    /**
     * Properties of entity Recommendation.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatientId = new Property(1, long.class, "patientId", false, "PATIENT_ID");
        public final static Property ExerciseId = new Property(2, long.class, "exerciseId", false, "EXERCISE_ID");

    }

    private Query<Recommendation> patient_RecommendationListQuery;
    private Query<Recommendation> exercise_RecommendationListQuery;

    public RecommendationDao(DaoConfig config) {
        super(config);
    }
    
    public RecommendationDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RECOMMENDATION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PATIENT_ID\" INTEGER NOT NULL ," + // 1: patientId

                "\"EXERCISE_ID\" INTEGER NOT NULL );"); // 3: exerciseId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RECOMMENDATION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Recommendation entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
        stmt.bindLong(3, entity.getExerciseId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Recommendation entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
        stmt.bindLong(3, entity.getExerciseId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Recommendation readEntity(Cursor cursor, int offset) {
        Recommendation entity = new Recommendation( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // patientId
            cursor.getLong(offset + 2) // exerciseId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Recommendation entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatientId(cursor.getLong(offset + 1));
        entity.setExerciseId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Recommendation entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Recommendation entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Recommendation entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "recommendationList" to-many relationship of Patient. */
    public List<Recommendation> _queryPatient_RecommendationList(long patientId) {
        synchronized (this) {
            if (patient_RecommendationListQuery == null) {
                QueryBuilder<Recommendation> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PatientId.eq(null));
                patient_RecommendationListQuery = queryBuilder.build();
            }
        }
        Query<Recommendation> query = patient_RecommendationListQuery.forCurrentThread();
        query.setParameter(0, patientId);
        return query.list();
    }

    /** Internal query to resolve the "recommendationList" to-many relationship of Exercise. */
    public List<Recommendation> _queryExercise_RecommendationList(long exerciseId) {
        synchronized (this) {
            if (exercise_RecommendationListQuery == null) {
                QueryBuilder<Recommendation> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ExerciseId.eq(null));
                exercise_RecommendationListQuery = queryBuilder.build();
            }
        }
        Query<Recommendation> query = exercise_RecommendationListQuery.forCurrentThread();
        query.setParameter(0, exerciseId);
        return query.list();
    }

}
