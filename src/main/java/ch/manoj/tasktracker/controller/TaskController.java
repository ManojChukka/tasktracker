package ch.manoj.tasktracker.controller;

import ch.manoj.tasktracker.dto.TodoDto;
import ch.manoj.tasktracker.entity.Todo;
import ch.manoj.tasktracker.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/{userId}/task")
    public ResponseEntity<TodoDto> addTask(@PathVariable(name = "userId") long userId, @RequestBody TodoDto todoDto){
        return new ResponseEntity<>(todoService.addTask(userId,todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TodoDto>> retrieveAllTasksByUserId(@PathVariable(name = "userId") long userId){
        return new ResponseEntity<>(todoService.retrieveAllTasks(userId),HttpStatus.OK);
    }

    @GetMapping("/{userId}/{taskId}/task")
    public ResponseEntity<TodoDto> getTask(@PathVariable(name = "userId") long userId, @PathVariable(name = "taskId") long taskId){
        return new ResponseEntity<>(todoService.getTask(userId,taskId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{taskId}/delete/task")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "userId") long userId, @PathVariable(name = "taskId") long taskId){
        todoService.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted successfully!!",HttpStatus.OK);
    }

    @PutMapping("/{userId}/{taskId}/task")
    public ResponseEntity<TodoDto> reassignTask(@PathVariable(name = "userId") long userId, @PathVariable(name = "taskId") long taskId){
        return new ResponseEntity<>(todoService.reassignTask(userId,taskId),HttpStatus.ACCEPTED);
    }
}
