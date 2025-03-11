package com.team15trello.team15trello.entities;

import com.team15trello.team15trello.state.State;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", state=" + state +
                ", board=" + board +
                ", todoLastEditedTime=" + todoLastEditedTime +
                ", assignedLastEditedTime=" + assignedLastEditedTime +
                ", doingLastEditedTime=" + doingLastEditedTime +
                ", doneLastEditedTime=" + doneLastEditedTime +
                ", description='" + description + '\'' +
                ", estimationPeriod=" + estimationPeriod +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                '}';
    }
    @PrePersist
    public void prePersist() {
        if ( todoLastEditedTime == null) {
            todoLastEditedTime = LocalDateTime.now();
        }
    }

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
        LocalDateTime now = LocalDateTime.now();

        switch (state) {
            case TODO:
                this.todoLastEditedTime = now;
                break;
            case ASSIGNED:
                this.assignedLastEditedTime = now;
                break;
            case DOING:
                this.doingLastEditedTime = now;
                break;
            case DONE:
                this.doneLastEditedTime = now;
                break;
            default:
                break;
        }
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {

        this.assignedTo = assignedTo;
        this.state = State.TODO;
    }

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @Column(name = "state",nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "todo_last_edited_time")
    private LocalDateTime todoLastEditedTime;
    @Column(name = "assigned_last_edited_time")
    private LocalDateTime assignedLastEditedTime;
    @Column(name = "doing_last_edited_time")
    private LocalDateTime doingLastEditedTime;
    @Column(name = "done_last_edited_time")
    private LocalDateTime doneLastEditedTime;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "estimation_period",nullable = false)
    private int estimationPeriod;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board boardId) {
        this.board = boardId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id",referencedColumnName = "board_id",nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by",referencedColumnName = "user_id",nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to",referencedColumnName = "user_id")
    private User assignedTo;

    @OneToMany(mappedBy = "taskId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments;

    // ... existing methods ...

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
