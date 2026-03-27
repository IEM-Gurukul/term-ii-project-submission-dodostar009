package com.attendance.observer;

public class AlertObserver implements AttendanceObserver {
    private double threshold;
    private String alertLog = "";

    public AlertObserver(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update(String studentName, String subject, double attendancePercentage) {
        if (attendancePercentage < threshold) {
            String alert = "ALERT: " + studentName + " attendance in " +
                    subject + " is " + String.format("%.1f", attendancePercentage) +
                    "% (below " + threshold + "%)";
            alertLog += alert + "\n";
            System.out.println(alert);
        }
    }

    public String getAlertLog() {
        return alertLog;
    }
}
