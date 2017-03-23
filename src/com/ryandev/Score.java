package com.ryandev;

/**
 * Created by Ryan on 2017-03-22.
 */
public class Score {

    private int points;
    private int maxPoints;

    public Score(int points, int maxPoints) {
        this.points = points;
        this.maxPoints = maxPoints;
    }

    public int getPoints() {
        return points;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
