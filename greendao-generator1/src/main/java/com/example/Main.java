package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.Property;
public class Main {
    public static void main(String[] args)throws Exception {
        Schema schema = new Schema(1, "co.edu.unicauca.appterapiademencia.domain");
        schema.setDefaultJavaPackageDao("co.edu.unicauca.appterapiademencia.domain.dao");


        //USER TABLE
        Entity user = schema.addEntity("User");

        user.addIdProperty().autoincrement();
        user.addStringProperty("username").notNull().unique();
        user.addStringProperty("password").notNull();
        user.addStringProperty("completeName").notNull();
        user.addBooleanProperty("accessType"); //Booleano para determinar si es supervisor

        //PATIENT TABLE
        Entity patient = schema.addEntity("Patient");

        patient.addIdProperty().autoincrement();
        patient.addStringProperty("name").notNull();
        patient.addStringProperty("birthday").notNull();
        patient.addStringProperty("photopath");
        patient.addStringProperty("eps");
        patient.addIntProperty("identity").notNull().unique();
        patient.addStringProperty("antecedents");
        patient.addStringProperty("syndromes");
        patient.addStringProperty("observations");
        patient.addIntProperty("mec");
        patient.addIntProperty("gds");
        patient.addIntProperty("visionlimitation");
        patient.addIntProperty("writinglimitation");
        patient.addIntProperty("drawinglimitation");





        //NOTE TABLE
        Entity note = schema.addEntity("Note");
        note.addIdProperty().autoincrement();

        Property patientIdnote = note.addLongProperty("patientId").notNull().getProperty();
        patient.addToMany(note,patientIdnote);


        Property userIdnote = note.addLongProperty("userId").notNull().getProperty();
        user.addToMany(note,userIdnote);



        note.addStringProperty("noteType");
        note.addStringProperty("date").notNull();
        note.addStringProperty("hour");
        note.addStringProperty("description");
        note.addStringProperty("color"); //Importancia
        note.addStringProperty("owner");
        note.addBooleanProperty("late"); //Notas tardias
        note.addBooleanProperty("state"); //Notas no aprobadas


        //TIP TABLE
        Entity tip = schema.addEntity("Tip");

        tip.addIdProperty().autoincrement();

        Property userIdtip = tip.addLongProperty("userId").notNull().getProperty();
        user.addToMany(tip,userIdtip);


        tip.addStringProperty("title");
        tip.addStringProperty("description");
        tip.addDateProperty("date");


        //COGNITIVE EXERCISE TABLE
        Entity exercise = schema.addEntity("Exercise");
        exercise.addIdProperty().autoincrement();
        exercise.addStringProperty("workshop").notNull();
        exercise.addIntProperty("level").notNull();
        exercise.addStringProperty("instructions");
        exercise.addStringProperty("audioinstructions");


        //HISTORIC TABLE
        Entity historic = schema.addEntity("Historic");
        historic.addIdProperty().autoincrement();

        Property patientIdhistoric = historic.addLongProperty("patientId").notNull().getProperty();
        patient.addToMany(historic,patientIdhistoric);


        Property exerciseIdhistoric = historic.addLongProperty("exerciseId").notNull().getProperty();
        exercise.addToMany(historic,exerciseIdhistoric);

        historic.addDateProperty("date");
        historic.addStringProperty("hour");
        historic.addBooleanProperty("completed");





        //COGNITIVE RECOMENDATION TABLE
        Entity recommendation = schema.addEntity("Recommendation");
        recommendation.addIdProperty().autoincrement();

        Property patientIdrecommendation = recommendation.addLongProperty("patientId").notNull().getProperty();
        patient.addToMany(recommendation,patientIdrecommendation);

        Property exerciseIdrecommendation = recommendation.addLongProperty("exerciseId").notNull().getProperty();
        exercise.addToMany(recommendation,exerciseIdhistoric);


        DaoGenerator dao = new DaoGenerator();
        dao.generateAll(schema,"./app/src/main/java");


    }
    }
