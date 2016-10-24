package co.edu.unicauca.appterapiademencia.principal;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import co.edu.unicauca.appterapiademencia.R;

/**
 * Created by ENF on 14/10/2016.
 */

public class PrincipalActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_principal);
    }
}
