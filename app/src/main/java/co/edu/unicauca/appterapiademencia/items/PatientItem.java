package co.edu.unicauca.appterapiademencia.items;

/**
 * Created by ENF on 28/10/2016.
 */

public class PatientItem {

    private String patientImage;
    private String patientName;
    private String patientAge;
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }



    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(String patientImage) {
        this.patientImage = patientImage;
    }
}
