package com.rasrov.todo.it.controller;

import com.rasrov.todo.it.dto.request.TaskRequestDTO;
import com.rasrov.todo.it.dto.response.TaskResponseDTO;
import com.rasrov.todo.it.service.impl.TaskServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskServiceImpl taskService;

  @Autowired
  private ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<TaskResponseDTO>> getTasks(@RequestParam LocalDate date) {
    return ResponseEntity.ok(taskService.getTasks(date));
  }

  @PostMapping(value = "/create")
  public ResponseEntity<List<TaskResponseDTO>> createTask(
      @RequestBody List<TaskRequestDTO> taskDTOList) {
    return ResponseEntity.ok(taskService.save(taskDTOList));
  }

  @PostMapping(value = "/update")
  public ResponseEntity<List<TaskResponseDTO>> updateTask(
      @RequestBody List<TaskRequestDTO> taskDTOList, @RequestParam LocalDate date) {

    return ResponseEntity.ok(taskService.update(taskDTOList, date));
  }

  @DeleteMapping(value = "/delete")
  public void deleteTasks(@RequestParam List<String> taskIdList) {
    taskService.delete(taskIdList);
  }

}
