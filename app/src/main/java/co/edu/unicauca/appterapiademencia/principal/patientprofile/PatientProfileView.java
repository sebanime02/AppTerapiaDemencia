package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;

/**
 * Created by SEBAS on 07/11/2016.
 */

public interface PatientProfileView  {
    void showPatientData(Patient patient);
    void changePhoto();
    void editPatientData(Long id);
    void getPatientData(Long id);
    void getBlessedScore(Long id);
    void showBlessedScore(Double score,String comentario,String color);
    void getFastScore(Long id);
    void showFastScore(String score,String etapa,String caracteristica,String edadMental,String mec,String gds);
    void getDowntonScore(Long id);
    void showDowntonScore(int score,String comentario);
    void getLawtonScore(Long id);
    void showLawtonScore(int score,String comentario);
    void graphBlessedScore(List<BlessedScoreAverage> blessedScoreAverageList);
    void graphLawtonScore();
    void graphDowntonScore();
    void graphGDSScore();


}
