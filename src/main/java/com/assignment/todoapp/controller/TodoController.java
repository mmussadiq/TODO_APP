package com.assignment.todoapp.controller;

import com.assignment.todoapp.enums.Status;
import com.assignment.todoapp.exception.ValidationException;
import com.assignment.todoapp.model.Task;
import com.assignment.todoapp.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mussadiq on 5/8/2018.
 */
@RestController
@RequestMapping(path = "/task")
public class TodoController
{
    Logger LOG = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    TaskRepository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addTask(@RequestBody Task task) throws ValidationException {
        validate(task);
        repository.save(task);
        LOG.info("Task saved successfully.");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateTask(@RequestBody Task task) throws ValidationException{
        validate(task);

        Optional<Task> optional = repository.findById(task.getId());
        if(!optional.isPresent()){
            throw new ValidationException("No Corresponding task found in database.");
        }

        Task taskEntity = optional.get();
        taskEntity.setName(task.getName());
        taskEntity.setDueDate(task.getDueDate());
        taskEntity.setStatus(task.getStatus());

        repository.save(task);
        LOG.info("Task updated successfully.");
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable(name = "id") Integer id) throws ValidationException {

        Optional<Task> optional = repository.findById(id);
        if(!optional.isPresent()){
            throw new ValidationException("No Corresponding task found in database.");
        }
        repository.delete(optional.get());
        LOG.info("Task deleted successfully.");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Task> getTasks(){
        LOG.info("Fetching list of all tasks.");
        List<Task> tasks = (List<Task>) repository.findAll();
        return tasks;
    }

    /**
     * This method is responsible for validating task object
     * @param task
     */
    private void validate(Task task) throws ValidationException {
        if(task == null){

            throw new ValidationException("Request object cannot be null");

        } if(task.getName() == null || task.getName().isEmpty()){

            throw new ValidationException("Task name cannot be null/empty");

        } else if(task.getDueDate() == null || task.getDueDate().before(new Date())){

            throw new ValidationException("Due date must be not null and cannot be before today");

        } else if(task.getStatus() == null || !(task.getStatus().equals(Status.TODO.getName())  || task.getStatus().equals(Status.COMPLETED.getName()))){

            throw new ValidationException("Invalid status value. It can be TODO or COMPLETED");

        }

    }
}
