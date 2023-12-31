package com.rasrov.todo.it.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
  private UUID id;
  private String description;
  private boolean completed;
  private List<TaskResponseDTO> subTaskList;
}
