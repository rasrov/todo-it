package com.rasrov.todo.it.repository;

import com.rasrov.todo.it.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

  @Query("SELECT task FROM Task task WHERE task.date =:date AND task.parentId IS NULL")
  List<Task> dailyTaskList(@Param("date") LocalDate currentDate);

  @Query("SELECT task FROM Task task WHERE task.date =:date AND task.parentId =:parentId")
  List<Task> dailySubTaskList(@Param("date") LocalDate currentDate,
      @Param("parentId") UUID parentId);

}
