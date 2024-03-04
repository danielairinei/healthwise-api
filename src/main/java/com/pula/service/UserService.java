package com.pula.service;

import com.pula.dto.response.UserResponseDTO;
import com.pula.dto.request.UserPreferenceDTO;
import com.pula.dto.request.UserRequestDTO;
import com.pula.dto.builder.UserBuilder;
import com.pula.entity.Preference;
import com.pula.entity.User;
import com.pula.repository.UserRepository;
import com.pula.util.ServiceUtils;
import com.pula.util.exceptions.UserNotFoundException;
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

    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserBuilder::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
        User fetchedUser = user.get();
        return UserBuilder.toUserDTO(fetchedUser);
    }

    public UUID addUser(UserRequestDTO userRequestDTO) {
        User user = UserBuilder.toUserEntity(userRequestDTO);
        userRepository.save(user);
        return user.getId();
    }

    public Boolean deleteUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO) {
        User user = UserBuilder.toUserEntity(userRequestDTO);
        Optional<User> existingUserOptional = userRepository.findById(user.getId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            ServiceUtils.updateFields(existingUser, user);
            return UserBuilder.toUserDTO(userRepository.save(existingUser));
        } else {
            throw new UserNotFoundException(User.class.getSimpleName() + " with id: " + user.getId());
        }
    }

    public List<Preference> getPreferences(UUID id) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            return existingUser.getPreferences();
        } else {
            throw new UserNotFoundException(User.class.getSimpleName() + " with id: " + id);
        }
    }

    public Boolean updatePreferences(UserPreferenceDTO userPreferenceDTO) {
        Optional<User> userOptional = userRepository.findById(userPreferenceDTO.userId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPreferences(userPreferenceDTO.preferences());

            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException(User.class.getSimpleName() + " with email: " + email);
        }
    }
}
