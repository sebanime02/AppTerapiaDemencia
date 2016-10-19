package co.edu.unicauca.appterapiademencia.domain.models;

import com.orm.SugarRecord;

/**
 * Created by ENF on 18/10/2016.
 */

public class User extends SugarRecord
{
    private String username;
    private String password;
    private String completeName;
    private int accessType;

    public User()
    {

    }
    public User(String username,String password,String completeName,int accessType){
        this.username = username;
        this.password = password;
        this.completeName = completeName;
        this.accessType = accessType;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getCompleteName() {
        return completeName;
    }

    public int getAccessType() {
        return accessType;
    }
}


