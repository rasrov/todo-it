package com.rasrov.todo.it.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
  private UUID id;
  private String username;
  private String password;
  private String userEmail;
  private String role;
}
