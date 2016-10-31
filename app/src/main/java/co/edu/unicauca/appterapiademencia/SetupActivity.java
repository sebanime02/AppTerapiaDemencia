package co.edu.unicauca.appterapiademencia;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import org.greenrobot.greendao.database.Database;

import java.io.File;

import co.edu.unicauca.appterapiademencia.domain.dao.DaoMaster;
import co.edu.unicauca.appterapiademencia.domain.dao.DaoSession;

/**
 * Created by ENF on 21/10/2016.
 */

public class SetupActivity extends Application {
    private static Context context;
    private static boolean autenticationMode;
    private static final String DB_NAME="terapiaprueba-db";
    private static DaoSession daoSession;




    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setupGreenDaoHelper(context);
        createFolder();


    }

    private void setupGreenDaoHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new DaoMaster(db).newSession();
    }


    public static Context getContext() {

        return context;
    }
    private void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/ModTerapia");
        boolean success = true;
        if (!folder.exists()) {
            //Toast.makeText(this, "carpeta creada", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        if (success) {
            //Toast.makeText(this, "La carpeta ya esxiste", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "fallo al crear carpeta", Toast.LENGTH_SHORT).show();
        }
    }

}
