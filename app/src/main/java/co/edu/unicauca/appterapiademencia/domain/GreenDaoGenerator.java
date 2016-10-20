package co.edu.unicauca.appterapiademencia.domain;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/**
 * Created by ENF on 19/10/2016.
 */

public class GreenDaoGenerator {
    public static void main(String[] args)throws Exception{
        Schema schema = new Schema(1,"co.edu.unicauca.appterapiademencia.domain");
        schema.setDefaultJavaPackageDao("co.edu.unicauca.appterapiademencia.domain.dao");
        addUserTable(schema);
    }

    private static void addUserTable(Schema schema) {
        Entity user = schema.addEntity("User");
        //Id, username,password,
        user.addIdProperty().autoincrement();







    }
}
