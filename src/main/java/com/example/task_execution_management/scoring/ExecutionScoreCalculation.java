package com.example.task_execution_management.scoring;

import com.example.task_execution_management.entity.Task;
import com.example.task_execution_management.enums.Status;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class ExecutionScoreCalculation {
    public int calculate(Task task) {

        // COMPLETED tasks always 0
        if (task.getStatus() == Status.COMPLETED) {
            return 0;
        }

        int score = 0;

        // Base score
        switch (task.getPriority()) {
            case HIGH -> score += 100;
            case MEDIUM -> score += 70;
            case LOW -> score += 40;
        }

        // Urgency
        LocalDateTime now = LocalDateTime.now();
        if (task.getDueDate().isBefore(now)) {
            score += 50;
        } else if (Duration.between(now, task.getDueDate()).toHours() <= 24) {
            score += 30;
        }

        // Effort penalty
        if (task.getEstimatedEffort() > 16) {
            score -= 40;
        } else if (task.getEstimatedEffort() > 8) {
            score -= 20;
        }

        // Status adjustment
        if (task.getStatus() == Status.IN_PROGRESS) {
            score -= 10;
        }

        // Final rule: never negative
        return Math.max(score, 0);
    }


}
