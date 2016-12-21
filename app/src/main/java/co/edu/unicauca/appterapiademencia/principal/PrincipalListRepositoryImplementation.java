package co.edu.unicauca.appterapiademencia.principal;

import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Note;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.domain.Tip;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.domain.dao.NoteDao;
import co.edu.unicauca.appterapiademencia.domain.dao.PatientDao;
import co.edu.unicauca.appterapiademencia.domain.dao.TipDao;
import co.edu.unicauca.appterapiademencia.domain.dao.UserDao;
import co.edu.unicauca.appterapiademencia.events.BlessedEvent;
import co.edu.unicauca.appterapiademencia.events.BlessedGraphEvent;
import co.edu.unicauca.appterapiademencia.events.NotificationEvent;
import co.edu.unicauca.appterapiademencia.events.PatientListEvent;
import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;
import co.edu.unicauca.appterapiademencia.lib.EventBus;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;

/**
 * Created by ENF on 26/10/2016.
 */

public class PrincipalListRepositoryImplementation implements PrincipalListRepository {
    private GreenDaoHelper helper;
    private UserDao userDao;
    private PatientDao patientDao;
    private TipDao tipDao;
    private boolean accessType;
    private QueryBuilder queryBuildergeneral;
    private Patient patient;
    private List<Patient> patientList;

    public PrincipalListRepositoryImplementation(){
        this.helper = GreenDaoHelper.getInstance();
        this.userDao = helper.getUserDao();
        this.patientDao = helper.getPatientDao();
        this.tipDao = helper.getTipDao();
        this.queryBuildergeneral = helper.getPatientDao().queryBuilder();
    }

    @Override
    public Patient getPatientData(Long id) {
        /*

       List<Patient> patientList = queryBuildergeneral.where(PatientDao.Properties.Identity.eq(id)).limit(1).list();
        Log.d("Repositorio","devolvio el nombre: "+patientList.get(0).getName());
        return patientList.get(0);
        */
        try
        {
            patient = helper.getPatientInformationUsingCedula(id);
            Log.d("Repositorio","devolvio el nombre: "+patient.getName());

        }catch (Exception e)
        {
            postEvent(PatientListEvent.onPatientGetDataError,0);
        }
        return patient;



    }

    @Override
    public void addPatient() {

    }

    @Override
    public void showPatients()
    {

    }

    @Override
    public List<Patient> getPatients() {
   /*Patient prueba = new Patient(null,"Orlando Ñ","18/04/1995","","cosmitet",11111,"ninguno",null,null,null,null,0,0,0);
        this.patientDao.insert(prueba);
           */
        try
        {
            QueryBuilder qbpatients = helper.getPatientDao().queryBuilder();
            List patients = qbpatients.orderDesc(PatientDao.Properties.Id).list();
            patientList = patients;
            int i;
            for(i=0; i<patientList.size();i++){
                Log.e("List Patients","Paciente con nombre "+patientList.get(i).getName());
            }
            Log.e("List Patients","Envia al postlistevent la lista");
        }
        catch (Exception e)
        {
            postEvent(PatientListEvent.onPatientListError,1);

        }

        return patientList;

    }

    @Override
    public void delete(Patient patient) {

    }

    @Override
    public void insertTip() {

    }

    @Override
    public List<Tip> getTips() {
        List<Tip> tipList;
        queryBuildergeneral = tipDao.queryBuilder();
        tipList = queryBuildergeneral.orderDesc(TipDao.Properties.Likes).list();
        return tipList;

    }

    @Override
    public void getNotifications() {
       List<Note> notificationList;
        try {
            notificationList = helper.getNotification();


            EventBus eventBus = GreenRobotEventBus.getInstance();

            NotificationEvent stickyevent = de.greenrobot.event.EventBus.getDefault().removeStickyEvent(NotificationEvent.class);
            if(stickyevent!=null) {
                eventBus.removeSticky(stickyevent);
            }
            if(stickyevent==null)
            {
                NotificationEvent notificationEvent = new NotificationEvent();
                notificationEvent.setNoteList(notificationList);
                Log.e("notesrepository","va a enviar el evento notificationEvent");
                Log.e("Principal Repository", "Va a registrar el evento");
                eventBus.postSticky(notificationEvent);

            }


        }catch (Exception e)
        {}

    }

    @Override
    public void aprobeNotifications(List<Annotation> annotationsList)
    {

    }

    @Override
    public void changeUserData(HashMap<String, Object> hashMap)
    {

    }

