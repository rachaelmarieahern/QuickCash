package com.example.quickcash.Model;
import java.util.Date;

public class Task {
    private String headline;
    private String description;
    private Date startDate;
    private Date endDate;
    private String wage;
    private boolean urgent;
    private double longitude, latitude;
    private int projectDays, projectHours, projectMinutes;


    public Task(){}

    public Task(String headLine, String description, Date startDate, Date endDate, boolean urgent,
                double longitude, double latitude, String wage, int projectDays, int projectHours, int projectMinutes) {
        this.headline = headLine;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.urgent = urgent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.wage = wage;
        this.projectDays = projectDays;
        this.projectHours = projectHours;
        this.projectMinutes = projectMinutes;
    }

    //getters
    public int getProjectMinutes() { return projectMinutes; }
    public int getProjectHours() { return projectHours; }
    public int getProjectDays() { return projectDays; }
    public String getWage() { return wage; }
    public Date getEndDate() { return endDate; }
    public Date getStartDate() { return startDate; }
    public String getDescription() { return description; }
    public String getHeadline() { return headline; }
    public boolean isUrgent() { return urgent; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }

    //setters
    public void setProjectDays(int projectDays) { this.projectDays = projectDays; }
    public void setProjectHours(int projectHours) { this.projectHours = projectHours; }
    public void setProjectMinutes(int projectMinutes) { this.projectMinutes = projectMinutes; }
    public void setHeadline(String headline) { this.headline = headline; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setWage(String wage) { this.wage = wage; }
    public void setUrgent(boolean urgent) { this.urgent = urgent; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

}