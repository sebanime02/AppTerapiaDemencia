package co.edu.unicauca.appterapiademencia;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import org.greenrobot.greendao.database.Database;

import co.edu.unicauca.appterapiademencia.domain.dao.DaoMaster;
import co.edu.unicauca.appterapiademencia.domain.dao.DaoSession;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;

/**
 * Created by ENF on 21/10/2016.
 */

public class SetupActivity extends Application {
    private static Context context;
    private static boolean autenticationMode;
    //private static final String DB_NAME="terapia-db";
    private static DaoSession daoSession;
    private SharedPreferences loginpreference;





    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        loginpreference = getSharedPreferences("appdata", Context.MODE_PRIVATE);
        if(loginpreference.getBoolean("sessionValidation",true)){
            SharedPreferences.Editor editor = loginpreference.edit();
            editor.putBoolean("sessionValidation",false);
            editor.commit();
        }
        setupGreenDaoHelper(context);



    }

    private void setupGreenDaoHelper(Context context) {


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, GreenDaoHelper.DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new DaoMaster(db).newSession();
        GreenDaoHelper.getInstance().setDaoSession(daoSession);

    }


    public static Context getContext() {

        return context;
    }


}
