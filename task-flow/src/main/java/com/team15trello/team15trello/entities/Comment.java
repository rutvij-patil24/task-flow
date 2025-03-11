package com.team15trello.team15trello.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    public Long getCommentId() {
        return commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", taskId=" + taskId +
                '}';
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, updatable = false, insertable = false)
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskId;
    @Column(name = "comment_description")
    private String commentDescription;

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }
}
