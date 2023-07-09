package com.posinhodev.ToDoApi.persistence.repository;

import com.posinhodev.ToDoApi.persistence.entity.Task;
import com.posinhodev.ToDoApi.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Query para que traiga de la base de datos uns lista de tareas por estado
    public List<Task> findAllByTaskStatus( TaskStatus status );

    //consulta nativa para cambiar estado de task
    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);
}
