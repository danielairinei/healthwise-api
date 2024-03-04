package com.actios.controller;

import com.actios.dto.UserDTO;
import com.actios.dto.UserPreferenceDTO;
import com.actios.dto.UserRequestDTO;
import com.actios.entity.Preference;
import com.actios.entity.User;
import com.actios.service.UserService;
import com.actios.util.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = Constants.API_ROOT + "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> dtos = userService.getUsers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UUID id = userService.addUser(userRequestDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserDTO userDTO = userService.updateUser(userRequestDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable("id") UUID id) {
        Boolean isDeleted = userService.deleteUserById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/preference")
    public ResponseEntity<List<Preference>> getPreferences(@PathVariable("id") UUID id) {
        List<Preference> preferences = userService.getPreferences(id);
        return new ResponseEntity<>(preferences, HttpStatus.OK);
    }

    @PutMapping(value = "/preference")
    public ResponseEntity<Boolean> updatePreferences(@Valid @RequestBody UserPreferenceDTO userPreferenceDTO) {
        Boolean isUpdated = userService.updatePreferences(userPreferenceDTO);
        return new ResponseEntity<>(isUpdated, HttpStatus.OK);
    }
}
