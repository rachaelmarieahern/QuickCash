package com.example.quickcash.Model;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String headline;
    private String description;
    private Date startDate;
    private Date endDate;
    private String wage;
    private String taskType;
    private boolean urgent;
    private double longitude, latitude;
    private String databaseId;
    private String author;
    private ArrayList<Application> applicants = new ArrayList<Application>();


    public Task(){}

    public Task(String headLine, String description, Date startDate, Date endDate, boolean urgent,
                double longitude, double latitude, String wage, String taskType, String author) {
        this.headline = headLine;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.urgent = urgent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.wage = wage;
        this.taskType = taskType;
        this.author = author;
        this.applicants = null;
    }

    //getters
    public String getWage() { return wage; }
    public String getTaskType() {return taskType;}
    public Date getEndDate() { return endDate; }
    public Date getStartDate() { return startDate; }
    public String getDescription() { return description; }
    public String getHeadline() { return headline; }
    public boolean isUrgent() { return urgent; }
    public double getLongitude() { return longitude; }
    public double getLatitude() { return latitude; }
    public String getTaskDatabaseID() { return databaseId; }

    //setters
    public void setHeadline(String headline) { this.headline = headline; }
    public void setDescription(String description) { this.description = description; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setTaskType(String taskType) {this.taskType = taskType;}
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setWage(String wage) { this.wage = wage; }
    public void setUrgent(boolean urgent) { this.urgent = urgent; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setTaskDatabaseID(String taskDatabaseID) { this.databaseId = taskDatabaseID; }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<Application> getApplicants() {
        return applicants;
    }

    public void addApplicant(Application applicant) {
        applicants.add(applicant);
    }
}