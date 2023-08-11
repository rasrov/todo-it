package com.rasrov.todo.it.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
  private String description;
  private boolean completed;
  private List<TaskResponseDTO> subTaskList;
}
