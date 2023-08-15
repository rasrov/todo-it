package com.rasrov.todo.it.repository;

import com.rasrov.todo.it.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByUserEmail(String userEmail);

}
