package co.edu.unicauca.appterapiademencia.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ENF on 30/11/2016.
 */

public class ManagerFechas {
    String var_hora,var_fecha;
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

    public String fechaActualCalender()
    {
        Locale spanish = new Locale("es", "ES");

        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy",spanish);
        var_fecha = dateFormat.format(Calendar.getInstance().getTime());
        return var_fecha;
    }
}
