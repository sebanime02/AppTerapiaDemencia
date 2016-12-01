package co.edu.unicauca.appterapiademencia.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ENF on 30/11/2016.
 */

public class ManagerFechas {
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    public String fechaActual(){
        Date fechaActual =new Date();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        return format.format(fechaActual);
    }
    public String horaActual(){
        Date fechaActual =new Date();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        return format.format(fechaActual);
    }
}
