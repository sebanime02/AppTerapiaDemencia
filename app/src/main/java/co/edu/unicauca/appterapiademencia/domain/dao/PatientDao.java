package co.edu.unicauca.appterapiademencia.domain.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import co.edu.unicauca.appterapiademencia.domain.Patient;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PATIENT".
*/
public class PatientDao extends AbstractDao<Patient, Long> {

    public static final String TABLENAME = "PATIENT";

    /**
     * Properties of entity Patient.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Birthday = new Property(2, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Photopath = new Property(3, String.class, "photopath", false, "PHOTOPATH");
        public final static Property Eps = new Property(4, String.class, "eps", false, "EPS");
        public final static Property Identity = new Property(5, int.class, "identity", false, "IDENTITY");
        public final static Property Antecedents = new Property(6, String.class, "antecedents", false, "ANTECEDENTS");
        public final static Property Syndromes = new Property(7, String.class, "syndromes", false, "SYNDROMES");
        public final static Property Observations = new Property(8, String.class, "observations", false, "OBSERVATIONS");
        public final static Property Mec = new Property(9, Integer.class, "mec", false, "MEC");
        public final static Property Gds = new Property(10, Integer.class, "gds", false, "GDS");
        public final static Property Visionlimitation = new Property(11, Integer.class, "visionlimitation", false, "VISIONLIMITATION");
        public final static Property Writinglimitation = new Property(12, Integer.class, "writinglimitation", false, "WRITINGLIMITATION");
        public final static Property Drawinglimitation = new Property(13, Integer.class, "drawinglimitation", false, "DRAWINGLIMITATION");
    }

    private DaoSession daoSession;


    public PatientDao(DaoConfig config) {
        super(config);
    }
    
    public PatientDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PATIENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"BIRTHDAY\" TEXT NOT NULL ," + // 2: birthday
                "\"PHOTOPATH\" TEXT," + // 3: photopath
                "\"EPS\" TEXT," + // 4: eps
                "\"IDENTITY\" INTEGER NOT NULL UNIQUE ," + // 5: identity
                "\"ANTECEDENTS\" TEXT," + // 6: antecedents
                "\"SYNDROMES\" TEXT," + // 7: syndromes
                "\"OBSERVATIONS\" TEXT," + // 8: observations
                "\"MEC\" INTEGER," + // 9: mec
                "\"GDS\" INTEGER," + // 10: gds
                "\"VISIONLIMITATION\" INTEGER," + // 11: visionlimitation
                "\"WRITINGLIMITATION\" INTEGER," + // 12: writinglimitation
                "\"DRAWINGLIMITATION\" INTEGER);"); // 13: drawinglimitation
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PATIENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Patient entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getBirthday());
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(4, photopath);
        }
 
        String eps = entity.getEps();
        if (eps != null) {
            stmt.bindString(5, eps);
        }
        stmt.bindLong(6, entity.getIdentity());
 
        String antecedents = entity.getAntecedents();
        if (antecedents != null) {
            stmt.bindString(7, antecedents);
        }
 
        String syndromes = entity.getSyndromes();
        if (syndromes != null) {
            stmt.bindString(8, syndromes);
        }
 
        String observations = entity.getObservations();
        if (observations != null) {
            stmt.bindString(9, observations);
        }
 
        Integer mec = entity.getMec();
        if (mec != null) {
            stmt.bindLong(10, mec);
        }
 
        Integer gds = entity.getGds();
        if (gds != null) {
            stmt.bindLong(11, gds);
        }
 
        Integer visionlimitation = entity.getVisionlimitation();
        if (visionlimitation != null) {
            stmt.bindLong(12, visionlimitation);
        }
 
        Integer writinglimitation = entity.getWritinglimitation();
        if (writinglimitation != null) {
            stmt.bindLong(13, writinglimitation);
        }
 
        Integer drawinglimitation = entity.getDrawinglimitation();
        if (drawinglimitation != null) {
            stmt.bindLong(14, drawinglimitation);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Patient entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getBirthday());
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(4, photopath);
        }
 
        String eps = entity.getEps();
        if (eps != null) {
            stmt.bindString(5, eps);
        }
        stmt.bindLong(6, entity.getIdentity());
 
        String antecedents = entity.getAntecedents();
        if (antecedents != null) {
            stmt.bindString(7, antecedents);
        }
 
        String syndromes = entity.getSyndromes();
        if (syndromes != null) {
            stmt.bindString(8, syndromes);
        }
 
        String observations = entity.getObservations();
        if (observations != null) {
            stmt.bindString(9, observations);
        }
 
        Integer mec = entity.getMec();
        if (mec != null) {
            stmt.bindLong(10, mec);
        }
 
        Integer gds = entity.getGds();
        if (gds != null) {
            stmt.bindLong(11, gds);
        }
 
        Integer visionlimitation = entity.getVisionlimitation();
        if (visionlimitation != null) {
            stmt.bindLong(12, visionlimitation);
        }
 
        Integer writinglimitation = entity.getWritinglimitation();
        if (writinglimitation != null) {
            stmt.bindLong(13, writinglimitation);
        }
 
        Integer drawinglimitation = entity.getDrawinglimitation();
        if (drawinglimitation != null) {
            stmt.bindLong(14, drawinglimitation);
        }
    }

    @Override
    protected final void attachEntity(Patient entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Patient readEntity(Cursor cursor, int offset) {
        Patient entity = new Patient( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // birthday
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // photopath
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // eps
            cursor.getInt(offset + 5), // identity
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // antecedents
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // syndromes
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // observations
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // mec
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // gds
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // visionlimitation
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // writinglimitation
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13) // drawinglimitation
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Patient entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setBirthday(cursor.getString(offset + 2));
        entity.setPhotopath(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEps(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIdentity(cursor.getInt(offset + 5));
        entity.setAntecedents(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSyndromes(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setObservations(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMec(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setGds(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setVisionlimitation(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setWritinglimitation(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setDrawinglimitation(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Patient entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Patient entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Patient entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