    @Override
    public List<Note> getNotes(Long id)
    {
        Patient patient = helper.getPatientInformationUsingCedula(id);

        Log.e("addnote","llege a getnotes del repositorio");
        QueryBuilder<Note> queryBuilder = helper.getNoteDao().queryBuilder();
        queryBuilder.where(NoteDao.Properties.State.eq(true));


        //queryBuilder.join(Note.class,PatientDao.Properties.Id).where(PatientDao.Properties.Id.eq(id));
        //queryBuilder.join(NoteDao.Properties.PatientId,Patient.class,patientDao.getPkProperty()).where(PatientDao.Properties.Identity.eq(id));
        queryBuilder.where(NoteDao.Properties.PatientId.eq(patient.getId()));
        queryBuilder.orderDesc(NoteDao.Properties.Id);


      try {


            for(int i=0;i<=queryBuilder.list().size();i++){
                Log.e("Repositorio notas",queryBuilder.list().get(i).getDescription());
            }

      }catch (IndexOutOfBoundsException e)
      {
          Log.e("Repositorio notas","ninguno");
      }

        return queryBuilder.list();



    }

    @Override
    public Double getBlessedScore(Long id) {
        Double score;
        score = helper.getBlessedScore(id);
       /*try
       {
           score = helper.getBlessedScore(id);

       }catch (Exception e)
       {
           Log.e("repositorio princi","Excepcion getBlessedScore");
           score=0.0;
           postEvent(BlessedEvent.onBlessedScoreError,2);

       }
       */
        return score;
    }

    @Override
    public Note getNote(Long idnote) {
        Note note;
        //try {
            Log.e("notesrepository","llego al repositorio, va a ejecutar el getnote");
            Log.e("notesrepository","El id es"+idnote);
                note = helper.getNote(idnote);
                return note;

        /*de.greenrobot.event.EventBus.getDefault().removeAllStickyEvents();

                NoteEvent noteEvent = new NoteEvent();
                noteEvent.setNote(note);
                Log.e("notesrepository","va a enviar el evento noteEvent");

                EventBus eventBus = GreenRobotEventBus.getInstance();



                Log.e("Principal Repository","Va a registrar el evento");
                eventBus.post(noteEvent);

                */



        //       }catch (Exception e){ Log.e("notesrepository","Error al traer la nota con id");}
    }

    @Override
    public void deleteNote(Long idnote) {

        NoteDao noteDao;
        try {

            Note note = helper.getNote(idnote);

            noteDao = helper.getNoteDao();
            noteDao.delete(note);


        }catch (Exception e){}

    }

    @Override
    public void aprobeNote(Long idnote) {
        NoteDao noteDao;
        try {

            Note note = helper.getNote(idnote);

            noteDao = helper.getNoteDao();
            note.setState(true);
            noteDao.update(note);



        }catch (Exception e){}
    }

    @Override
    public String getFastScore(Long id) {

         return helper.getFASTScore(id);
    }

    @Override
    public int getLawtonScore(Long id) {
        return helper.getLawtonScore(id);
    }

    @Override
    public int getDowntonScore(Long id) {

        int Score;

        Log.e("downscore","llegue repositorio");
        Score = helper.getDowntonScore(id);
        Log.e("downscore","el score sacado"+Score);


        return Score;

    }

    @Override
    public void getBlessedData(Long id) {


            BlessedGraphEvent graphEvent = new BlessedGraphEvent();
            List<BlessedScoreAverage> averageList;
            averageList = helper.getScoreData(id);
            Log.e("Principal Repository","Trae la Lista");

            graphEvent.setBlessedScoreAverages(averageList);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            Log.e("Principal Repository","Va a registrar el evento");
            eventBus.post(graphEvent);




    }


    private void postEvent(int type,int typemethod){
        //typemethod==0 para getPatientData, typemethod==1 para getPatientList


        if(typemethod==0)
        {
            PatientListEvent patientListEvent = new PatientListEvent();
            patientListEvent.setEventType(type);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            Log.e("Principal Repository","Va a registrar el evento");
            eventBus.post(patientListEvent);
        }

        if(typemethod==1)
        {
            PatientListEvent patientListEvent = new PatientListEvent();
            patientListEvent.setEventType(type);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            Log.e("Principal Repository","Va a registrar el evento");
            eventBus.post(patientListEvent);
        }
        if(typemethod==2)
        {
            BlessedEvent blessedEvent = new BlessedEvent();
            blessedEvent.setEventType(type);

            EventBus eventBus = GreenRobotEventBus.getInstance();
            eventBus.post(blessedEvent);
        }


    }
}
