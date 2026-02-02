package com.hexacode.dayone.service;

import com.hexacode.dayone.entity.Task;
import com.hexacode.dayone.entity.TaskDTO;
import com.hexacode.dayone.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDTO createTask(TaskDTO task) {
        Task taskEntity = mapToEntity(task);
        Task savedTask = taskRepository.save(taskEntity);
        return  mapToDto(savedTask);
    }

    private Task mapToEntity(TaskDTO task){
        return Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }

    private TaskDTO mapToDto(Task task){
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }

    public List<TaskDTO> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream()
                .map(this::mapToDto)
                .toList();
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    public TaskDTO updateTask(TaskDTO task) {

        Task inputTask = mapToEntity(task);

        Task existingTask = taskRepository.findById(inputTask.getId())
                .orElseThrow(() -> new RuntimeException("Task not found: " + inputTask.getId()));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        taskRepository.save(existingTask);

        return mapToDto(existingTask);
    }
}
