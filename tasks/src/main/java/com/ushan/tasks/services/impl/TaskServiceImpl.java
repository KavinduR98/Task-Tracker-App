package com.ushan.tasks.services.impl;

import com.ushan.tasks.domain.entities.Task;
import com.ushan.tasks.domain.entities.TaskList;
import com.ushan.tasks.domain.entities.TaskPriority;
import com.ushan.tasks.domain.entities.TaskStatus;
import com.ushan.tasks.repositories.TaskListRepository;
import com.ushan.tasks.repositories.TaskRepository;
import com.ushan.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {

        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID!");
        }

        if (null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title!");
        }

        TaskPriority taskPriority =  Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }
}