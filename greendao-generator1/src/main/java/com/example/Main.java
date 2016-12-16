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

        user.addIdProperty().autoincrement().primaryKey();
        user.addStringProperty("username").notNull().unique();
        user.addStringProperty("password").notNull();
        user.addStringProperty("completeName").notNull();
        user.addBooleanProperty("accessType"); //Booleano para determinar si es supervisor
        user.addStringProperty("photopath");

        //PATIENT TABLE
        Entity patient = schema.addEntity("Patient");

        patient.addIdProperty().autoincrement().primaryKey();
        patient.addStringProperty("name").notNull();
        patient.addStringProperty("sex");
        patient.addStringProperty("birthday").notNull();
        patient.addStringProperty("photopath");
        patient.addStringProperty("eps");
        patient.addLongProperty("identity").notNull().unique();
        patient.addStringProperty("antecedents");
        patient.addStringProperty("syndromes");
        patient.addStringProperty("observations");
        patient.addIntProperty("mec");
        patient.addIntProperty("gds");
        patient.addIntProperty("visionlimitation");
        patient.addIntProperty("writinglimitation");
        patient.addIntProperty("drawinglimitation");


        //BLESSED INCAPACITY TABLE

        Entity blessedIncapacity = schema.addEntity("BlessedIncapacity");
        blessedIncapacity.addIdProperty().autoincrement();



        Property patientIdblessedincapacity = blessedIncapacity.addLongProperty("patientId").notNull().getProperty();
        blessedIncapacity.addToOne(patient,patientIdblessedincapacity);

        //patient.addToMany(blessedIncapacity,patientIdblessedincapacity);


         //blessedIncapacity.addLongProperty("patientId").notNull();
        //ACTIVIDADES DE LA VIDA DIARIA


        blessedIncapacity.addDoubleProperty("tareasdomesticas");
        blessedIncapacity.addDoubleProperty("pequenasdinero");
        blessedIncapacity.addDoubleProperty("listascortas");
        blessedIncapacity.addDoubleProperty("orientarsecasa");
        blessedIncapacity.addDoubleProperty("orientarsecalle");
        blessedIncapacity.addDoubleProperty("valorarentorno");
        blessedIncapacity.addDoubleProperty("recordarrecientes");
        blessedIncapacity.addDoubleProperty("rememorarpasado");

        //HABITOS
        blessedIncapacity.addIntProperty("alimentacion");
        blessedIncapacity.addIntProperty("vestimenta");
        blessedIncapacity.addIntProperty("esfinteres");


        //PERSIONALIDAD Y CONDUCTA
        blessedIncapacity.addIntProperty("retraimientoconstante");
        blessedIncapacity.addIntProperty("egocentrismoaumentado");
        blessedIncapacity.addIntProperty("perdidainteressentimientos");
        blessedIncapacity.addIntProperty("afectividadembolatada");
        blessedIncapacity.addIntProperty("perturbacioncontrolemocional");
        blessedIncapacity.addIntProperty("hilaridadinapropiada");
        blessedIncapacity.addIntProperty("respuestaemocional");
        blessedIncapacity.addIntProperty("indiscrecionessexuales");
        blessedIncapacity.addIntProperty("faltainteresaficiones");
        blessedIncapacity.addIntProperty("disminucioniniciativaprogresiva");
        blessedIncapacity.addIntProperty("hiperactividadnojustificada");




        //NOTE TABLE
        Entity note = schema.addEntity("Note");
        note.addIdProperty().autoincrement().primaryKey();

        Property patientIdnote = note.addLongProperty("patientId").notNull().getProperty();
        patient.addToMany(note,patientIdnote);


        Property userIdnote = note.addLongProperty("userId").notNull().getProperty();
        user.addToMany(note,userIdnote);



        note.addIntProperty("type");
        note.addStringProperty("date").notNull();
        note.addStringProperty("hour");
        note.addStringProperty("description");
        note.addStringProperty("ambito"); //Tipo de Evento o Sintoma
        note.addStringProperty("selection"); //Evento especifico
        note.addStringProperty("owner");
        note.addBooleanProperty("late"); //Notas tardias
        note.addBooleanProperty("state"); //Notas no aprobadas


        //SINTOMAS O SIGNOS
        Entity sintoma = schema.addEntity("Sintoma");
        sintoma.addIdProperty().autoincrement().primaryKey();

        Property patientIdsintoma = sintoma.addLongProperty("patientId").notNull().getProperty();
        patient.addToMany(sintoma,patientIdsintoma);

        /*Property noteIdSintoma = sintoma.addLongProperty("noteId").notNull().getProperty();
        note.addToMany(sintoma,noteIdSintoma);*/

        sintoma.addStringProperty("ambito"); //Indicador
        sintoma.addStringProperty("signo"); //Evento especifico
        sintoma.addBooleanProperty("activo");//1 activo, 0 inactivo
        //sintoma.addLongProperty("takegroup");
        //sintoma.addStringProperty("test");
        //sintoma.addStringProperty("puntaje");


        //CALIFICATION
        Entity escala = schema.addEntity("Scale");
        escala.addIdProperty().autoincrement().primaryKey();

        Property sintomaIdescala = escala.addLongProperty("sintomaId").notNull().getProperty();
        sintoma.addToMany(escala,sintomaIdescala);

        escala.addStringProperty("escalaname");
        escala.addStringProperty("puntaje");



        //FASTDETALLE
        Entity detallefast= schema.addEntity("DetailFast");
        detallefast.addIdProperty().autoincrement().primaryKey();
        Property calificationIddetallefast = detallefast.addLongProperty("escalaId").notNull().getProperty();
        escala.addToMany(detallefast,calificationIddetallefast);

        detallefast.addStringProperty("stagename");
        detallefast.addStringProperty("characteristics");
        detallefast.addStringProperty("mentalage");
        detallefast.addStringProperty("mmsescore");


        //TIP TABLE
        Entity tip = schema.addEntity("Tip");
        tip.addIdProperty().autoincrement().primaryKey();

        Property userIdtip = tip.addLongProperty("userId").notNull().getProperty();
        user.addToMany(tip,userIdtip);


        tip.addStringProperty("title");
        tip.addStringProperty("description");
        tip.addBooleanProperty("active");
        //tip.addBooleanProperty("favorite");
        tip.addIntProperty("likes");

        //PREFERENCETIP
        Entity preferencetip = schema.addEntity("PreferenceTip");
        preferencetip.addIdProperty().autoincrement().primaryKey();

        Property userIdpreferencetip = preferencetip.addLongProperty("userId").notNull().getProperty();
        user.addToMany(preferencetip,userIdpreferencetip);

        Property tipIdpreference = preferencetip.addLongProperty("tipId").notNull().getProperty();
        tip.addToMany(preferencetip,tipIdpreference);

        preferencetip.addBooleanProperty("favorite");


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
