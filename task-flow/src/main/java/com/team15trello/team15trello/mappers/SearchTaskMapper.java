package com.team15trello.team15trello.mappers;
import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.SearchTaskDTO;
import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.Task;
import com.team15trello.team15trello.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BoardMapper.class})
public interface SearchTaskMapper {
    SearchTaskMapper INSTANCE = Mappers.getMapper(SearchTaskMapper.class);

    @Mapping(source = "board", target = "board", qualifiedByName = "BoardToEntity")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "UserToEntity")
    @Mapping(source = "assignedTo", target = "assignedTo", qualifiedByName = "UserToEntity")
    Task searchTaskDTOToTask(SearchTaskDTO searchTaskDTO);

    default Board boardDtoToBoard(BoardDTO boardDTO) {
        return Mappers.getMapper(BoardMapper.class).toEntity(boardDTO);
    }

    default User displayUserDtoToUser(DisplayUserDTO displayUserDTO) {
        return Mappers.getMapper(UserMapper.class).toEntity(displayUserDTO);
    }
}
