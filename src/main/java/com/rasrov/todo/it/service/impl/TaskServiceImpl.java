package com.rasrov.todo.it.service.impl;

import com.rasrov.todo.it.dto.request.TaskRequestDTO;
import com.rasrov.todo.it.dto.response.TaskResponseDTO;
import com.rasrov.todo.it.model.Task;
import com.rasrov.todo.it.repository.ITaskRepository;
import com.rasrov.todo.it.service.ITaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements ITaskService {

  private final ITaskRepository taskRepository;
  private final ModelMapper mapper;

  @Autowired
  public TaskServiceImpl(ITaskRepository taskRepository, ModelMapper mapper) {
    this.taskRepository = taskRepository;
    this.mapper = mapper;
  }

  @Override
  public List<TaskResponseDTO> getTasks(final LocalDate currentDate) {
    return buildParentChildTasks(currentDate);
  }

  @Override
  public List<TaskResponseDTO> save(final List<TaskRequestDTO> taskDTOList) {
    LocalDate currentDate = LocalDate.now();
    taskDTOList.forEach(taskDTO -> {
      List<Task> subTaskListToSave = new ArrayList<>();
      Task task = taskRepository.save(toTask(taskDTO, currentDate));

      if (Objects.nonNull(taskDTO.getSubTaskList())) {
        taskDTO.getSubTaskList().forEach(subTaskDTO -> {
          Task subTask = toTask(subTaskDTO, currentDate);
          subTask.setParentId(task.getId());
          subTaskListToSave.add(subTask);
        });
      }

      taskRepository.saveAll(subTaskListToSave);
    });

    return buildParentChildTasks(currentDate);
  }

  private List<TaskResponseDTO> buildParentChildTasks(final LocalDate date) {
    List<TaskResponseDTO> result = new ArrayList<>();
    List<Task> parentTaskList = taskRepository.dailyTaskList(date);

    if (parentTaskList.size() > 0) {
      parentTaskList.forEach(parentTask -> {
        TaskResponseDTO taskResponse = mapper.map(parentTask, TaskResponseDTO.class);

        List<TaskResponseDTO> subTasks =
            taskRepository.dailySubTaskList(date, parentTask.getId()).stream()
                .map(task -> mapper.map(task, TaskResponseDTO.class)).toList();

        taskResponse.setSubTaskList(subTasks);
        result.add(taskResponse);
      });
    }

    return result;
  }

  private Task toTask(final TaskRequestDTO taskDTO, final LocalDate currentDate) {
    Task task = mapper.map(taskDTO, Task.class);
    task.setDate(currentDate);
    task.setCompleted(false);

    return task;
  }
}
