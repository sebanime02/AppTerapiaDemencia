package co.edu.unicauca.appterapiademencia.events;

import java.util.List;

import co.edu.unicauca.appterapiademencia.items.BlessedScoreAverage;

/**
 * Created by ENF on 20/12/2016.
 */

public class BlessedGraphEvent {

    public List<BlessedScoreAverage> getBlessedScoreAverages() {
        return blessedScoreAverages;
    }

    public void setBlessedScoreAverages(List<BlessedScoreAverage> blessedScoreAverages) {
        this.blessedScoreAverages = blessedScoreAverages;
    }

    List<BlessedScoreAverage> blessedScoreAverages;
}
