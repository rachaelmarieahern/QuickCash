package com.example.quickcash;

import android.app.Application;

import com.example.quickcash.Util.ErrorTypes;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TaskUnitTest {
    TaskViewModel taskViewModel;

    @Before public void initializeViewModel(){
        taskViewModel = new TaskViewModel();
        taskViewModel.description = "";
        taskViewModel.headLine = "";
        taskViewModel.price = "25";
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
    public void invalidPrice(){
        taskViewModel.price = "0.0";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errors.contains(ErrorTypes.invalidPrice));
    }

    @Test
    public void validPrice(){
        taskViewModel.price = "10.0";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertFalse(taskViewModel.errors.contains(ErrorTypes.invalidPrice));
    }
}
