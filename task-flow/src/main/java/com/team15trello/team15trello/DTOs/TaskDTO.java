package com.team15trello.team15trello.DTOs;

import com.team15trello.team15trello.state.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Builder;


public class TaskDTO {
    @NotNull(message = "Invalid Task: Task state is NULL")
    private State state;

    @NotNull(message = "Invalid Description: Task Description should not be NULL")
    @NotBlank(message = "Invalid Description: Empty Description")
    @Size(min = 3, max = 30, message = "Invalid Description: Must be of 3 - 30 characters")
    private String description;
    @Min(value = 1, message = "Estimation period must be at least 1")
    @Max(value = 365, message = "Estimation period cannot exceed 365 days")
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
