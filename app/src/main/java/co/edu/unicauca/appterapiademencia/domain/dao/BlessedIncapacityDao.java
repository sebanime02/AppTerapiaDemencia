package co.edu.unicauca.appterapiademencia.domain.dao;

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

import co.edu.unicauca.appterapiademencia.domain.Patient;

import co.edu.unicauca.appterapiademencia.domain.BlessedIncapacity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BLESSED_INCAPACITY".
*/
public class BlessedIncapacityDao extends AbstractDao<BlessedIncapacity, Long> {

    public static final String TABLENAME = "BLESSED_INCAPACITY";

    /**
     * Properties of entity BlessedIncapacity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PatientId = new Property(1, long.class, "patientId", false, "PATIENT_ID");
        public final static Property Tareasdomesticas = new Property(2, Double.class, "tareasdomesticas", false, "TAREASDOMESTICAS");
        public final static Property Pequenasdinero = new Property(3, Double.class, "pequenasdinero", false, "PEQUENASDINERO");
        public final static Property Listascortas = new Property(4, Double.class, "listascortas", false, "LISTASCORTAS");
        public final static Property Orientarsecasa = new Property(5, Double.class, "orientarsecasa", false, "ORIENTARSECASA");
        public final static Property Orientarsecalle = new Property(6, Double.class, "orientarsecalle", false, "ORIENTARSECALLE");
        public final static Property Valorarentorno = new Property(7, Double.class, "valorarentorno", false, "VALORARENTORNO");
        public final static Property Recordarrecientes = new Property(8, Double.class, "recordarrecientes", false, "RECORDARRECIENTES");
        public final static Property Rememorarpasado = new Property(9, Double.class, "rememorarpasado", false, "REMEMORARPASADO");
        public final static Property Alimentacion = new Property(10, Integer.class, "alimentacion", false, "ALIMENTACION");
        public final static Property Vestimenta = new Property(11, Integer.class, "vestimenta", false, "VESTIMENTA");
        public final static Property Esfinteres = new Property(12, Integer.class, "esfinteres", false, "ESFINTERES");
        public final static Property Retraimientoconstante = new Property(13, Integer.class, "retraimientoconstante", false, "RETRAIMIENTOCONSTANTE");
        public final static Property Egocentrismoaumentado = new Property(14, Integer.class, "egocentrismoaumentado", false, "EGOCENTRISMOAUMENTADO");
        public final static Property Perdidainteressentimientos = new Property(15, Integer.class, "perdidainteressentimientos", false, "PERDIDAINTERESSENTIMIENTOS");
        public final static Property Afectividadembolatada = new Property(16, Integer.class, "afectividadembolatada", false, "AFECTIVIDADEMBOLATADA");
        public final static Property Perturbacioncontrolemocional = new Property(17, Integer.class, "perturbacioncontrolemocional", false, "PERTURBACIONCONTROLEMOCIONAL");
        public final static Property Hilaridadinapropiada = new Property(18, Integer.class, "hilaridadinapropiada", false, "HILARIDADINAPROPIADA");
        public final static Property Respuestaemocional = new Property(19, Integer.class, "respuestaemocional", false, "RESPUESTAEMOCIONAL");
        public final static Property Indiscrecionessexuales = new Property(20, Integer.class, "indiscrecionessexuales", false, "INDISCRECIONESSEXUALES");
        public final static Property Faltainteresaficiones = new Property(21, Integer.class, "faltainteresaficiones", false, "FALTAINTERESAFICIONES");
        public final static Property Disminucioniniciativaprogresiva = new Property(22, Integer.class, "disminucioniniciativaprogresiva", false, "DISMINUCIONINICIATIVAPROGRESIVA");
        public final static Property Hiperactividadnojustificada = new Property(23, Integer.class, "hiperactividadnojustificada", false, "HIPERACTIVIDADNOJUSTIFICADA");
    }

    private DaoSession daoSession;


    public BlessedIncapacityDao(DaoConfig config) {
        super(config);
    }
    
    public BlessedIncapacityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BLESSED_INCAPACITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PATIENT_ID\" INTEGER NOT NULL ," + // 1: patientId
                "\"TAREASDOMESTICAS\" REAL," + // 2: tareasdomesticas
                "\"PEQUENASDINERO\" REAL," + // 3: pequenasdinero
                "\"LISTASCORTAS\" REAL," + // 4: listascortas
                "\"ORIENTARSECASA\" REAL," + // 5: orientarsecasa
                "\"ORIENTARSECALLE\" REAL," + // 6: orientarsecalle
                "\"VALORARENTORNO\" REAL," + // 7: valorarentorno
                "\"RECORDARRECIENTES\" REAL," + // 8: recordarrecientes
                "\"REMEMORARPASADO\" REAL," + // 9: rememorarpasado
                "\"ALIMENTACION\" INTEGER," + // 10: alimentacion
                "\"VESTIMENTA\" INTEGER," + // 11: vestimenta
                "\"ESFINTERES\" INTEGER," + // 12: esfinteres
                "\"RETRAIMIENTOCONSTANTE\" INTEGER," + // 13: retraimientoconstante
                "\"EGOCENTRISMOAUMENTADO\" INTEGER," + // 14: egocentrismoaumentado
                "\"PERDIDAINTERESSENTIMIENTOS\" INTEGER," + // 15: perdidainteressentimientos
                "\"AFECTIVIDADEMBOLATADA\" INTEGER," + // 16: afectividadembolatada
                "\"PERTURBACIONCONTROLEMOCIONAL\" INTEGER," + // 17: perturbacioncontrolemocional
                "\"HILARIDADINAPROPIADA\" INTEGER," + // 18: hilaridadinapropiada
                "\"RESPUESTAEMOCIONAL\" INTEGER," + // 19: respuestaemocional
                "\"INDISCRECIONESSEXUALES\" INTEGER," + // 20: indiscrecionessexuales
                "\"FALTAINTERESAFICIONES\" INTEGER," + // 21: faltainteresaficiones
                "\"DISMINUCIONINICIATIVAPROGRESIVA\" INTEGER," + // 22: disminucioniniciativaprogresiva
                "\"HIPERACTIVIDADNOJUSTIFICADA\" INTEGER);"); // 23: hiperactividadnojustificada
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BLESSED_INCAPACITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BlessedIncapacity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        Double tareasdomesticas = entity.getTareasdomesticas();
        if (tareasdomesticas != null) {
            stmt.bindDouble(3, tareasdomesticas);
        }
 
        Double pequenasdinero = entity.getPequenasdinero();
        if (pequenasdinero != null) {
            stmt.bindDouble(4, pequenasdinero);
        }
 
        Double listascortas = entity.getListascortas();
        if (listascortas != null) {
            stmt.bindDouble(5, listascortas);
        }
 
        Double orientarsecasa = entity.getOrientarsecasa();
        if (orientarsecasa != null) {
            stmt.bindDouble(6, orientarsecasa);
        }
 
        Double orientarsecalle = entity.getOrientarsecalle();
        if (orientarsecalle != null) {
            stmt.bindDouble(7, orientarsecalle);
        }
 
        Double valorarentorno = entity.getValorarentorno();
        if (valorarentorno != null) {
            stmt.bindDouble(8, valorarentorno);
        }
 
        Double recordarrecientes = entity.getRecordarrecientes();
        if (recordarrecientes != null) {
            stmt.bindDouble(9, recordarrecientes);
        }
 
        Double rememorarpasado = entity.getRememorarpasado();
        if (rememorarpasado != null) {
            stmt.bindDouble(10, rememorarpasado);
        }
 
        Integer alimentacion = entity.getAlimentacion();
        if (alimentacion != null) {
            stmt.bindLong(11, alimentacion);
        }
 
        Integer vestimenta = entity.getVestimenta();
        if (vestimenta != null) {
            stmt.bindLong(12, vestimenta);
        }
 
        Integer esfinteres = entity.getEsfinteres();
        if (esfinteres != null) {
            stmt.bindLong(13, esfinteres);
        }
 
        Integer retraimientoconstante = entity.getRetraimientoconstante();
        if (retraimientoconstante != null) {
            stmt.bindLong(14, retraimientoconstante);
        }
 
        Integer egocentrismoaumentado = entity.getEgocentrismoaumentado();
        if (egocentrismoaumentado != null) {
            stmt.bindLong(15, egocentrismoaumentado);
        }
 
        Integer perdidainteressentimientos = entity.getPerdidainteressentimientos();
        if (perdidainteressentimientos != null) {
            stmt.bindLong(16, perdidainteressentimientos);
        }
 
        Integer afectividadembolatada = entity.getAfectividadembolatada();
        if (afectividadembolatada != null) {
            stmt.bindLong(17, afectividadembolatada);
        }
 
        Integer perturbacioncontrolemocional = entity.getPerturbacioncontrolemocional();
        if (perturbacioncontrolemocional != null) {
            stmt.bindLong(18, perturbacioncontrolemocional);
        }
 
        Integer hilaridadinapropiada = entity.getHilaridadinapropiada();
        if (hilaridadinapropiada != null) {
            stmt.bindLong(19, hilaridadinapropiada);
        }
 
        Integer respuestaemocional = entity.getRespuestaemocional();
        if (respuestaemocional != null) {
            stmt.bindLong(20, respuestaemocional);
        }
 
        Integer indiscrecionessexuales = entity.getIndiscrecionessexuales();
        if (indiscrecionessexuales != null) {
            stmt.bindLong(21, indiscrecionessexuales);
        }
 
        Integer faltainteresaficiones = entity.getFaltainteresaficiones();
        if (faltainteresaficiones != null) {
            stmt.bindLong(22, faltainteresaficiones);
        }
 
        Integer disminucioniniciativaprogresiva = entity.getDisminucioniniciativaprogresiva();
        if (disminucioniniciativaprogresiva != null) {
            stmt.bindLong(23, disminucioniniciativaprogresiva);
        }
 
        Integer hiperactividadnojustificada = entity.getHiperactividadnojustificada();
        if (hiperactividadnojustificada != null) {
            stmt.bindLong(24, hiperactividadnojustificada);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BlessedIncapacity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPatientId());
 
        Double tareasdomesticas = entity.getTareasdomesticas();
        if (tareasdomesticas != null) {
            stmt.bindDouble(3, tareasdomesticas);
        }
 
        Double pequenasdinero = entity.getPequenasdinero();
        if (pequenasdinero != null) {
            stmt.bindDouble(4, pequenasdinero);
        }
 
        Double listascortas = entity.getListascortas();
        if (listascortas != null) {
            stmt.bindDouble(5, listascortas);
        }
 
        Double orientarsecasa = entity.getOrientarsecasa();
        if (orientarsecasa != null) {
            stmt.bindDouble(6, orientarsecasa);
        }
 
        Double orientarsecalle = entity.getOrientarsecalle();
        if (orientarsecalle != null) {
            stmt.bindDouble(7, orientarsecalle);
        }
 
        Double valorarentorno = entity.getValorarentorno();
        if (valorarentorno != null) {
            stmt.bindDouble(8, valorarentorno);
        }
 
        Double recordarrecientes = entity.getRecordarrecientes();
        if (recordarrecientes != null) {
            stmt.bindDouble(9, recordarrecientes);
        }
 
        Double rememorarpasado = entity.getRememorarpasado();
        if (rememorarpasado != null) {
            stmt.bindDouble(10, rememorarpasado);
        }
 
        Integer alimentacion = entity.getAlimentacion();
        if (alimentacion != null) {
            stmt.bindLong(11, alimentacion);
        }
 
        Integer vestimenta = entity.getVestimenta();
        if (vestimenta != null) {
            stmt.bindLong(12, vestimenta);
        }
 
        Integer esfinteres = entity.getEsfinteres();
        if (esfinteres != null) {
            stmt.bindLong(13, esfinteres);
        }
 
        Integer retraimientoconstante = entity.getRetraimientoconstante();
        if (retraimientoconstante != null) {
            stmt.bindLong(14, retraimientoconstante);
        }
 
        Integer egocentrismoaumentado = entity.getEgocentrismoaumentado();
        if (egocentrismoaumentado != null) {
            stmt.bindLong(15, egocentrismoaumentado);
        }
 
        Integer perdidainteressentimientos = entity.getPerdidainteressentimientos();
        if (perdidainteressentimientos != null) {
            stmt.bindLong(16, perdidainteressentimientos);
        }
 
        Integer afectividadembolatada = entity.getAfectividadembolatada();
        if (afectividadembolatada != null) {
            stmt.bindLong(17, afectividadembolatada);
        }
 
        Integer perturbacioncontrolemocional = entity.getPerturbacioncontrolemocional();
        if (perturbacioncontrolemocional != null) {
            stmt.bindLong(18, perturbacioncontrolemocional);
        }
 
        Integer hilaridadinapropiada = entity.getHilaridadinapropiada();
        if (hilaridadinapropiada != null) {
            stmt.bindLong(19, hilaridadinapropiada);
        }
 
        Integer respuestaemocional = entity.getRespuestaemocional();
        if (respuestaemocional != null) {
            stmt.bindLong(20, respuestaemocional);
        }
 
        Integer indiscrecionessexuales = entity.getIndiscrecionessexuales();
        if (indiscrecionessexuales != null) {
            stmt.bindLong(21, indiscrecionessexuales);
        }
 
        Integer faltainteresaficiones = entity.getFaltainteresaficiones();
        if (faltainteresaficiones != null) {
            stmt.bindLong(22, faltainteresaficiones);
        }
 
        Integer disminucioniniciativaprogresiva = entity.getDisminucioniniciativaprogresiva();
        if (disminucioniniciativaprogresiva != null) {
            stmt.bindLong(23, disminucioniniciativaprogresiva);
        }
 
        Integer hiperactividadnojustificada = entity.getHiperactividadnojustificada();
        if (hiperactividadnojustificada != null) {
            stmt.bindLong(24, hiperactividadnojustificada);
        }
    }

    @Override
    protected final void attachEntity(BlessedIncapacity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BlessedIncapacity readEntity(Cursor cursor, int offset) {
        BlessedIncapacity entity = new BlessedIncapacity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // patientId
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // tareasdomesticas
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // pequenasdinero
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // listascortas
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // orientarsecasa
            cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6), // orientarsecalle
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // valorarentorno
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // recordarrecientes
            cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9), // rememorarpasado
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // alimentacion
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // vestimenta
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // esfinteres
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // retraimientoconstante
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // egocentrismoaumentado
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // perdidainteressentimientos
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // afectividadembolatada
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17), // perturbacioncontrolemocional
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // hilaridadinapropiada
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // respuestaemocional
            cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // indiscrecionessexuales
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // faltainteresaficiones
            cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22), // disminucioniniciativaprogresiva
            cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23) // hiperactividadnojustificada
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BlessedIncapacity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPatientId(cursor.getLong(offset + 1));
        entity.setTareasdomesticas(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setPequenasdinero(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setListascortas(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setOrientarsecasa(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setOrientarsecalle(cursor.isNull(offset + 6) ? null : cursor.getDouble(offset + 6));
        entity.setValorarentorno(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setRecordarrecientes(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setRememorarpasado(cursor.isNull(offset + 9) ? null : cursor.getDouble(offset + 9));
        entity.setAlimentacion(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setVestimenta(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setEsfinteres(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setRetraimientoconstante(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setEgocentrismoaumentado(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setPerdidainteressentimientos(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setAfectividadembolatada(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setPerturbacioncontrolemocional(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
        entity.setHilaridadinapropiada(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setRespuestaemocional(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setIndiscrecionessexuales(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setFaltainteresaficiones(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setDisminucioniniciativaprogresiva(cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22));
        entity.setHiperactividadnojustificada(cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BlessedIncapacity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BlessedIncapacity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BlessedIncapacity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getPatientDao().getAllColumns());
            builder.append(" FROM BLESSED_INCAPACITY T");
            builder.append(" LEFT JOIN PATIENT T0 ON T.\"PATIENT_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected BlessedIncapacity loadCurrentDeep(Cursor cursor, boolean lock) {
        BlessedIncapacity entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Patient patient = loadCurrentOther(daoSession.getPatientDao(), cursor, offset);
         if(patient != null) {
            entity.setPatient(patient);
        }

        return entity;    
    }

    public BlessedIncapacity loadDeep(Long key) {
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
    public List<BlessedIncapacity> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<BlessedIncapacity> list = new ArrayList<BlessedIncapacity>(count);
        
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
    
    protected List<BlessedIncapacity> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<BlessedIncapacity> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
