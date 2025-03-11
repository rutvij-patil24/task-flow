package com.team15trello.team15trello.DTOs.TaskDTOs;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.CommentDTO;
import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.state.State;

import java.time.LocalDateTime;
import java.util.List;


public class DisplayTaskDTO {
    private Long taskId;
    private State state;
    private LocalDateTime todoLastEditedTime;
    private LocalDateTime assignedLastEditedTime;
    private LocalDateTime doingLastEditedTime;
    private LocalDateTime doneLastEditedTime;
    private String description;
    private int estimationPeriod;
    private BoardDTO board;
    private DisplayUserDTO createdBy;
    private DisplayUserDTO assignedTo;
    private List<CommentDTO> comments;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getTodoLastEditedTime() {
        return todoLastEditedTime;
    }

    public void setTodoLastEditedTime(LocalDateTime todoLastEditedTime) {
        this.todoLastEditedTime = todoLastEditedTime;
    }

    public LocalDateTime getAssignedLastEditedTime() {
        return assignedLastEditedTime;
    }

    public void setAssignedLastEditedTime(LocalDateTime assignedLastEditedTime) {
        this.assignedLastEditedTime = assignedLastEditedTime;
    }

    public LocalDateTime getDoingLastEditedTime() {
        return doingLastEditedTime;
    }

    public void setDoingLastEditedTime(LocalDateTime doingLastEditedTime) {
        this.doingLastEditedTime = doingLastEditedTime;
    }

    public LocalDateTime getDoneLastEditedTime() {
        return doneLastEditedTime;
    }

    public void setDoneLastEditedTime(LocalDateTime doneLastEditedTime) {
        this.doneLastEditedTime = doneLastEditedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimationPeriod() {
        return estimationPeriod;
    }

    public void setEstimationPeriod(int estimationPeriod) {
        this.estimationPeriod = estimationPeriod;
    }

    public BoardDTO getBoard() {
        return board;
    }

    public void setBoard(BoardDTO board) {
        this.board = board;
    }

    public DisplayUserDTO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(DisplayUserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public DisplayUserDTO getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(DisplayUserDTO assignedTo) {
        this.assignedTo = assignedTo;
    }
    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
