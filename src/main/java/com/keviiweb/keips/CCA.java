package com.keviiweb.keips;

import java.util.Comparator;

/**
 * CCA class to list the breakdown of each student's CCA, its details and the points breakdown.
 */
public class CCA {
    private String name;
    private String category;
    private int attendancePoints;
    private int performancePoints;
    private int outstandingPoints;
    private int totalPoints;

    public CCA(String name, String category, int attPts, int perfPts, int outsPts) {
        this.name = name;
        this.category = category;
        this.attendancePoints = attPts;
        this.performancePoints = perfPts;
        this.outstandingPoints = outsPts;
        this.totalPoints = attendancePoints + performancePoints + outstandingPoints;
    }

    public String toString() {
        String pointsString = String.format("Name: %s, Category: %s, Attendance = %d, Performance = %d, Outstanding = %d, " +
                        "Total = %d", name, category, attendancePoints, performancePoints, outstandingPoints, totalPoints);
        return pointsString;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public String getCategory() {
        return category;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getOutstandingPoints() {
        return outstandingPoints;
    }

    public void setOutstandingPoints(int outstandingPoints) {
        this.outstandingPoints = outstandingPoints;
    }

    public void recalculateTotalPoints() {
        this.totalPoints = attendancePoints + performancePoints + outstandingPoints;
    }

}
