package com.team15trello.team15trello.mappers;

import com.team15trello.team15trello.DTOs.UserDTOs.DisplayUserDTO;
import com.team15trello.team15trello.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default User userIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    default Long userToUserId(User user) {
        return user == null ? null : user.getUserId();
    }

    DisplayUserDTO userToUserDTO(User user);

    @Named("UserToEntity")
    User toEntity(DisplayUserDTO displayUserDTO);
}
