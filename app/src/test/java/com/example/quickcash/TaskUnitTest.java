package com.example.quickcash;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TaskUnitTest {
    AddTaskViewModel taskViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before public void initializeViewModel(){
        //Application mockApplication = mock(Application.class);
        taskViewModel = new AddTaskViewModel();
        taskViewModel.description = "";
        taskViewModel.headLine = "";
        taskViewModel.wage = "25";
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,0,1);
        taskViewModel.startDate = calendar.getTime();
        calendar.set(2000,0,1);
        taskViewModel.endDate = calendar.getTime();
    }


    @Test
    public void headlineInvalid(){
        taskViewModel.headLine = "AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errors.contains(ErrorTypes.invalidHeadline));
    }

    @Test
    public void headlineValid(){
        taskViewModel.headLine = "AHHHHHHHHHHHHHHHHHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertFalse(taskViewModel.errors.contains(ErrorTypes.invalidHeadline));
    }

    @Test
    public void descriptionInvalid(){
        taskViewModel.description = "AHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errors.contains(ErrorTypes.invalidDescription));
    }

    @Test
    public void descriptionValid(){
        taskViewModel.description = "AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertFalse(taskViewModel.errors.contains(ErrorTypes.invalidDescription));
    }

    //TODO: Test cases for location
    @Test
    public void latitudeValid(){
    }
    @Test
    public void longitudeValid(){
    }

    @Test
    public void dateOrder(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,11,06);
        taskViewModel.startDate = calendar.getTime();
        calendar.set(2021,11,06);
        taskViewModel.endDate = calendar.getTime();
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.startDate.before(taskViewModel.endDate));
    }



    @Test
    public void invalidWage(){
        taskViewModel.wage = "0.0";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errors.contains(ErrorTypes.invalidWage));
    }

    @Test
    public void validWage(){
        taskViewModel.wage = "10.0";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertFalse(taskViewModel.errors.contains(ErrorTypes.invalidWage));
    }

    @Test
    public void validateAddingTask(){
        //define variables in taskview model
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2012,12,25);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2013,11,11);
        taskViewModel.headLine = "Headline 1";
        taskViewModel.description = "This is a description for a new task";
        taskViewModel.startDate = calendarStart.getTime();
        taskViewModel.endDate = calendarEnd.getTime();
        taskViewModel.urgent = false;
        taskViewModel.latitude = 111.232;
        taskViewModel.longitude = 145.112;
        taskViewModel.wage = "25.00";
        //taskViewModel.projectDays = 321;
        //taskViewModel.projectHours = 0;
        //taskViewModel.projectMinutes = 0;
    }
}
