package co.edu.unicauca.appterapiademencia.principal.patientprofile;

import java.util.List;

import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;

/**
 * Created by ENF on 22/12/2016.
 */

public interface StatisticsView {
    void graphBlessedScore(List<BlessedScoreAverage> blessedScoreAverageList);
}
