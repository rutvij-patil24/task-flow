package com.team15trello.team15trello.services;

import com.team15trello.team15trello.entities.Comment;
import com.team15trello.team15trello.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    public List<Comment> findAllComments(int pageSize){ return commentRepository.findAll(Pageable.ofSize(pageSize)).getContent();}
    public Optional<Comment> findOneComment(Long commentId){return commentRepository.findById(commentId);}
    public Comment saveComment(Comment comment){return commentRepository.save(comment);}
}
