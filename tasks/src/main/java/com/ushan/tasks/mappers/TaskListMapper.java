package com.ushan.tasks.mappers;

import com.ushan.tasks.domain.dto.TaskListDto;
import com.ushan.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
