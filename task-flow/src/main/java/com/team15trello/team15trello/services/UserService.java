package com.team15trello.team15trello.services;

import com.team15trello.team15trello.entities.User;
import com.team15trello.team15trello.exceptions.ResourceNotFoundException;
import com.team15trello.team15trello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        System.out.println("User:"+user.toString());
        return userRepository.save(user);
    }
    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }
    public List<User> findAllUsers(int pageSize){
        return userRepository.findAll(Pageable.ofSize(pageSize)).getContent();
    }
    public void removeUser(Long id){
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }
    public List<User> findUserByCriteria(User userCriteria){
        Example<User> example = Example.of(userCriteria);
        return userRepository.findAll(example);
    }
    public User updateUser(User user){
        if (!userRepository.existsById(user.getUserId())) {
            throw new ResourceNotFoundException("User with id " + user.getUserId() + " not found");
        }
        return userRepository.save(user);
    }
}
