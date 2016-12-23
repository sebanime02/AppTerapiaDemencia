package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import co.edu.unicauca.appterapiademencia.R;
import co.edu.unicauca.appterapiademencia.domain.dao.GreenDaoHelper;
import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;
import co.edu.unicauca.appterapiademencia.items.LawtonScoreAverage;
import co.edu.unicauca.appterapiademencia.principal.patientlist.PatientProfileActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by ENF on 22/12/2016.
 */

public class StatisticsActivity extends AppCompatActivity {

    private BarChart blessedChart;
    private StatisticsPresenter statisticsPresenter;
    private GreenDaoHelper daoHelper;
    private Long idsistema;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private Long cedula;
    private BarChart lawtonYearChart;


    public StatisticsActivity()
    {
        daoHelper = GreenDaoHelper.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        blessedChart = (BarChart) findViewById(R.id.blessedYearChart);
        lawtonYearChart = (BarChart) findViewById(R.id.LawtonChart);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Volver A La Ficha de Paciente");
        Bundle extras = getIntent().getExtras();

        if(extras!=null)
        {
            idsistema = extras.getLong("idsistema");
            cedula = extras.getLong("cedula");

        }
        graphBlessedYear(idsistema);
        graphLawtonYear(idsistema);



    }

    public void graphBlessedYear(Long idsistema)
    {
        final String[] labels;
        List<BlessedScoreAverage> blessedScoreAverages;
        blessedScoreAverages = new ArrayList<>();

        try {
            blessedScoreAverages = daoHelper.getScoreYearData(idsistema);

        }catch (Exception e)
        {
            Log.e("Error","Error trayendo datos del año");
        }





        float month;
        float score;
        try {


            blessedChart.setNoDataText("Gráfico de Monitoreo de la demencia segun Blessed, Vacía");
            List<BarEntry> entries = new ArrayList<BarEntry>();
            entries.clear();
            //List<String> labels = new ArrayList<String>();
            labels = new String[blessedScoreAverages.size()];
            //final ArrayList<String> labels= new ArrayList<String>();
            for(int m=0;m<blessedScoreAverages.size();m++)
            {

                score = Float.parseFloat(blessedScoreAverages.get(m).getScore()+"");
                Log.e("score graph"," Score"+score);
                entries.add(new BarEntry(Float.parseFloat(m+""),score));
                labels[m] = blessedScoreAverages.get(m).getMonth();

            }
            entries.size();


            for(int q=0;q<entries.size();q++)
            {
                Log.e("score graph"," Entry x"+entries.get(q).getX());
                Log.e("score graph"," Entry Y"+entries.get(q).getY());
                Log.e("score graph"," Año"+labels[q]);

            }
            if(entries.size()>0)
            {

                Log.e("score graph","Tamaño Entries "+entries);

                BarDataSet dataSet = new BarDataSet(entries, "Escala Demencia Blessed, Interpretación: Por debajo de 4.0 No presenta Demencia,entre 4.0 y 15.0 Hay Sospecha, Por encima de 15.0 Es altamente probable que el paciente tenga Demencia en etapa moderada o grave");
                //dataSet.setDrawFilled(true);
                //dataSet.setDrawCircles(true);
                //dataSet.setColors();

                //dataSet.setColor(getResources().getColor(R.color.white));
                //dataSet.setCircleColor(getResources().getColor(R.color.colorPrimaryDark));
                //dataSet.setColors(getResources().getColor(R.color.white));
                //dataSet.setCircleColors(getResources().getColor(R.color.white));
                dataSet.setColor(getResources().getColor(R.color.colorPrimaryDark));
                dataSet.setValueTextSize(12f);

                //dataSet.setValueTextSize(15f);

                //List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                BarData data = new BarData(dataSet);
                //dataSet.addEntry(dataSet);
                //LineData data = new LineData(dataSets);
                data.setBarWidth(0.6f);
                data.setValueTextSize(15f);

                //blessedChart.setFitBars(true);

                XAxis xAxis = blessedChart.getXAxis();
                YAxis yAxis = blessedChart.getAxisLeft();
                YAxis yAxisright = blessedChart.getAxisRight();


                try {
                    xAxis.setValueFormatter(new IAxisValueFormatter() {

                        public int getDecimalDigits() {
                            return 0;
                        }

                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return labels[((int)value)];
                        }
                    });
                }catch (Exception e){}


                xAxis.setGranularity(1f);

                xAxis.setTextSize(15f);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                blessedChart.getAxisLeft().setDrawGridLines(false);
                blessedChart.getXAxis().setDrawGridLines(false);
                blessedChart.getAxisLeft().setDrawAxisLine(false);



                //xAxis.setGridColor(getResources().getColor(R.color.white));
                //xAxis.setAxisMinimum(Float.parseFloat(0+""));
                //xAxis.setAxisMaximum(Float.parseFloat(32+""));
                yAxis.setTextSize(15f);
                yAxisright.setTextSize(15f);
                yAxisright.setEnabled(false);
                //yAxis.setTextColor(getResources().getColor(R.color.white));
                blessedChart.setData(data);

                Description desc = new Description();
                desc.setText("Monitoreo del la Demencia Segun Escala Blessed");
                //desc.setTextAlign(Paint.Align.CENTER);
                //desc.setTextColor(getResources().getColor(R.color.white));
                desc.setTextSize(12f);
                //blessedChart.setGridBackgroundColor(getResources().getColor(R.color.white));

                blessedChart.setDescription(desc);


                blessedChart.invalidate();
                blessedChart.notifyDataSetChanged();

            }




            //blessedChart.invalidate();

        }catch (Exception e)
        {

        }


    }


    public void graphLawtonYear(Long idsistema)
    {
        final String[] labels;
        List<LawtonScoreAverage> lawtonScoreAverages;
        lawtonScoreAverages = new ArrayList<>();

        try {
            lawtonScoreAverages = daoHelper.getLawtonYearData(idsistema);

        }catch (Exception e)
        {
            Log.e("Error","Error trayendo datos del año");
        }





        float month;
        float score;
        try {


            blessedChart.setNoDataText("Gráfico de Monitoreo de la escala de Depedencia en Actividades Instrumentales, Vacía");
            List<BarEntry> entries = new ArrayList<BarEntry>();
            entries.clear();
            //List<String> labels = new ArrayList<String>();
            labels = new String[lawtonScoreAverages.size()];
            //final ArrayList<String> labels= new ArrayList<String>();
            for(int m=0;m<lawtonScoreAverages.size();m++)
            {

                score = Float.parseFloat(lawtonScoreAverages.get(m).getScore()+"");
                Log.e("score graph"," Score"+score);
                entries.add(new BarEntry(Float.parseFloat(m+""),score));
                labels[m] = lawtonScoreAverages.get(m).getMonth();

            }
            entries.size();


            for(int q=0;q<entries.size();q++)
            {
                Log.e("score graph"," Entry x"+entries.get(q).getX());
                Log.e("score graph"," Entry Y"+entries.get(q).getY());
                Log.e("score graph"," Año"+labels[q]);

            }
            if(entries.size()>0)
            {

                Log.e("score graph","Tamaño Entries "+entries);

                BarDataSet dataSet = new BarDataSet(entries, "Escala AIVD, Lawton y Brody,Interpretación: 0 Muy dependiente, 8 Independiente ");
                //dataSet.setDrawFilled(true);
                //dataSet.setDrawCircles(true);
                //dataSet.setColors();

                //dataSet.setColor(getResources().getColor(R.color.white));
                //dataSet.setCircleColor(getResources().getColor(R.color.colorPrimaryDark));
                //dataSet.setColors(getResources().getColor(R.color.white));
                //dataSet.setCircleColors(getResources().getColor(R.color.white));
                dataSet.setColor(getResources().getColor(R.color.colorPrimaryDark));
                dataSet.setValueTextSize(12f);


                //dataSet.setValueTextSize(15f);

                //List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                BarData data = new BarData(dataSet);
                //dataSet.addEntry(dataSet);
                //LineData data = new LineData(dataSets);
                data.setBarWidth(0.6f);
                data.setValueTextSize(15f);

                //blessedChart.setFitBars(true);

                XAxis xAxis = lawtonYearChart.getXAxis();
                YAxis yAxis = lawtonYearChart.getAxisLeft();
                YAxis yAxisright = lawtonYearChart.getAxisRight();


                try {
                    xAxis.setValueFormatter(new IAxisValueFormatter() {

                        public int getDecimalDigits() {
                            return 0;
                        }

                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            return labels[((int)value)];
                        }
                    });
                }catch (Exception e){}


                xAxis.setGranularity(1f);

                xAxis.setTextSize(15f);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                lawtonYearChart.getAxisLeft().setDrawGridLines(false);
                lawtonYearChart.getXAxis().setDrawGridLines(false);
                lawtonYearChart.getAxisLeft().setDrawAxisLine(false);



                //xAxis.setGridColor(getResources().getColor(R.color.white));
                //xAxis.setAxisMinimum(Float.parseFloat(0+""));
                //xAxis.setAxisMaximum(Float.parseFloat(32+""));
                yAxis.setTextSize(15f);
                yAxisright.setTextSize(15f);
                yAxisright.setEnabled(false);
                //yAxis.setTextColor(getResources().getColor(R.color.white));
                lawtonYearChart.setData(data);

                Description desc = new Description();
                desc.setText("Monitoreo del las Actividades Instrumentales de la vida diaria");
                //desc.setTextAlign(Paint.Align.CENTER);
                //desc.setTextColor(getResources().getColor(R.color.white));
                desc.setTextSize(12f);
                //blessedChart.setGridBackgroundColor(getResources().getColor(R.color.white));

                lawtonYearChart.setDescription(desc);


                lawtonYearChart.invalidate();
                lawtonYearChart.notifyDataSetChanged();

            }




            //blessedChart.invalidate();

        }catch (Exception e)
        {

        }


    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(),PatientProfileActivity.class);
                intent.putExtra("cedula",cedula);
                EventBus.clearCaches();
                EventBus.getDefault().removeAllStickyEvents();
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
