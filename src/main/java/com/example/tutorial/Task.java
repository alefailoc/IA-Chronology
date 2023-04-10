package com.example.tutorial;

import java.time.LocalDate;

public class Task {

    String task;
    String taskDetails;
    LocalDate dueDate;
    int expectedTimeToTake;

    public Task(String task, String taskDetails, int dueDate, int expectedTimeToTake) {
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

    public getdueDate() {
        return dueDate;
    }

    public void setdueDate(dueDate) {
        this.dueDate = dueDate;
    }

    public int getExpectedTimeToTake() {
        return expectedTimeToTake;
    }

    public void setExpectedTimeToTake(int expectedTimeToTake) {
        this.expectedTimeToTake = expectedTimeToTake;
    }
}
