package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.CreateBoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.UpdateBoardDTO;
import com.team15trello.team15trello.entities.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BoardMapper {
    @Mapping(source = "createdBy.userId", target = "createdBy")
    BoardDTO toDTO(Board board);

    @Mapping(source = "createdBy", target = "createdBy.userId")
    Board toEntity(CreateBoardDTO createBoardDTO);

    @Named("BoardToEntity")
    @Mapping(source = "createdBy", target = "createdBy.userId")
    Board toEntity(BoardDTO boardDTO);

    @Mapping(source = "createdBy", target = "createdBy.userId")
    Board toEntity(UpdateBoardDTO updateBoardDTO);

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardDTO boardToBoardDTO(Board board);
}
