package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import co.edu.unicauca.appterapiademencia.domain.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

import co.edu.unicauca.appterapiademencia.domain.dao.ExerciseDao;
import co.edu.unicauca.appterapiademencia.domain.dao.HistoricDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "EXERCISE".
 */
@Entity(active = true)
public class Exercise {

    @Id(autoincrement = true)
    private Long id;
    private long rutinaId;

    @NotNull
    private String workshop;
    private int level;
    private Integer state;
    private Integer time;
    private Boolean completemen;
    private String observations;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient ExerciseDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "exerciseId")
    })
    private List<Historic> historicList;

    @Generated
    public Exercise() {
    }

    public Exercise(Long id) {
        this.id = id;
    }

    @Generated
    public Exercise(Long id, long rutinaId, String workshop, int level, Integer state, Integer time, Boolean completemen, String observations) {
        this.id = id;
        this.rutinaId = rutinaId;
        this.workshop = workshop;
        this.level = level;
        this.state = state;
        this.time = time;
        this.completemen = completemen;
        this.observations = observations;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getExerciseDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(long rutinaId) {
        this.rutinaId = rutinaId;
    }

    @NotNull
    public String getWorkshop() {
        return workshop;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setWorkshop(@NotNull String workshop) {
        this.workshop = workshop;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Boolean getCompletemen() {
        return completemen;
    }

    public void setCompletemen(Boolean completemen) {
        this.completemen = completemen;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Historic> getHistoricList() {
        if (historicList == null) {
            __throwIfDetached();
            HistoricDao targetDao = daoSession.getHistoricDao();
            List<Historic> historicListNew = targetDao._queryExercise_HistoricList(id);
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
