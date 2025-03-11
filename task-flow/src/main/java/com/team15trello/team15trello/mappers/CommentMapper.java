package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.CommentDTO;
import com.team15trello.team15trello.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO commentToCommentDTO(Comment comment);
}
