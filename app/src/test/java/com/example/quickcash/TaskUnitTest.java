package com.example.quickcash;

import com.example.quickcash.Util.ErrorTypes;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TaskUnitTest {
    TaskViewModel taskViewModel = new TaskViewModel();

    @Test
    public void headlineCharacterErrorMessage(){
        taskViewModel.headLine = "AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errorMessage.equals("Headline contains too many characters!"));
    }

    @Test
    public void headlineNewLineErrorMessage(){
        taskViewModel.headLine = "AHHHHHH" + "\n" + "HHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errorMessage.equals("Headline must remain on one line!"));
    }

    @Test
    public void headlineValid(){
        taskViewModel.headLine = "AHHHHHHHHHHHHHHHHHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertFalse(taskViewModel.errors.contains(ErrorTypes.invalidHeadline));
    }

    @Test
    public void descriptionCharacterErrorMessage(){
        taskViewModel.description = "AHHHHHH";
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertEquals(taskViewModel.errorMessage, "Headline contains too few characters!");
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
    public void validPrice(){
        taskViewModel.price = 0.0;
        taskViewModel.validateInfo();
        taskViewModel.addTaskClicked();
        assertTrue(taskViewModel.errors.contains(ErrorTypes.invalidPrice));
    }
}
