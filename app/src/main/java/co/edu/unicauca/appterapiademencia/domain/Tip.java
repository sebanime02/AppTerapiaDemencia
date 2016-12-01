package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "TIP".
 */
@Entity
public class Tip {

    @Id(autoincrement = true)
    private Long id;
    private long userId;
    private String title;
    private String description;
    private Boolean active;
    private Boolean favorite;

    @Generated
    public Tip() {
    }

    public Tip(Long id) {
        this.id = id;
    }

    @Generated
    public Tip(Long id, long userId, String title, String description, Boolean active, Boolean favorite) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.active = active;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

}
