package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "REMINISCENCE".
 */
@Entity
public class Reminiscence {

    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String description;
    private String photopath;
    private String author;
    private String audiopath;

    @Generated
    public Reminiscence() {
    }

    public Reminiscence(Long id) {
        this.id = id;
    }

    @Generated
    public Reminiscence(Long id, String title, String description, String photopath, String author, String audiopath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photopath = photopath;
        this.author = author;
        this.audiopath = audiopath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAudiopath() {
        return audiopath;
    }

    public void setAudiopath(String audiopath) {
        this.audiopath = audiopath;
    }

}