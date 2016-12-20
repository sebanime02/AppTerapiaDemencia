package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "HISTORIC_SCORE".
 */
@Entity
public class HistoricScore {

    @Id(autoincrement = true)
    private Long id;
    private long patientId;
    private String scale;
    private Double value;
    private java.util.Date date;

    @Generated
    public HistoricScore() {
    }

    public HistoricScore(Long id) {
        this.id = id;
    }

    @Generated
    public HistoricScore(Long id, long patientId, String scale, Double value, java.util.Date date) {
        this.id = id;
        this.patientId = patientId;
        this.scale = scale;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

}
