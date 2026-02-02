package com.hexacode.dayone.controller;

import com.hexacode.dayone.entity.TaskDTO;
import com.hexacode.dayone.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @RequestMapping("/getAllTasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    @RequestMapping("/createTask")
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid TaskDTO task) {
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.deleteTask(id));
    }
}
