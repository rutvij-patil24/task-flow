package com.team15trello.team15trello.controllers;

import com.team15trello.team15trello.DTOs.BoardDTOs.BoardDTO;
import com.team15trello.team15trello.DTOs.StateUpdateRequestDTO;
import com.team15trello.team15trello.DTOs.TaskDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.DisplayTaskDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.SearchTaskDTO;
import com.team15trello.team15trello.DTOs.UpdateTaskDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.Task;
import com.team15trello.team15trello.entities.User;
import com.team15trello.team15trello.mappers.CommentMapper;
import com.team15trello.team15trello.mappers.DisplayTaskMapper;
import com.team15trello.team15trello.mappers.SearchTaskMapper;
import com.team15trello.team15trello.mappers.TaskMapper;
import com.team15trello.team15trello.services.TaskService;
import com.team15trello.team15trello.state.State;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private DisplayTaskMapper displayTaskMapper;
    @Autowired
    private SearchTaskMapper searchTaskMapper;
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<DisplayTaskDTO>> findAllTasks(){
        List<Task> tasks = taskService.findAll(10);
        List<DisplayTaskDTO> taskDTOs = tasks.stream()
                .map(task -> displayTaskMapper.taskToDisplayTaskDTO(task, commentMapper)) // Corrected lambda expression
                .collect(Collectors.toList());
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Task> saveTask(@Valid @RequestBody TaskDTO taskDTO){
        return new ResponseEntity<>(taskService.saveTask(taskDTO),HttpStatus.CREATED);
    }
    @PatchMapping
    public ResponseEntity<Task> updateTask(@RequestBody UpdateTaskDTO taskDTO){
        return new ResponseEntity<>(taskService.updateTask(taskDTO),HttpStatus.CREATED);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId){
        taskService.deleteTask(Long.parseLong(taskId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<DisplayTaskDTO>> getTask(SearchTaskDTO searchTaskDTO) {
        List<DisplayTaskDTO> tasks = taskService.findTaskByCriteria(searchTaskDTO);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<List<DisplayTaskDTO>> findTaskById(@PathVariable Long taskId){
        List<DisplayTaskDTO> tasks = taskService.findTaskByID(taskId);
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }
    @PatchMapping("/{taskId}/state")
    public ResponseEntity<Task> updateTaskState(@PathVariable Long taskId, @RequestBody StateUpdateRequestDTO stateUpdateRequest){
        Task updatedTask = taskService.updateTaskState(taskId, stateUpdateRequest.getState());
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

}
