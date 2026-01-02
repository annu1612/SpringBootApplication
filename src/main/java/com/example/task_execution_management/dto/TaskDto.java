package com.example.task_execution_management.dto;


import com.example.task_execution_management.entity.Task;
import lombok.Data;

@Data
public class TaskDto {
    private Task task;
    private int executionScore;

    public TaskDto(Task task, int executionScore) {
        this.task = task;
        this.executionScore = executionScore;
    }
}
