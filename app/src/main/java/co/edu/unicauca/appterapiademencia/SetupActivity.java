package co.edu.unicauca.appterapiademencia;

import android.app.Application;
import android.content.Context;

/**
 * Created by ENF on 21/10/2016.
 */

public class SetupActivity extends Application {
    private static Context context;
    private static boolean autenticationMode;




    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        autenticationMode = false;//inicializa en inicio de sesion


    }
    public static void setAutenticationMode(boolean autenticationMode) {
        SetupActivity.autenticationMode = autenticationMode;
    }

    public static boolean autenticationMode() {
        return autenticationMode;
    }



    public static Context getContext() {

        return context;
    }


}
