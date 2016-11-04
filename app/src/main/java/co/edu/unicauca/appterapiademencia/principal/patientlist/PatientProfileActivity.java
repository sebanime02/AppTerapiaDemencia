package co.edu.unicauca.appterapiademencia.principal.patientlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 04/11/2016.
 */

public class PatientProfileActivity extends AppCompatActivity{
    private TextView inputprofileidentity;
    private String cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_profile_activity);
        Bundle bundle = getIntent().getExtras();
        cedula = bundle.get("cedula").toString();

        inputprofileidentity= (TextView) findViewById(R.id.txt_profile);
        inputprofileidentity.setText(cedula);

    }
}
