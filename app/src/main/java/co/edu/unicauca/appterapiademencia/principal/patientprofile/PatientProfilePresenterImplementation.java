package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.util.Log;

import co.edu.unicauca.appterapiademencia.domain.HistoricScore;
import co.edu.unicauca.appterapiademencia.domain.Patient;
import co.edu.unicauca.appterapiademencia.events.BlessedEvent;
import co.edu.unicauca.appterapiademencia.events.BlessedGraphEvent;
import co.edu.unicauca.appterapiademencia.lib.GreenRobotEventBus;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractor;
import co.edu.unicauca.appterapiademencia.principal.PrincipalListInteractorImplementation;

/**
 * Created by SEBAS on 07/11/2016.
 */

public class PatientProfilePresenterImplementation implements PatientProfilePresenter {

    private PrincipalListInteractor principalListInteractor;
    private PatientProfileView patientProfileView;
    private Patient patientObject;
    private GreenRobotEventBus eventBus;

    public PatientProfilePresenterImplementation(PatientProfileView patientProfileView){
        this.patientProfileView = patientProfileView;
        this.principalListInteractor = new PrincipalListInteractorImplementation();
        this.eventBus = GreenRobotEventBus.getInstance();


    }



    @Override
    public void showPatientData(Patient patient) {
        Log.d("Devuelta presentador","nombre"+patient.getName().toString());
            patientProfileView.showPatientData(patient);


    }

    @Override
    public void changePhoto() {

    }

    @Override
    public void getPatientData(Long id) {

       showPatientData(principalListInteractor.getPatientData(id));


    }



