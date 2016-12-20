package co.edu.unicauca.appterapiademencia.domain.dao;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.HistoricScore;
import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.PreferenceTip;
import co.edu.unicauca.appterapiademencia.domain.Scale;
import co.edu.unicauca.appterapiademencia.domain.Sintoma;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.User;


/**
 * Created by ENF on 21/10/2016.
 */

public class GreenDaoHelper {

    private static co.edu.unicauca.appterapiademencia.domain.dao.DaoSession daoSession;
    public static final String DB_NAME="terapiaprueba-db";
    private static Context context;
    private HashMap<String,String> userinformation;
    private QueryBuilder queryBuilder;


    private static class SingletonHolder{

     private static final GreenDaoHelper INSTANCE = new GreenDaoHelper();

    }
    public static GreenDaoHelper getInstance(){
            return SingletonHolder.INSTANCE;
    }

    private GreenDaoHelper()
    {

    }

    public  DaoSession getDaoSession(){
        return daoSession;
    }
    public void setDaoSession(DaoSession daoSession){
        this.daoSession = daoSession;
    }
    public UserDao getUserDao(){
        return daoSession.getUserDao();
    }
    public  PatientDao getPatientDao(){
        return daoSession.getPatientDao();
    }
    public  ExerciseDao getExerciseDao(){
        return daoSession.getExerciseDao();
    }
    public  NoteDao getNoteDao(){
        return daoSession.getNoteDao();
    }
    public  TipDao getTipDao(){
        return daoSession.getTipDao();
    }
    public  RecommendationDao getRecommendationDao(){
        return daoSession.getRecommendationDao();
    }
    public  HistoricDao getHistoricDao(){
        return daoSession.getHistoricDao();
    }
    public  HistoricScoreDao getHistoricScoreDao(){return daoSession.getHistoricScoreDao();}
    public  SintomaDao getSintomaDao(){
        return daoSession.getSintomaDao();
    }
    public ScaleDao getScaleDao(){return daoSession.getScaleDao();}
    public DetailFastDao getDetailFastDao(){return daoSession.getDetailFastDao();}

    public PreferenceTipDao getTipPreferenceDao(){return daoSession.getPreferenceTipDao();}


