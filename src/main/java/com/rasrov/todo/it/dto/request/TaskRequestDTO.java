package com.rasrov.todo.it.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {
  private String description;
  private List<TaskRequestDTO> subTaskList;
}
