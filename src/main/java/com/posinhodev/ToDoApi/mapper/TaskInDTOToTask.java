package com.posinhodev.ToDoApi.mapper;

import com.posinhodev.ToDoApi.persistence.entity.Task;
import com.posinhodev.ToDoApi.persistence.entity.TaskStatus;
import com.posinhodev.ToDoApi.service.dto.TaskInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * este mapper nos convierte el objeto que creamos en TaskInDTO a un objeto
 * real de Task, para que no sean requeridos todos los atributos de la entidad
 * al momento inicial de la creacion
 */
@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{
    @Override
    public Task map(TaskInDTO in){
        Task task = new Task();
        //TaskInDTO -> Task
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setFinalEstimateDate(in.getFinalEstimateDate());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;
    }
}
