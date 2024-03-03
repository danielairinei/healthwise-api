package com.actios.service;

import com.actios.dto.UserDTO;
import com.actios.dto.builder.UserBuilder;
import com.actios.entity.User;
import com.actios.repository.UserRepository;
import com.actios.util.ServiceUtils;
import com.actios.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
        return UserBuilder.toUserDTO(user.get());
    }

    public UUID addUser(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public boolean deleteUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserDTO updateUser(User user) {
        Optional<User> existingUserOptional = userRepository.findById(user.getId());

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            ServiceUtils.updateFields(existingUser, user);
            return UserBuilder.toUserDTO(userRepository.save(existingUser));
        } else {
            throw new UserNotFoundException(User.class.getSimpleName() + " with id: " + user.getId());
        }
    }
}
