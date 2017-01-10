package co.edu.unicauca.appterapiademencia.domain.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import co.edu.unicauca.appterapiademencia.domain.User;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.HistoricScore;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Sintoma;
import co.edu.unicauca.appterapiademencia.domain.Scale;
import co.edu.unicauca.appterapiademencia.domain.DetailFast;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.PreferenceTip;
import co.edu.unicauca.appterapiademencia.domain.Rutina;
import co.edu.unicauca.appterapiademencia.domain.Exercise;
import co.edu.unicauca.appterapiademencia.domain.Historic;
import co.edu.unicauca.appterapiademencia.domain.Reminiscence;

import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.domain.dao.HistoricScoreDao;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ScaleDao;
import co.edu.unicauca.appterapiademencia.domain.dao.DetailFastDao;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.domain.dao.PreferenceTipDao;
import co.edu.unicauca.appterapiademencia.domain.dao.RutinaDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ExerciseDao;
import co.edu.unicauca.appterapiademencia.domain.dao.HistoricDao;
import co.edu.unicauca.appterapiademencia.domain.dao.ReminiscenceDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig patientDaoConfig;
    private final DaoConfig historicScoreDaoConfig;
    private final DaoConfig noteDaoConfig;
    private final DaoConfig sintomaDaoConfig;
    private final DaoConfig scaleDaoConfig;
    private final DaoConfig detailFastDaoConfig;
    private final DaoConfig tipDaoConfig;
    private final DaoConfig preferenceTipDaoConfig;
    private final DaoConfig rutinaDaoConfig;
    private final DaoConfig exerciseDaoConfig;
    private final DaoConfig historicDaoConfig;
    private final DaoConfig reminiscenceDaoConfig;

    private final UserDao userDao;
    private final PatientDao patientDao;
    private final HistoricScoreDao historicScoreDao;
    private final NoteDao noteDao;
    private final SintomaDao sintomaDao;
    private final ScaleDao scaleDao;
    private final DetailFastDao detailFastDao;
    private final TipDao tipDao;
    private final PreferenceTipDao preferenceTipDao;
    private final RutinaDao rutinaDao;
    private final ExerciseDao exerciseDao;
    private final HistoricDao historicDao;
    private final ReminiscenceDao reminiscenceDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        patientDaoConfig = daoConfigMap.get(PatientDao.class).clone();
        patientDaoConfig.initIdentityScope(type);

        historicScoreDaoConfig = daoConfigMap.get(HistoricScoreDao.class).clone();
        historicScoreDaoConfig.initIdentityScope(type);

        noteDaoConfig = daoConfigMap.get(NoteDao.class).clone();
        noteDaoConfig.initIdentityScope(type);

        sintomaDaoConfig = daoConfigMap.get(SintomaDao.class).clone();
        sintomaDaoConfig.initIdentityScope(type);

        scaleDaoConfig = daoConfigMap.get(ScaleDao.class).clone();
        scaleDaoConfig.initIdentityScope(type);

        detailFastDaoConfig = daoConfigMap.get(DetailFastDao.class).clone();
        detailFastDaoConfig.initIdentityScope(type);

        tipDaoConfig = daoConfigMap.get(TipDao.class).clone();
        tipDaoConfig.initIdentityScope(type);

        preferenceTipDaoConfig = daoConfigMap.get(PreferenceTipDao.class).clone();
        preferenceTipDaoConfig.initIdentityScope(type);

        rutinaDaoConfig = daoConfigMap.get(RutinaDao.class).clone();
        rutinaDaoConfig.initIdentityScope(type);

        exerciseDaoConfig = daoConfigMap.get(ExerciseDao.class).clone();
        exerciseDaoConfig.initIdentityScope(type);

        historicDaoConfig = daoConfigMap.get(HistoricDao.class).clone();
        historicDaoConfig.initIdentityScope(type);

        reminiscenceDaoConfig = daoConfigMap.get(ReminiscenceDao.class).clone();
        reminiscenceDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        patientDao = new PatientDao(patientDaoConfig, this);
        historicScoreDao = new HistoricScoreDao(historicScoreDaoConfig, this);
        noteDao = new NoteDao(noteDaoConfig, this);
        sintomaDao = new SintomaDao(sintomaDaoConfig, this);
        scaleDao = new ScaleDao(scaleDaoConfig, this);
        detailFastDao = new DetailFastDao(detailFastDaoConfig, this);
        tipDao = new TipDao(tipDaoConfig, this);
        preferenceTipDao = new PreferenceTipDao(preferenceTipDaoConfig, this);
        rutinaDao = new RutinaDao(rutinaDaoConfig, this);
        exerciseDao = new ExerciseDao(exerciseDaoConfig, this);
        historicDao = new HistoricDao(historicDaoConfig, this);
        reminiscenceDao = new ReminiscenceDao(reminiscenceDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Patient.class, patientDao);
        registerDao(HistoricScore.class, historicScoreDao);
        registerDao(Note.class, noteDao);
        registerDao(Sintoma.class, sintomaDao);
        registerDao(Scale.class, scaleDao);
        registerDao(DetailFast.class, detailFastDao);
        registerDao(Tip.class, tipDao);
        registerDao(PreferenceTip.class, preferenceTipDao);
        registerDao(Rutina.class, rutinaDao);
        registerDao(Exercise.class, exerciseDao);
        registerDao(Historic.class, historicDao);
        registerDao(Reminiscence.class, reminiscenceDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        patientDaoConfig.clearIdentityScope();
        historicScoreDaoConfig.clearIdentityScope();
        noteDaoConfig.clearIdentityScope();
        sintomaDaoConfig.clearIdentityScope();
        scaleDaoConfig.clearIdentityScope();
        detailFastDaoConfig.clearIdentityScope();
        tipDaoConfig.clearIdentityScope();
        preferenceTipDaoConfig.clearIdentityScope();
        rutinaDaoConfig.clearIdentityScope();
        exerciseDaoConfig.clearIdentityScope();
        historicDaoConfig.clearIdentityScope();
        reminiscenceDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PatientDao getPatientDao() {
        return patientDao;
    }

    public HistoricScoreDao getHistoricScoreDao() {
        return historicScoreDao;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public SintomaDao getSintomaDao() {
        return sintomaDao;
    }

    public ScaleDao getScaleDao() {
        return scaleDao;
    }

    public DetailFastDao getDetailFastDao() {
        return detailFastDao;
    }

    public TipDao getTipDao() {
        return tipDao;
    }

    public PreferenceTipDao getPreferenceTipDao() {
        return preferenceTipDao;
    }

    public RutinaDao getRutinaDao() {
        return rutinaDao;
    }

    public ExerciseDao getExerciseDao() {
        return exerciseDao;
    }

    public HistoricDao getHistoricDao() {
        return historicDao;
    }

    public ReminiscenceDao getReminiscenceDao() {
        return reminiscenceDao;
    }

}
