package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.CommentDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.DisplayTaskDTO;
import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.Comment;
import com.team15trello.team15trello.entities.Task;
import com.team15trello.team15trello.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BoardMapper.class,CommentMapper.class})
public interface DisplayTaskMapper {

    @Mapping(source = "board", target = "board", qualifiedByName = "boardToBoardDTO")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "userToUserDTO")
    @Mapping(source = "assignedTo", target = "assignedTo", qualifiedByName = "userToUserDTO")
    @Mapping(target = "comments", expression = "java(mapComments(task.getComments(), commentMapper))")
    DisplayTaskDTO taskToDisplayTaskDTO(Task task, @Context CommentMapper commentMapper);

    default List<CommentDTO> mapComments(Set<Comment> comments, @Context CommentMapper commentMapper) {
        return comments.stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Named("boardToBoardDTO")
    default BoardDTO boardToBoardDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setBoardId(board.getBoardId());
        dto.setName(board.getName());
        dto.setDescription(board.getDescription());
        dto.setCreatedBy(board.getCreatedBy().getUserId());
        return dto;
    }

    @Named("userToUserDTO")
    default DisplayUserDTO userToUserDTO(User user) {
        return Mappers.getMapper(UserMapper.class).userToUserDTO(user);
    }
}
