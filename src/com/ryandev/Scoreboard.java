package com.ryandev;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 2017-03-22.
 */
public class Scoreboard {

    private List<Score> allScores = new ArrayList<>();

    public Scoreboard() {
    }

    public void addScore(int points, int maxPoints) {
        Score score = new Score(points, maxPoints);
        allScores.add(score);
    }

    public List<Score> getAllScores() {
        return allScores;
    }
}
