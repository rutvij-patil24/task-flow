package com.team15trello.team15trello.services;

import com.team15trello.team15trello.DTOs.TaskDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.DisplayTaskDTO;
import com.team15trello.team15trello.DTOs.TaskDTOs.SearchTaskDTO;
import com.team15trello.team15trello.DTOs.UpdateTaskDTO;
import com.team15trello.team15trello.entities.Board;
import com.team15trello.team15trello.entities.Task;
import com.team15trello.team15trello.entities.User;
import com.team15trello.team15trello.exceptions.ResourceNotFoundException;
import com.team15trello.team15trello.mappers.*;
import com.team15trello.team15trello.repositories.BoardRepository;
import com.team15trello.team15trello.repositories.TaskRepository;
import com.team15trello.team15trello.repositories.UserRepository;
import com.team15trello.team15trello.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UpdateTaskMapper updateTaskMapper;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private SearchTaskMapper searchTaskMapper;
    @Autowired
    private DisplayTaskMapper displayTaskMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public TaskService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Task> findAll(int pages){return taskRepository.findAll(Pageable.ofSize(pages)).getContent();}
    public Task saveTask(TaskDTO taskDTO){
        User createdBy = getCreatedBy(taskDTO.getCreatedById());
        User assignedTo = getAssignedTo(taskDTO.getAssignedToId());
        Board board = getBoardFromId(taskDTO.getBoardId());

        Task task = taskMapper.toEntity(taskDTO);
        task.setCreatedBy(createdBy);
        task.setAssignedTo(assignedTo);
        task.setBoard(board);

        return taskRepository.save(task);
    }
    public void deleteTask(Long taskId){ taskRepository.deleteById(taskId);}
    public List<DisplayTaskDTO> findTaskByCriteria(SearchTaskDTO userCriteria){
        Task taskUserCriteria = searchTaskMapper.searchTaskDTOToTask(userCriteria);
        Example<Task> example = Example.of(taskUserCriteria);

        // Find all tasks that match the criteria
        List<Task> tasks = taskRepository.findAll(example);

        // Convert the list of Task entities to a list of DisplayTaskDTO
        return tasks.stream()
                .map(task -> displayTaskMapper.taskToDisplayTaskDTO(task, commentMapper))
                .collect(Collectors.toList());
    }
    public List<DisplayTaskDTO> findTaskByID(Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            DisplayTaskDTO displayTaskDTO = displayTaskMapper.taskToDisplayTaskDTO(task, commentMapper);
            return Collections.singletonList(displayTaskDTO);
        } else {
            throw new ResourceNotFoundException("Task with ID " + id + " not found");
        }
    }
    public Task updateTaskState(Long taskId, State newState) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task with ID " + taskId + " not found"));
        task.setState(newState);
        return taskRepository.save(task);
    }
    public Task updateTask(UpdateTaskDTO updateTaskDTO){
        if (!taskRepository.existsById(updateTaskDTO.getTaskId())) {
            throw new ResourceNotFoundException("User with id " + updateTaskDTO.getTaskId() + " not found");
        }
        User createdBy = getCreatedBy(updateTaskDTO.getCreatedById());
        User assignedTo = getAssignedTo(updateTaskDTO.getAssignedToId());
        Board board = getBoardFromId(updateTaskDTO.getBoardId());

        Task task = updateTaskMapper.updateTaskToEntity(updateTaskDTO);
        task.setCreatedBy(createdBy);
        task.setBoard(board);
        task.setAssignedTo(assignedTo);

        return taskRepository.save(task);
    }
    private User getCreatedBy(Long createdById) {
        return userRepository.findById(createdById)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + createdById + " not found"));
    }
    private User getAssignedTo(Long assignedToId) {
        if (assignedToId == null) {
            return null;
        }
        return userRepository.findById(assignedToId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + assignedToId + " not found"));
    }
    private Board getBoardFromId(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board with ID " + boardId + " not found"));
    }

}
