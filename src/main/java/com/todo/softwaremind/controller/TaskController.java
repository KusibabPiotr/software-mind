package com.todo.softwaremind.controller;

import com.todo.softwaremind.model.dto.TaskDto;
import com.todo.softwaremind.service.contract.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(taskService.createTask(taskDto));
    }

    @GetMapping
    public ResponseEntity<Page<TaskDto>> getTasks(
            @RequestParam(required = false, defaultValue = "isDone") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(taskService.getTasks(sortBy, sortDirection, page, pageSize));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable UUID id) {
        return ResponseEntity.ok(taskService.updateTask(taskDto, id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("User with id " + id + " has been successfully deleted!");
    }
}
