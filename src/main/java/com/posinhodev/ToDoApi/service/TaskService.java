package com.posinhodev.ToDoApi.service;

import com.posinhodev.ToDoApi.exceptions.ToDoExceptions;
import com.posinhodev.ToDoApi.mapper.TaskInDTOToTask;
import com.posinhodev.ToDoApi.persistence.entity.Task;
import com.posinhodev.ToDoApi.persistence.entity.TaskStatus;
import com.posinhodev.ToDoApi.persistence.repository.TaskRepository;
import com.posinhodev.ToDoApi.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    /**
     * Este seria el principio de inyecccion de dependencias, donde se inyecta
     * el repository para ser usado en el service
     */
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapperTaskInDTOToTask;

    /**
     * inyeccion de dependencias basada en contructor, pero se puede tambien con (@Autowrite, por metodo...)
     * buena practica es con contructores
     */
    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapperTaskInDTOToTask) {
        this.taskRepository = taskRepository;
        this.mapperTaskInDTOToTask = mapperTaskInDTOToTask;
    }

    //mapper nos permite convertir un objeto en otro
    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapperTaskInDTOToTask.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    //service para obtener todos los registros de la tabla task
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    //service para devolver lista de tareas por estado
    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.taskRepository.findAllByTaskStatus(status);
    }

    //servicio para marcar tarea como finalizada
    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    //servicio para eliminar una task
    public void deleteById(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
