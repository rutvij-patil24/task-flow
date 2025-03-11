package com.team15trello.team15trello.DTOs;

import com.team15trello.team15trello.state.State;

public class UpdateTaskDTO {
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    private Long taskId;
    private State state;
    private String description;
    private int estimationPeriod;

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    private Long boardId;

    public Long getCreatedById() {
        return createdById;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    private Long createdById;
    private Long assignedToId;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
    public void setCreatedById(Long createdBy) {
        this.createdById = createdBy;
    }
    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }
}

