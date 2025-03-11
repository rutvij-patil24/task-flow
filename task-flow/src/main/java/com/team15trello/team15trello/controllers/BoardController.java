package com.team15trello.team15trello.controllers;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.CreateBoardDTO;
import com.team15trello.team15trello.DTOs.BoardDTOs.UpdateBoardDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards(){
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @PutMapping
    public ResponseEntity<Board> createBoard(@RequestBody CreateBoardDTO createBoardDTO) {
        return ResponseEntity.ok(boardService.saveBoard(createBoardDTO));
    }
    @PatchMapping
    public ResponseEntity<Board> updateBoard(@RequestBody UpdateBoardDTO updateBoardDTO) {
        return ResponseEntity.ok(boardService.updateBoard(updateBoardDTO));
    }
    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoardById(boardId));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Board>> searchBoard(BoardDTO boardDTO) {
        List<Board> boards = boardService.findBoardByCriteria(boardDTO);
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }
}