package com.posinhodev.ToDoApi.controller;

import com.posinhodev.ToDoApi.persistence.entity.Task;
import com.posinhodev.ToDoApi.persistence.entity.TaskStatus;
import com.posinhodev.ToDoApi.service.TaskService;
import com.posinhodev.ToDoApi.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //RestController para crear una task
    @PostMapping
    public Task creaTask(@RequestBody TaskInDTO taskInDTO){
       return this.taskService.createTask(taskInDTO);
    }

    //RestController para listar todas las task
    @GetMapping
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    //RestController para listar las task por estado
    @GetMapping("/status/{status}")
    public List<Task> findAllbyStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllByTaskStatus(status);
    }

    //RestController para marcar tarea como finalizada
    @PatchMapping("/mark_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        //noContent devulve un httpstatus 204 como respuesta al que consume el api
        return ResponseEntity.noContent().build();
    }

    //RestController para eliminar una task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        //noContent devulve un httpstatus 204 como respuesta al que consume el api
        return ResponseEntity.noContent().build();
    }
}
