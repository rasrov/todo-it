package com.rasrov.todo.it.controller;

import com.rasrov.todo.it.dto.request.UserRequestDTO;
import com.rasrov.todo.it.dto.response.UserResponseDTO;
import com.rasrov.todo.it.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private ModelMapper mapper;

  @PostMapping(value = "/create")
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.ok(mapper.map(userService.create(userRequestDTO), UserResponseDTO.class));
  }

  @PutMapping(value = "/update")
  public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
    return ResponseEntity.ok(mapper.map(userService.update(userRequestDTO), UserResponseDTO.class));
  }

  @DeleteMapping(value = "/delete")
  public void deleteUser(@RequestParam UUID userId) {
    userService.delete(userId);
  }

}
