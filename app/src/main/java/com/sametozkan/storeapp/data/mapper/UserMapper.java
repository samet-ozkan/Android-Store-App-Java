package com.sametozkan.storeapp.data.mapper;

import com.sametozkan.storeapp.data.dto.UserDTO;
import com.sametozkan.storeapp.domain.model.User;

public class UserMapper {

    public static User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getUid(),
                userDTO.getFullName(),
                userDTO.getEmail()
        );
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUid(),
                user.getFullName(),
                user.getEmail()
        );
    }
}
