package com.rasrov.todo.it.service.impl;

import com.rasrov.todo.it.dto.request.UserRequestDTO;
import com.rasrov.todo.it.model.User;
import com.rasrov.todo.it.repository.IUserRepository;
import com.rasrov.todo.it.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

  private final IUserRepository userRepository;
  private final ModelMapper mapper;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public UserServiceImpl(IUserRepository userRepository, ModelMapper mapper,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public User create(UserRequestDTO userDTO) {
    userRepository.findByUserEmail(userDTO.getUserEmail()).ifPresent(user -> {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          String.format("User with user email %s, already exists.", user.getUserEmail()));
    });
    encodePassword(userDTO);
    return userRepository.save(mapper.map(userDTO, User.class));
  }

  @Override
  public User update(UserRequestDTO userDTO) {
    try {
      userRepository.deleteById(userDTO.getId());
      return this.create(userDTO);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          String.format("User %s cannot updated.", userDTO.getUsername()));
    }
  }

  @Override
  public void delete(UUID userId) {
    userRepository.deleteById(userId);
  }

  private void encodePassword(UserRequestDTO userDTO) {
    userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
  }
}
