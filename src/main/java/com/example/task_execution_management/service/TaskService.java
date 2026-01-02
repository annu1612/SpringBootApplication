package com.example.task_execution_management.service;

import com.example.task_execution_management.dto.TaskDto;
import com.example.task_execution_management.entity.Task;
import com.example.task_execution_management.enums.Status;

import java.util.List;

public interface TaskService {


    Task createTask(Task task);

    Task updateTaskStatus(Long id, Status status);

    List<TaskDto> getTasksByExecutionPriority();

}
