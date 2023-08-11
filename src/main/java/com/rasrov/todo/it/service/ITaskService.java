package com.rasrov.todo.it.service;

import com.rasrov.todo.it.dto.request.TaskRequestDTO;
import com.rasrov.todo.it.dto.response.TaskResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ITaskService {

  List<TaskResponseDTO> getTasks(LocalDate currentDate);

  List<TaskResponseDTO> save(List<TaskRequestDTO> taskDTOList);

}
