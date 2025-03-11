package com.team15trello.team15trello.controllers;

import com.team15trello.team15trello.entities.Comment;
import com.team15trello.team15trello.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAllComments(){
        return new ResponseEntity<>(commentService.findAllComments(10), HttpStatus.OK);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<Optional<Comment>> findOneComment(@PathVariable String commentId){
        return new ResponseEntity<>(commentService.findOneComment(Long.parseLong(commentId)), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.saveComment(comment),HttpStatus.CREATED);
    }
}
