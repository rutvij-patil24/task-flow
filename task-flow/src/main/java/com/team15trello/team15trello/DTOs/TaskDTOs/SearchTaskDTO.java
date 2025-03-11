package com.team15trello.team15trello.DTOs.TaskDTOs;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.state.State;

import java.time.LocalDateTime;

public class SearchTaskDTO {
    private Long taskId;
    private State state;
    private int estimationPeriod;

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

    private BoardDTO board;
    private DisplayUserDTO createdBy;
    private DisplayUserDTO assignedTo;
}
