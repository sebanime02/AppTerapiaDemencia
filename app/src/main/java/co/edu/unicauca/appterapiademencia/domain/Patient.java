package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import co.edu.unicauca.appterapiademencia.domain.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

import co.edu.unicauca.appterapiademencia.domain.dao.BlessedIncapacityDao;
import co.edu.unicauca.appterapiademencia.domain.dao.HistoricDao;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.domain.dao.RecommendationDao;
import co.edu.unicauca.appterapiademencia.domain.dao.SintomaDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "PATIENT".
 */
@Entity(active = true)
public class Patient {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String birthday;
    private String photopath;
    private String eps;

    @Unique
    private long identity;
    private String antecedents;
    private String syndromes;
    private String observations;
    private Integer mec;
    private Integer gds;
    private Integer visionlimitation;
    private Integer writinglimitation;
    private Integer drawinglimitation;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient PatientDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "patientId")
    })
    private List<BlessedIncapacity> blessedIncapacityList;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "patientId")
    })
    private List<Note> noteList;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "patientId")
    })
    private List<Sintoma> sintomaList;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "patientId")
    })
    private List<Historic> historicList;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "patientId")
    })
    private List<Recommendation> recommendationList;

    @Generated
    public Patient() {
    }

    public Patient(Long id) {
        this.id = id;
    }

    @Generated
    public Patient(Long id, String name, String birthday, String photopath, String eps, long identity, String antecedents, String syndromes, String observations, Integer mec, Integer gds, Integer visionlimitation, Integer writinglimitation, Integer drawinglimitation) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.photopath = photopath;
        this.eps = eps;
        this.identity = identity;
        this.antecedents = antecedents;
        this.syndromes = syndromes;
        this.observations = observations;
        this.mec = mec;
        this.gds = gds;
        this.visionlimitation = visionlimitation;
        this.writinglimitation = writinglimitation;
        this.drawinglimitation = drawinglimitation;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPatientDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getBirthday() {
        return birthday;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBirthday(@NotNull String birthday) {
        this.birthday = birthday;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getSyndromes() {
        return syndromes;
    }

    public void setSyndromes(String syndromes) {
        this.syndromes = syndromes;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getMec() {
        return mec;
    }

    public void setMec(Integer mec) {
        this.mec = mec;
    }

    public Integer getGds() {
        return gds;
    }

    public void setGds(Integer gds) {
        this.gds = gds;
    }

    public Integer getVisionlimitation() {
        return visionlimitation;
    }

    public void setVisionlimitation(Integer visionlimitation) {
        this.visionlimitation = visionlimitation;
    }

    public Integer getWritinglimitation() {
        return writinglimitation;
    }

    public void setWritinglimitation(Integer writinglimitation) {
        this.writinglimitation = writinglimitation;
    }

    public Integer getDrawinglimitation() {
        return drawinglimitation;
    }

    public void setDrawinglimitation(Integer drawinglimitation) {
        this.drawinglimitation = drawinglimitation;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<BlessedIncapacity> getBlessedIncapacityList() {
        if (blessedIncapacityList == null) {
            __throwIfDetached();
            BlessedIncapacityDao targetDao = daoSession.getBlessedIncapacityDao();
            List<BlessedIncapacity> blessedIncapacityListNew = targetDao._queryPatient_BlessedIncapacityList(id);
            synchronized (this) {
                if(blessedIncapacityList == null) {
                    blessedIncapacityList = blessedIncapacityListNew;
                }
            }
        }
        return blessedIncapacityList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetBlessedIncapacityList() {
        blessedIncapacityList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Note> getNoteList() {
        if (noteList == null) {
            __throwIfDetached();
            NoteDao targetDao = daoSession.getNoteDao();
            List<Note> noteListNew = targetDao._queryPatient_NoteList(id);
            synchronized (this) {
                if(noteList == null) {
                    noteList = noteListNew;
                }
            }
        }
        return noteList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetNoteList() {
        noteList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Sintoma> getSintomaList() {
        if (sintomaList == null) {
            __throwIfDetached();
            SintomaDao targetDao = daoSession.getSintomaDao();
            List<Sintoma> sintomaListNew = targetDao._queryPatient_SintomaList(id);
            synchronized (this) {
                if(sintomaList == null) {
                    sintomaList = sintomaListNew;
                }
            }
        }
        return sintomaList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetSintomaList() {
        sintomaList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Historic> getHistoricList() {
        if (historicList == null) {
            __throwIfDetached();
            HistoricDao targetDao = daoSession.getHistoricDao();
            List<Historic> historicListNew = targetDao._queryPatient_HistoricList(id);
            synchronized (this) {
                if(historicList == null) {
                    historicList = historicListNew;
                }
            }
        }
        return historicList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetHistoricList() {
        historicList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Recommendation> getRecommendationList() {
        if (recommendationList == null) {
            __throwIfDetached();
            RecommendationDao targetDao = daoSession.getRecommendationDao();
            List<Recommendation> recommendationListNew = targetDao._queryPatient_RecommendationList(id);
            synchronized (this) {
                if(recommendationList == null) {
                    recommendationList = recommendationListNew;
                }
            }
        }
        return recommendationList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetRecommendationList() {
        recommendationList = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
