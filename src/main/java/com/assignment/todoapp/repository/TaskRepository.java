package com.assignment.todoapp.repository;

import com.assignment.todoapp.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mussadiq on 5/8/2018.
 */

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer>{
}
