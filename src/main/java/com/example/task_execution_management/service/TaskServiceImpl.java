package com.example.task_execution_management.service;
import com.example.task_execution_management.dto.TaskDto;
import com.example.task_execution_management.entity.Task;
import com.example.task_execution_management.enums.Status;
import com.example.task_execution_management.exception.TaskNotFoundException;
import com.example.task_execution_management.repository.TaskRepository;
import com.example.task_execution_management.scoring.ExecutionScoreCalculation;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ExecutionScoreCalculation scoreCalculation;
    public TaskServiceImpl(TaskRepository taskRepository, ExecutionScoreCalculation scoreCalculation) {
        this.taskRepository = taskRepository;
        this.scoreCalculation = scoreCalculation;
    }
    @Override
    @Transactional
    public Task createTask(Task task) {
        task.setCreatedTime(LocalDateTime.now());
        task.setStatus(Status.NEW);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTaskStatus(Long id, Status status) {
        Task task = taskRepository.
                findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatus(status);
        return taskRepository.save(task);
    }
    @Override
    public List<TaskDto> getTasksByExecutionPriority() {
        return taskRepository.findAll()
                .stream()
                .map(task ->
                        new TaskDto(task, scoreCalculation.calculate(task)))
                .sorted(Comparator.comparingInt(TaskDto::getExecutionScore)
                        .reversed())
                .toList();
    }

}