    @Override
    public void editPatientData(Long id) {

    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        patientProfileView = null;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void getBlessedScore(Long id) {

        Double score;
        String comentario = "No presenta demencia por el momento";
        score = principalListInteractor.getBlessedScore(id);

        if(patientProfileView!=null)
        {
            if(score>=4.0 || score<=15.0)
            {
            comentario ="Hay sospecha de Demencia en el Paciente";
                patientProfileView.showBlessedScore(score,comentario,"verde");
            }
            if(score<4.0){
                comentario = "No presenta demencia por el momento";
                patientProfileView.showBlessedScore(score,comentario,"amarillo");

            }
            if(score>=15.0)
            {
                comentario = "Es altamente probable que el paciente tenga Demencia en etapa moderada o grave";
                patientProfileView.showBlessedScore(score,comentario,"rojo");

            }



        }
    }

    @Override
    public void onEventMainThread(BlessedEvent event) {
        switch (event.getEventType())
        {
            case BlessedEvent.onBlessedScoreError:

            break;
        }
    }

    @Override
    public void onEventMainThread(BlessedGraphEvent event) {
        Log.e("Presenter","LLego al presenter de blessedGraphEvent");
        if(patientProfileView!=null)
        {
            Log.e("Presenter","Ejecuta el metodo para pasar el average a la vista");

            patientProfileView.graphBlessedScore(event.getBlessedScoreAverages());
        }
    }

    @Override
    public void getFastScore(Long id) {

        String score="1";
        String etapa="Normal";
        String caracteristica="No hay déficit";
        String edadMental="Adulto";
        //String gds="GDS 1, Ausencia de déficit cognitivo";
        String gds="1.0";

        String mec="Normal,30-35";
        score = principalListInteractor.getFastScore(id);

        if(patientProfileView!=null)
        {
            switch (score)
            {
                case "1":
                    etapa="Normal";
                    caracteristica="No hay déficit";
                    edadMental="Adulto";
                    mec="Normal,30-35";
                    //gds="GDS 1, Ausencia de déficit cognitivo";
                    gds="1.0";
                    break;
                case "2":
                    etapa="Normal para su edad";
                    caracteristica="Déficit funcional subjetivo";
                    edadMental="";
                    mec="Normal para su olvido,25-30";
                    //gds="GDS 2, Déficit Cognitivo Muy Leve";
                    gds="2.0";
                    break;
                case "3":
                    etapa="Deterioro Cognitivo Leve";
                    caracteristica="Déficit funcional Objetivo, Interfiere con la mayoria de tareas complejas";
                    edadMental="12+";
                    mec="Deterioro límite, 20-27";
                    //gds="GDS 3, Déficit Cognitivo Leve";
                    gds="3.0";
                    break;
                case "4":
                    etapa="Demencia Leve";
                    caracteristica="Actividades Instrumentales Diarias Afectadas: Pagar, cocinar, limpiar.";
                    edadMental="8-12 años";
                    mec="Deterioro Límite, MEC 16-23";
                    //gds="GDS 4, Déficit Cognitivo Moderado";
                    gds="4.0";
                    break;
                case "5":
                    etapa="Demencia Moderada";
                    caracteristica="Necesita Ayuda para seleccionar su Ropa.";
                    edadMental="5-7 años";
                    mec="Enfermedad Alzheimer Moderada, MEC 10-19";
                    //gds="GDS 5, Déficit Cognitivo Moderadamente Grave";
                    gds="5.0";
                    break;
                case "6a":
                    etapa="Demencia Moderadamente Severa";
                    caracteristica="Necesita ayuda para vestirse";
                    edadMental="5 años";
                    mec="Enfermedad Alzheimer Moderadamente Grave, MEC 0-12";
                    //gds="GDS 6, Déficit Cognitivo Grave";
                    gds="6.0";
                    break;
                case "6b":
                    etapa="Demencia Moderadamente Severa";
                    caracteristica="Necesita ayuda para bañarse";
                    edadMental="4 años";
                    mec="Enfermedad Alzheimer Moderadamente Grave, MEC 0-12";
                    //gds="GDS 6, Déficit Cognitivo Grave";
                    gds="6.0";
                    break;
                case "6c":
                    etapa="Demencia Moderadamente Severa";
                    caracteristica="Necesita ayuda para usar el inodoro";
                    edadMental="4 años";
                    mec="Enfermedad Alzheimer Moderadamente Grave, MEC 0-12";
                    gds="GDS 6, Déficit Cognitivo Grave";
                    gds="6.0";
                    break;
                case "6d":
                    etapa="Demencia Moderadamente Severa";
                    caracteristica="Incontinensia Urinaria";
                    edadMental="3-4 años";
                    mec="Enfermedad Alzheimer Moderadamente Grave, MEC 0-12";
                    //gds="GDS 6, Déficit Cognitivo Grave";
                    gds="6.0";
                    break;

                case "6e":
                    etapa="Demencia Moderadamente Severa";
                    caracteristica="Incontinensia Fecal";
                    edadMental="2-3 años";
                    mec="Enfermedad Alzheimer Moderadamente Grave, MEC 0-12";
                    //gds="GDS 6, Déficit Cognitivo Grave";
                    gds="6.0";
                    break;

                case "7a":
                    etapa="Demencia Severa";
                    caracteristica="Habla tan solo 5 a 6 palabras durante el dia";
                    edadMental="1,25 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Muy Grave";
                    gds="7.0";
                    break;


                case "7b":
                    etapa="Demencia Severa";
                    caracteristica="Habla solamente 1 palabra claramente";
                    edadMental="1 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Muy Grave";
                    gds="7.0";
                    break;
                case "7c":
                    etapa="Demencia Severa";
                    caracteristica="No Puede Caminar";
                    edadMental="1 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Muy Grave";
                    gds="7.0";
                    break;
                case "7d":
                    etapa="Demencia Severa";
                    caracteristica="No puede sentarse de manera independiente";
                    edadMental="0,5-0,8 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Muy Grave";
                    gds="7.0";
                    break;
                case "7e":
                    etapa="Demencia Severa";
                    caracteristica="Ya no Sonrie";
                    edadMental="0,2-0,4 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Muy Grave";
                    gds="7.0";
                    break;
                case "7f":
                    etapa="Demencia Severa";
                    caracteristica="No puede sontener su Cabeza sin asistencia";
                    edadMental="0-0,2 años";
                    mec="Enfermedad de Alzheimer grave, MEC 0";
                    //gds="GDS 7, Déficit Cognitivo Grave";
                    gds="7.0";
                    break;


            }




            patientProfileView.showFastScore(score,etapa,caracteristica,edadMental,mec,gds);
        }
    }

    @Override
    public void getMMSEScore(Long id) {

        int score;
        int day;
        int month;
        int year;
        String fecha;
        HistoricScore historicScore;


        try {
             historicScore = principalListInteractor.getMMSEScore(id);
             day = historicScore.getDay();
             month=historicScore.getMonth()+1;
            year = historicScore.getYear();
             patientProfileView.showMMSEScore(historicScore.getValue().toString(),day+"-"+month+"-"+year);

        }catch (Exception e)
        {
            e.printStackTrace();
            patientProfileView.showMMSEScore("","");

        }
    }

    @Override
    public void getDowntonScore(Long id) {
        int score;
        String comentario;

        score = principalListInteractor.getDowntonScore(id);

        try
        {

            if(score>2)
            {
                comentario="Alto Riesgo De Caida";
            }
            else
            {
                comentario="Riesgo de Caida Bajo";

            }

            patientProfileView.showDowntonScore(score,comentario);

        }catch (Exception e)
        {}
    }

    @Override
    public void getLawtonScore(Long id) {
        int score;
        String comentario;
        try {
            score = principalListInteractor.getLawtonScore(id);

            if(score==0)
            {
                comentario="Máxima Dependencia";
            }
            else if(score==8)
            {
                comentario="Mínima Dependencia";
            }
            else
            {
                comentario="";
            }

            if(patientProfileView!=null)
            {
                patientProfileView.showLawtonScore(score,comentario);

            }





        }catch (Exception e)
        {

        }



    }

    @Override
    public void getBlessedData(Long id,int mode) {
        Log.e("presenter graph","modo "+mode);
        principalListInteractor.getBlessedData(id,mode);
    }

    @Override
    public void handleBlessedGraph(int selector) {

    }


}
