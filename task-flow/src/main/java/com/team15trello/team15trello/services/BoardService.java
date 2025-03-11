package com.team15trello.team15trello.services;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.CreateBoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.UpdateBoardDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.User;
import com.team15trello.team15trello.mappers.BoardMapper;
import com.team15trello.team15trello.repositories.BoardRepository;
import com.team15trello.team15trello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardMapper boardMapper;

    public Board saveBoard(CreateBoardDTO createBoardDTO) {
        User createdBy = userRepository.findById(createBoardDTO.getCreatedBy())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        Board board = boardMapper.toEntity(createBoardDTO);
        board.setCreatedBy(createdBy);
        return boardRepository.save(board);
    }

    public Board updateBoard(UpdateBoardDTO updateBoardDTO) {
        Board board = boardRepository.findById(updateBoardDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Board ID"));

        board.setName(updateBoardDTO.getName());
        board.setDescription(updateBoardDTO.getDescription());

        User createdBy = userRepository.findById(updateBoardDTO.getCreatedBy())
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
        board.setCreatedBy(createdBy);

        return boardRepository.save(board);
    }
    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Board ID"));
    }
    public List<Board> findBoardByCriteria(BoardDTO boardCriteria){
        Board boardEntityCriteria = boardMapper.toEntity(boardCriteria);
        Example<Board> example = Example.of(boardEntityCriteria);
        return boardRepository.findAll(example);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
}
