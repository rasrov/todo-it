package com.rasrov.todo.it.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
  private UUID id;
  private String username;
  private String password;
  private String userEmail;
  private String role;
}
