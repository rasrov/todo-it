package com.rasrov.todo.it.service;

import com.rasrov.todo.it.dto.request.UserRequestDTO;
import com.rasrov.todo.it.model.User;

import java.util.UUID;

public interface IUserService {

  User create(UserRequestDTO userDTO);

  User update(UserRequestDTO userDTO);

  void delete(UUID userId);

}
