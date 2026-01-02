package com.example.task_execution_management.controller;
import com.example.task_execution_management.dto.TaskDto;
import com.example.task_execution_management.entity.Task;
import com.example.task_execution_management.enums.Status;
import com.example.task_execution_management.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return taskService.updateTaskStatus(id, status);
    }

    @GetMapping("/priority-queue")
    public List<TaskDto> priorityQueue() {
        return taskService.getTasksByExecutionPriority();
    }
}


