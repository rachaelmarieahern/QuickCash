package com.example.quickcash.Model;

public class Application {

    private String applicantID, applicationStatus, taskID;

    public Application (){}

    public Application(String applicantID, String applicationStatus, String taskID){
        this.applicantID = applicantID;
        this.applicationStatus = applicationStatus;
        this.taskID = taskID;
    }

    public String getApplicantID() {
        return applicantID;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}
