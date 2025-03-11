package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.TaskDTO;
import com.team15trello.team15trello.DTOs.UpdateTaskDTO;
import com.team15trello.team15trello.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UpdateTaskMapper {
    @Mapping(source = "createdBy.userId", target = "createdById")
    @Mapping(source = "assignedTo.userId", target = "assignedToId")
    @Mapping(source = "board.boardId", target = "boardId")  // This should now be correct.
    UpdateTaskDTO toDTO(Task task);

    @Mapping(source = "createdById", target = "createdBy.userId")
    @Mapping(source = "assignedToId", target = "assignedTo.userId")
    @Mapping(source = "boardId", target = "board.boardId")
    Task updateTaskToEntity(UpdateTaskDTO updateTaskDTO);
}
