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
        public final static Property Sex = new Property(2, Boolean.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(3, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Photopath = new Property(4, String.class, "photopath", false, "PHOTOPATH");
        public final static Property Eps = new Property(5, String.class, "eps", false, "EPS");
        public final static Property Identity = new Property(6, long.class, "identity", false, "IDENTITY");
        public final static Property Antecedents = new Property(7, String.class, "antecedents", false, "ANTECEDENTS");
        public final static Property Syndromes = new Property(8, String.class, "syndromes", false, "SYNDROMES");
        public final static Property Observations = new Property(9, String.class, "observations", false, "OBSERVATIONS");
        public final static Property Mec = new Property(10, Integer.class, "mec", false, "MEC");
        public final static Property Gds = new Property(11, Integer.class, "gds", false, "GDS");
        public final static Property Visionlimitation = new Property(12, Integer.class, "visionlimitation", false, "VISIONLIMITATION");
        public final static Property Writinglimitation = new Property(13, Integer.class, "writinglimitation", false, "WRITINGLIMITATION");
        public final static Property Drawinglimitation = new Property(14, Integer.class, "drawinglimitation", false, "DRAWINGLIMITATION");
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
                "\"SEX\" INTEGER," + // 2: sex
                "\"BIRTHDAY\" TEXT NOT NULL ," + // 3: birthday
                "\"PHOTOPATH\" TEXT," + // 4: photopath
                "\"EPS\" TEXT," + // 5: eps
                "\"IDENTITY\" INTEGER NOT NULL UNIQUE ," + // 6: identity
                "\"ANTECEDENTS\" TEXT," + // 7: antecedents
                "\"SYNDROMES\" TEXT," + // 8: syndromes
                "\"OBSERVATIONS\" TEXT," + // 9: observations
                "\"MEC\" INTEGER," + // 10: mec
                "\"GDS\" INTEGER," + // 11: gds
                "\"VISIONLIMITATION\" INTEGER," + // 12: visionlimitation
                "\"WRITINGLIMITATION\" INTEGER," + // 13: writinglimitation
                "\"DRAWINGLIMITATION\" INTEGER);"); // 14: drawinglimitation
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
 
        Boolean sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(3, sex ? 1L: 0L);
        }
        stmt.bindString(4, entity.getBirthday());
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(5, photopath);
        }
 
        String eps = entity.getEps();
        if (eps != null) {
            stmt.bindString(6, eps);
        }
        stmt.bindLong(7, entity.getIdentity());
 
        String antecedents = entity.getAntecedents();
        if (antecedents != null) {
            stmt.bindString(8, antecedents);
        }
 
        String syndromes = entity.getSyndromes();
        if (syndromes != null) {
            stmt.bindString(9, syndromes);
        }
 
        String observations = entity.getObservations();
        if (observations != null) {
            stmt.bindString(10, observations);
        }
 
        Integer mec = entity.getMec();
        if (mec != null) {
            stmt.bindLong(11, mec);
        }
 
        Integer gds = entity.getGds();
        if (gds != null) {
            stmt.bindLong(12, gds);
        }
 
        Integer visionlimitation = entity.getVisionlimitation();
        if (visionlimitation != null) {
            stmt.bindLong(13, visionlimitation);
        }
 
        Integer writinglimitation = entity.getWritinglimitation();
        if (writinglimitation != null) {
            stmt.bindLong(14, writinglimitation);
        }
 
        Integer drawinglimitation = entity.getDrawinglimitation();
        if (drawinglimitation != null) {
            stmt.bindLong(15, drawinglimitation);
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
 
        Boolean sex = entity.getSex();
        if (sex != null) {
            stmt.bindLong(3, sex ? 1L: 0L);
        }
        stmt.bindString(4, entity.getBirthday());
 
        String photopath = entity.getPhotopath();
        if (photopath != null) {
            stmt.bindString(5, photopath);
        }
 
        String eps = entity.getEps();
        if (eps != null) {
            stmt.bindString(6, eps);
        }
        stmt.bindLong(7, entity.getIdentity());
 
        String antecedents = entity.getAntecedents();
        if (antecedents != null) {
            stmt.bindString(8, antecedents);
        }
 
        String syndromes = entity.getSyndromes();
        if (syndromes != null) {
            stmt.bindString(9, syndromes);
        }
 
        String observations = entity.getObservations();
        if (observations != null) {
            stmt.bindString(10, observations);
        }
 
        Integer mec = entity.getMec();
        if (mec != null) {
            stmt.bindLong(11, mec);
        }
 
        Integer gds = entity.getGds();
        if (gds != null) {
            stmt.bindLong(12, gds);
        }
 
        Integer visionlimitation = entity.getVisionlimitation();
        if (visionlimitation != null) {
            stmt.bindLong(13, visionlimitation);
        }
 
        Integer writinglimitation = entity.getWritinglimitation();
        if (writinglimitation != null) {
            stmt.bindLong(14, writinglimitation);
        }
 
        Integer drawinglimitation = entity.getDrawinglimitation();
        if (drawinglimitation != null) {
            stmt.bindLong(15, drawinglimitation);
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
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // sex
            cursor.getString(offset + 3), // birthday
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // photopath
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // eps
            cursor.getLong(offset + 6), // identity
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // antecedents
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // syndromes
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // observations
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // mec
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // gds
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // visionlimitation
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // writinglimitation
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14) // drawinglimitation
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Patient entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setBirthday(cursor.getString(offset + 3));
        entity.setPhotopath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEps(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIdentity(cursor.getLong(offset + 6));
        entity.setAntecedents(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSyndromes(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setObservations(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setMec(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setGds(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setVisionlimitation(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setWritinglimitation(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setDrawinglimitation(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
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
