package com.example.tutorial;

import java.util.Date;

public class Task {

    String task;
    String taskDetails;
    Date dueDate;
    int expectedTimeToTake;

    public Task(String task, String taskDetails, Date dueDate, int expectedTimeToTake) {
        this.task = task;
        this.taskDetails = taskDetails;
        this.dueDate = dueDate;
        this.expectedTimeToTake = expectedTimeToTake;
    }


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Date getdueDate() {
        return dueDate;
    }

    public void setdueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getExpectedTimeToTake() {
        return expectedTimeToTake;
    }

    public void setExpectedTimeToTake(int expectedTimeToTake) {
        this.expectedTimeToTake = expectedTimeToTake;
    }
}
