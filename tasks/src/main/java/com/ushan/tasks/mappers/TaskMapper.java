package com.ushan.tasks.mappers;

import com.ushan.tasks.domain.dto.TaskDto;
import com.ushan.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);
}
