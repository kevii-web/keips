package com.keviiweb.keips;

/**
 * CCA class to list the breakdown of each student's CCA, its details and the points breakdown.
 */
public class CCA {
    private String name;
    private int attendancePoints;
    private int performancePoints;
    private int outstandingPoints;
    private int totalPoints;

    public CCA(String name, int attPts, int perfPts, int outsPts) {
        this.name = name;
        this.attendancePoints = attPts;
        this.performancePoints = perfPts;
        this.outstandingPoints = outsPts;
        this.totalPoints = attendancePoints + performancePoints + outstandingPoints;
    }

    public String toString() {
        String pointsString = String.format("Name: %s, Attendance = %d, Performance = %d, Outstanding = %d, Total = %d", name, attendancePoints, performancePoints, outstandingPoints, totalPoints);
        return pointsString;
    }
}
