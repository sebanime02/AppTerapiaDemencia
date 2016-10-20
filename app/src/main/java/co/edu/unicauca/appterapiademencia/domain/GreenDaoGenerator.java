package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by ENF on 19/10/2016.
 */

public class GreenDaoGenerator {
    public static void main(String[] args)throws Exception{
        Schema schema = new Schema(1,"co.edu.unicauca.appterapiademencia.domain");
        schema.setDefaultJavaPackageDao("co.edu.unicauca.appterapiademencia.domain.dao");
        addUserTable(schema);
        addPatientTable(schema);
        addNoteTable(schema);
        addExercisecognitiveTable(schema);
        addRecommendationTable(schema);

    }



    private static void addUserTable(Schema schema) {
        Entity user = schema.addEntity("User");
        //Id, username,password,completeName,accessType
        user.addIdProperty().autoincrement();
        user.addStringProperty("username").notNull().unique();
        user.addStringProperty("password").notNull();
        user.addStringProperty("completeName").notNull();
        user.addBooleanProperty("accessType"); //Booleano para determinar si es supervisor

    }
    private static void addPatientTable(Schema schema) {
        Entity patient = schema.addEntity("Patient");
        //Id,name,age,eps,identity,antecedents,familiar-name,familiar-cellphone
        patient.addIdProperty().autoincrement();
        patient.addStringProperty("name").notNull();
        patient.addIntProperty("age").notNull();
        patient.addIntProperty("eps");
        patient.addIntProperty("identity").notNull().unique();
        patient.addStringProperty("antecedents");
        patient.addStringProperty("familiarname");
        patient.addStringProperty("familiarcellphone");
        patient.addStringProperty("syndromes");


    }
    private static void addNoteTable(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty().autoincrement();
        Property patientId = note.addLongProperty("patientId").notNull().getProperty();
        note.addStringProperty("noteType");
        note.addDateProperty("date").notNull();
        note.addStringProperty("hour");
        note.addStringProperty("description");
        note.addStringProperty("color");
        note.addStringProperty("owner");
        note.addBooleanProperty("late");
        note.addBooleanProperty("state");
    }
    private static void addExercisecognitiveTable(Schema schema) {
        Entity exercise = schema.addEntity("ExerciseCognitive");
        exercise.addIdProperty().autoincrement();
        exercise.addStringProperty("workshop").notNull();
        exercise.addIntProperty("level").notNull();
        exercise.addStringProperty("instructions");
        exercise.addStringProperty("audioinstructions");
    }
    private static void addRecommendationTable(Schema schema) {
        Entity recommendation = schema.addEntity("Recommendation");
    }


}
