package co.edu.unicauca.appterapiademencia;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

import co.edu.unicauca.appterapiademencia.domain.dao.DaoMaster;
import co.edu.unicauca.appterapiademencia.domain.dao.DaoSession;

/**
 * Created by ENF on 21/10/2016.
 */

public class SetupActivity extends Application {
    private static Context context;
    private static boolean autenticationMode;
    private static final String DB_NAME="terapia-db";
    private static DaoSession daoSession;




    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setupGreenDaoHelper(context);




    }

    private void setupGreenDaoHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        this.daoSession = new DaoMaster(db).newSession();
    }


    public static Context getContext() {

        return context;
    }


}
