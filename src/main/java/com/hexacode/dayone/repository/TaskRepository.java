package com.hexacode.dayone.repository;

import com.hexacode.dayone.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean deleteTaskById(String trim);
}
