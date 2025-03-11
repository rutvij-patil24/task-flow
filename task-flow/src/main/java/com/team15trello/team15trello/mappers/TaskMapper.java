package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.TaskDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.DisplayTaskDTO;
import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.Task;
import com.team15trello.team15trello.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "createdBy.userId", target = "createdById")
    @Mapping(source = "assignedTo.userId", target = "assignedToId")
    @Mapping(source = "board.boardId", target = "boardId")  // This should now be correct.
    TaskDTO toDTO(Task task);

    @Mapping(source = "createdById", target = "createdBy.userId")
    @Mapping(source = "assignedToId", target = "assignedTo.userId")
    @Mapping(source = "boardId", target = "board.boardId")
    Task toEntity(TaskDTO taskDTO);
    }