    public User getUserInformation(String username)
    {
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.Username.eq(username)).limit(1).list();
        return listuser.get(0);

    }
    public boolean insertUser(String username, String password, String completeName, Boolean accessType)
    {
        try {
            UserDao userDao;
            userDao = getUserDao();
            User user = new User(null,username,password,completeName,accessType,"");
            userDao.insert(user);
            Log.d("RegistroUsuario","Nueva id insertada: "+user.getId());
            return true;

        }catch (Exception e)
        {
            return false;

        }

    }

    public Patient getPatientInformationUsingCedula(Long id){
        queryBuilder=getPatientDao().queryBuilder();
        List<Patient> listpatient = queryBuilder.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        return listpatient.get(0);
    }
    public User getUserInformationUsingId(Long id){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.Id.eq(id)).limit(1).list();
        return listuser.get(0);
    }

    public Patient getPatientInformation(Long id){
        queryBuilder=getPatientDao().queryBuilder();
        List<Patient> listpatient = queryBuilder.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        return listpatient.get(0);
    }
    public Boolean carerIdDetector(){
        queryBuilder=getUserDao().queryBuilder();
        try
        {
            List<User> listuser = queryBuilder.where(UserDao.Properties.AccessType.eq(false)).limit(1).list();
            Log.e("helper name carer",listuser.get(0).getCompleteName());
            return true;
        }catch (IndexOutOfBoundsException e)
        {
            return false;
        }
    }
    public User getCarerInformation(){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.where(UserDao.Properties.AccessType.eq(false)).limit(1).list();
        return listuser.get(0);
    }

    public void getUsers(){
        queryBuilder=getUserDao().queryBuilder();
        List<User> listuser = queryBuilder.list();
        try {
            for (int m = 0; m <= listuser.size(); m++) {
                Log.e("helper getusers", " " + listuser.get(m).getId());
                Log.e("helper getusers", " " + listuser.get(m).getUsername());
                Log.e("helper getusers", " " + listuser.get(m).getPassword());
                Log.e("helper getusers", " " + listuser.get(m).getCompleteName());
                Log.e("helper getusers", " " + listuser.get(m).getAccessType());
                Log.e("helper getusers", "                     ");
            }
        }
        catch (IndexOutOfBoundsException e){
            Log.e("helper getusers","Sin usuarios");

        }
    }




    public Note getNote(Long idnote)
    {
        Note note;
        List<Note> noteList1;
        QueryBuilder<Note> noteQueryBuilder = getNoteDao().queryBuilder();
        noteList1 = noteQueryBuilder.where(NoteDao.Properties.Id.eq(idnote)).limit(1).list();
        note = noteList1.get(0);
        return note;
    }




    public Sintoma getSintoma(Long patientId,String escala, String indicador,String signo)
    {
        Sintoma sintoma;
        List<Sintoma> sintomaList;


        QueryBuilder<Sintoma> sintomaQueryBuilder = getSintomaDao().queryBuilder();
        sintomaQueryBuilder.where(SintomaDao.Properties.PatientId.eq(patientId));
        //sintomaQueryBuilder.where(SintomaDao.Properties.Test.eq(escala));
        sintomaQueryBuilder.where(SintomaDao.Properties.Ambito.eq(indicador));
        sintomaQueryBuilder.where(SintomaDao.Properties.Signo.eq(signo));
        //sintomaQueryBuilder.join(ScaleDao.Properties.SintomaId,Sintoma.class,SintomaDao.Properties.Id);
        //sintomaQueryBuilder.where(ScaleDao.Properties.Escalaname.eq(escala));
        //sintomaQueryBuilder.orderDesc(SintomaDao.Properties.Id);
        sintomaList = sintomaQueryBuilder.list();
        sintoma = sintomaList.get(0);
        Log.e("getsintoma",sintoma.getSigno());
        return  sintoma;
        //.and(SintomaDao.Properties.Test.eq(escala));

    }
    public void printSintomas(Long patientId)
    {
        /*
        Sintoma sintoma;
        List<Sintoma> sintomaList;
        List<Tip> arrrayList= new ArrayList<Tip>();

        QueryBuilder<Sintoma> sintomaQueryBuilder = getSintomaDao().queryBuilder();
        sintomaQueryBuilder.where(SintomaDao.Properties.PatientId.eq(patientId));
        sintomaQueryBuilder.where(SintomaDao.Properties.Activo.eq(true));
        sintomaList = sintomaQueryBuilder.list();
        try
        {
            for(int m=0;m<=sintomaList.size();m++)
            {
                Log.e("Helper sintomas"," "+sintomaList.get(m).getId());
                Log.e("Helper sintomas"," "+sintomaList.get(m).getSigno());
                Log.e("Helper sintomas"," "+sintomaList.get(m).getAmbito());
                Log.e("Helper sintomas"," "+sintomaList.get(m).getActivo());

                if(sintomaList.get(m).getActivo())
                {
                    Log.e("Helper tips","aceptados "+tipList.get(m).getId());
                    arrrayList.add(tipList.get(m));
                }
            }
        }catch (Exception e){}
       */
    }


    public Scale getScale(Long sintomaId, String escala)
    {
        Scale scale;
        List<Scale> scaleList;
        QueryBuilder<Scale> scaleQueryBuilder = getScaleDao().queryBuilder();
        scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq(escala));
        scaleQueryBuilder.where(ScaleDao.Properties.SintomaId.eq(sintomaId));



        scaleList = scaleQueryBuilder.list();
        scale = scaleList.get(0);
        Log.e("getscale",scale.getEscalaname());
        Log.e("getscale",scale.getPuntaje());

        return  scale;

    }
    public Tip getTip(Long idtip)
    {
            Tip tip;
            List<Tip> tipList;
            QueryBuilder<Tip> tipQueryBuilder = getTipDao().queryBuilder();
            tipQueryBuilder.where(TipDao.Properties.Id.eq(idtip));
            tipList = tipQueryBuilder.limit(1).list();
            tip = tipList.get(0);
            return tip;
    }

    public List<Tip> getTipsNotifications()
    {
        List<Tip> tipList;
        List<Tip> arrrayList= new ArrayList<Tip>();
        QueryBuilder<Tip> tipQueryBuilder = getTipDao().queryBuilder();
        //tipList = tipQueryBuilder.where(TipDao.Properties.Active.eq("true")).list();
        tipList = tipQueryBuilder.list();
        try
        {
            for(int m=0;m<=tipList.size();m++)
            {
            Log.e("Helper tips"," "+tipList.get(m).getId());
            Log.e("Helper tips"," "+tipList.get(m).getTitle());
            Log.e("Helper tips"," "+tipList.get(m).getActive());
                if(tipList.get(m).getActive())
                {
                    Log.e("Helper tips","aceptados "+tipList.get(m).getId());
                    arrrayList.add(tipList.get(m));
                }
            }
        }catch (Exception e){}
        return  arrrayList;
    }
    public Double getBlessedScore(Long patientid)
    {
        Log.e("blessed score","patient id "+patientid);
        List<Scale> scaleList = new ArrayList<Scale>();
        List<Sintoma> sintomaList;
        List<Scale> totalList;
        Double x=0.0;
        Double z=0.0;
        Double alimentacion = 0.0;
        Double vestimenta = 0.0;
        Double higiene = 0.0;




        Log.e("blessed score","Entro a calcular el blessed score");
        QueryBuilder sintomaQueryBuilder = getSintomaDao().queryBuilder();


        sintomaQueryBuilder.where(SintomaDao.Properties.Activo.eq(true),SintomaDao.Properties.PatientId.eq(patientid));
        sintomaList = sintomaQueryBuilder.list();

        QueryBuilder<Scale> scaleQueryBuilder = getScaleDao().queryBuilder();
        //scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq("Blessed"));


        for(int y=0;y<sintomaList.size();y++)
        {

            Log.e("blessed score","paciente: "+sintomaList.get(y).getPatientId());
            Log.e("blessed score","sintoma: "+sintomaList.get(y).getSigno());



            for(int m=0;m<sintomaList.get(y).getScaleList().size();m++)

            {
                String escalatexto = sintomaList.get(y).getScaleList().get(m).getEscalaname().toString();
                Log.e("blessed score","Entre al for sintomaList");
                Log.e("blessed score","getNombreEscala: "+escalatexto);

                if(escalatexto.matches("Blessed"))

                {

                    if(sintomaList.get(y).getAmbito().matches("alimentacion")) {
                        if (sintomaList.get(y).getSigno().matches("alimentacioncuchara")) {
                            alimentacion=1.0;

                        } else if (sintomaList.get(y).getSigno().matches("alimentacionsolidos")) {
                            alimentacion=2.0;

                        } else if (  sintomaList.get(y).getSigno().matches("alimentaciondependientes")){
                            alimentacion = 3.0;

                        }
                    }




                    else if(sintomaList.get(y).getAmbito().matches("vestimenta"))
                    {
                        if (sintomaList.get(y).getSigno().matches("vestimentafallosocasionales")) {
                            vestimenta=1.0;

                        } else if (sintomaList.get(y).getSigno().matches("vestimentasecuencia")) {
                            vestimenta=2.0;

                        } else if (  sintomaList.get(y).getSigno().matches("vestimentaincapaz")){
                            vestimenta = 3.0;

                        }
                        else
                        {
                            Log.e("blessed score","alimentacion: "+alimentacion);
                            Log.e("blessed score","vestimenta: "+vestimenta);

                            Log.e("blessed score","higiene: "+higiene);


                            Log.e("blessed score","Es blesssed");
                            Log.e("blessed score","getNombreEscala: "+sintomaList.get(y).getScaleList().get(m).getEscalaname());
                            Log.e("blessed score","getPuntaje: "+sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            x = x + Double.parseDouble(sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            Log.e("blessed score","x: "+x);
                        }

                    }
                    else if(sintomaList.get(y).getAmbito().matches("higiene"))
                    {
                        if (sintomaList.get(y).getSigno().matches("higieneincontinensiaurinaria")) {
                            higiene=2.0;

                        } else if (sintomaList.get(y).getSigno().matches("higieneayudaincontinensiafecal")) {
                            if(higiene==2.0)
                            {
                                higiene=3.0;
                            }

                        }
                        else
                        {
                            Log.e("blessed score","alimentacion: "+alimentacion);
                            Log.e("blessed score","vestimenta: "+vestimenta);

                            Log.e("blessed score","higiene: "+higiene);


                            Log.e("blessed score","Es blesssed");
                            Log.e("blessed score","getNombreEscala: "+sintomaList.get(y).getScaleList().get(m).getEscalaname());
                            Log.e("blessed score","getPuntaje: "+sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            x = x + Double.parseDouble(sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            Log.e("blessed score","x: "+x);
                        }
                    }

                    else
                    {
                        Log.e("blessed score","alimentacion: "+alimentacion);
                        Log.e("blessed score","vestimenta: "+vestimenta);

                        Log.e("blessed score","higiene: "+higiene);


                            Log.e("blessed score","Es blesssed");
                            Log.e("blessed score","getNombreEscala: "+sintomaList.get(y).getScaleList().get(m).getEscalaname());
                            Log.e("blessed score","getPuntaje: "+sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            x = x + Double.parseDouble(sintomaList.get(y).getScaleList().get(m).getPuntaje());
                            Log.e("blessed score","x: "+x);


                    }






                }

            }
            z = z + x;

            Log.e("blessed score ","acumulado z: "+z);
            x=0.0;


        }
        if(alimentacion!=0.0)
        {
            Log.e("helper greendao","Valor alimentacion:"+alimentacion);

            z = z + alimentacion;

            alimentacion=0.0;
        }
        if(vestimenta!=0.0)
        {
            Log.e("helper greendao","Valor vestimenta:"+vestimenta);

            z = z + vestimenta;
            vestimenta=0.0;
        }
        if(higiene!=0.0)
        {
            Log.e("helper greendao","Valor higiene:"+higiene);
            z = z + higiene;
            higiene=0.0;
        }



        Log.e("blessed score","total "+z);

        return z;

        /*

        QueryBuilder<Scale> scaleQueryBuilder = getScaleDao().queryBuilder();
        //scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq("Blessed"));
        //QueryBuilder<Sintoma> sintomaQueryBuilder = getSintomaDao().queryBuilder();
        //sintomaList = sintomaQueryBuilder.where(SintomaDao.Properties.PatientId.eq(patientid)).list();
        //scaleQueryBuilder.join(ScaleDao.Properties.SintomaId,Sintoma.class,SintomaDao.Properties.o)

        scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq("Blessed"));
        scaleQueryBuilder.join(ScaleDao.Properties.SintomaId,Sintoma.class,SintomaDao.Properties.Id).where(SintomaDao.Properties.PatientId.eq(patientid));
        scaleList = scaleQueryBuilder.list();

        for(int m=0;m<scaleList.size();m++)
        {
            x = x + Double.parseDouble(scaleList.get(m).getPuntaje());
            Log.e("blessed score","suma y suma x. ."+x);
        }
        return x;
        */



    }

    public String getFASTScore(Long patientid)
    {
        Log.e("fast score","patient id "+patientid);
        List<Scale> scaleList = new ArrayList<Scale>();
        List<Sintoma> sintomaList;
        List<Scale> totalList;
        int x=10;
        int w=10;
        int z=10;
        int t=10;



        Log.e("fast score","Entro a calcular el FAST score");
        QueryBuilder sintomaQueryBuilder = getSintomaDao().queryBuilder();


        sintomaQueryBuilder.where(SintomaDao.Properties.Activo.eq(true),SintomaDao.Properties.PatientId.eq(patientid));
        sintomaList = sintomaQueryBuilder.list();

        QueryBuilder<Scale> scaleQueryBuilder = getScaleDao().queryBuilder();
        //scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq("Blessed"));


        for(int y=0;y<sintomaList.size();y++)
        {

            Log.e("fast score","paciente: "+sintomaList.get(y).getPatientId());
            Log.e("fast score","sintoma: "+sintomaList.get(y).getSigno());

            for(int m=0;m<sintomaList.get(y).getScaleList().size();m++)

            {
                String escalatexto = sintomaList.get(y).getScaleList().get(m).getEscalaname().toString();
                Log.e("fast score","Entre al for sintomaList");
                Log.e("fast score","getNombreEscala: "+escalatexto);

                if(escalatexto.matches("FAST"))

                {
                    Log.e("blessed score","Es blesssed");
                    Log.e("blessed score","getNombreEscala: "+sintomaList.get(y).getScaleList().get(m).getEscalaname());
                    Log.e("blessed score","getPuntaje: "+sintomaList.get(y).getScaleList().get(m).getPuntaje());
                    try {
                        z = Integer.parseInt(sintomaList.get(y).getScaleList().get(m).getPuntaje());

                        if (x < z)
                        {
                            x = z;
                        }

                    }catch (Exception e)
                    {

                    }




                }
                        w = x;
                }
                    t = w;
                    Log.e("fast score ","acumulado w: "+t);


            }


        String fastScore;

        fastScore="1";


        switch (t)
        {
            case 10:
                fastScore="1";
            break;
            case 20:
                fastScore="2";
            break;
            case 30:
                fastScore="3";
                break;
            case 40:
                fastScore="4";
                break;
            case 50:
                fastScore="5";
                break;
            case 61:
                fastScore="6a";
                break;
            case 62:
                fastScore="6b";
                break;
            case 63:
                fastScore="6c";
                break;
            case 64:
                fastScore="6d";
                break;
            case 65:
                fastScore="6e";
                break;
            case 71:
                fastScore="7a";
                break;
            case 72:
                fastScore="7b";
                break;
            case 73:
                fastScore="7c";
                break;
            case 74:
                fastScore="7d";
                break;
            case 75:
                fastScore="7e";
                break;
            case 76:
                fastScore="7f";
                break;


        }


        return fastScore;

    }

    public List<Note> getNotification()
    {
        List<Note> noteList;
        QueryBuilder<Note> noteQueryBuilder= getNoteDao().queryBuilder();
        noteList = noteQueryBuilder.where(NoteDao.Properties.State.eq(false)).orderDesc(NoteDao.Properties.Id).list();
        return  noteList;


    }
    public int getDowntonScore(Long patientid)
    {

        Log.e("Downton score","patient id "+patientid);
        List<Scale> scaleList = new ArrayList<Scale>();
        List<Sintoma> sintomaList;
        List<Note> noteList;
        int x=0;
        int z=0;
        boolean caidas=false;
        boolean medicacion=false;
        boolean estadomental=false;
        boolean handlerestadomental=false;
        boolean movilidad = false;

        Log.e("downton score","Entro a calcular el Lawton score");
        QueryBuilder sintomaQueryBuilder = getSintomaDao().queryBuilder();
        QueryBuilder noteQueryBuilder = getNoteDao().queryBuilder();

        noteQueryBuilder.where(NoteDao.Properties.PatientId.eq(patientid));
        noteList = noteQueryBuilder.list();

        for(int a=0;a<noteList.size();a++)
        {
            if(noteList.get(a).getAmbito().matches("caidas"))
            caidas = true;
        }


        sintomaQueryBuilder.where(SintomaDao.Properties.Activo.eq(true),SintomaDao.Properties.PatientId.eq(patientid));
        sintomaList = sintomaQueryBuilder.list();



        for(int y=0;y<sintomaList.size();y++) {

            Log.e("downton score", "paciente: " + sintomaList.get(y).getPatientId());
            Log.e("downton score", "sintoma: " + sintomaList.get(y).getSigno());

            if(sintomaList.get(y).getActivo())
            {






            if (sintomaList.get(y).getSigno().matches("medicamentosmedicacion")) {

                medicacion = true;
            }


            if (!handlerestadomental) {
                if (sintomaList.get(y).getAmbito().matches("orientacion")) {
                    estadomental = true;
                    handlerestadomental=true;
                }
            }

            if (sintomaList.get(y).getSigno().matches("movilidad")) {

                movilidad = true;
            }
        }
        }

        if(movilidad)
        {
            z = z+1;
        }
        if(caidas)
        {
            z = z+1;
        }
        if(estadomental)
        {
            z = z+1;
        }
        if(medicacion)
        {
            z = z+1;
        }


        Log.e("downton score","total "+z);

        return z;


    }

    public int getLawtonScore(Long patientid)
    {
        Log.e("lawton score","patient id "+patientid);
        List<Scale> scaleList = new ArrayList<Scale>();
        List<Sintoma> sintomaList;
        int x=0;

        int z=8;



        Log.e("lawton score","Entro a calcular el Lawton score");
        QueryBuilder sintomaQueryBuilder = getSintomaDao().queryBuilder();


        sintomaQueryBuilder.where(SintomaDao.Properties.Activo.eq(true),SintomaDao.Properties.PatientId.eq(patientid));
        sintomaList = sintomaQueryBuilder.list();

        QueryBuilder<Scale> scaleQueryBuilder = getScaleDao().queryBuilder();
        //scaleQueryBuilder.where(ScaleDao.Properties.Escalaname.eq("Blessed"));


        for(int y=0;y<sintomaList.size();y++)
        {

            Log.e("lawton score","paciente: "+sintomaList.get(y).getPatientId());
            Log.e("lawton score","sintoma: "+sintomaList.get(y).getSigno());

            for(int m=0;m<sintomaList.get(y).getScaleList().size();m++)

            {
                String escalatexto = sintomaList.get(y).getScaleList().get(m).getEscalaname().toString();
                Log.e("lawton score","Entre al for sintomaList");
                Log.e("lawton score","getNombreEscala: "+escalatexto);

                if(escalatexto.matches("Lawton"))

                {

                    Log.e("lawton score","Es Lawton");
                    Log.e("lawton score","getNombreEscala: "+sintomaList.get(y).getScaleList().get(m).getEscalaname());
                    Log.e("lawton score","getPuntaje: "+sintomaList.get(y).getScaleList().get(m).getPuntaje());
                    x = x + Integer.parseInt(sintomaList.get(y).getScaleList().get(m).getPuntaje());
                    Log.e("lawton score","x: "+x);

                }
            }
            z = z - x;

            Log.e("lawton score ","acumulado z: "+z);
            x=0;


        }


        return z;

    }

    public int getLikesCount(Long idtip)
    {
        List<Tip> tipList;
        Tip tip;
        int count;
        QueryBuilder<Tip> tipQueryBuilder= getTipDao().queryBuilder();
        tipList = tipQueryBuilder.where(TipDao.Properties.Id.eq(idtip)).list();
        tip = tipList.get(0);
        count = tip.getLikes();
        return count;

    }
    public void addLike(Long idtip)
    {
        List<Tip> tipList;
        Tip tip;
        int count;
        int countadd;

        QueryBuilder<Tip> tipQueryBuilder= getTipDao().queryBuilder();
        tipList = tipQueryBuilder.where(TipDao.Properties.Id.eq(idtip)).list();
        count = tipList.get(0).getLikes();

        countadd = count+1;

        tipList.get(0).setLikes(countadd);
        getTipDao().update(tipList.get(0));
    }

    public PreferenceTip getPreferenceTip(Long idtip, Long iduser)
    {
        PreferenceTip preferenceTip;
        List<PreferenceTip> preferenceTipList;


        QueryBuilder<PreferenceTip> preferenceTipQueryBuilder= getTipPreferenceDao().queryBuilder();
        preferenceTipQueryBuilder.where(PreferenceTipDao.Properties.TipId.eq(idtip));
        preferenceTipQueryBuilder.where(PreferenceTipDao.Properties.UserId.eq(iduser));

        preferenceTipList = preferenceTipQueryBuilder.list();
        return preferenceTip = preferenceTipList.get(0);
    }

    public void insertHistoricScale(Long patientid,String scale, Double score, Date fecha)
    {
        HistoricScore historicScore = new HistoricScore(null,patientid,scale,score,fecha);
        getHistoricScoreDao().insert(historicScore);
    }




}
