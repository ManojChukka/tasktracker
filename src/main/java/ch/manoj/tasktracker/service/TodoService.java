package ch.manoj.tasktracker.service;

import ch.manoj.tasktracker.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoService {

    public TodoDto addTask(long userId, TodoDto todoDto);

    public List<TodoDto> retrieveAllTasks(long userId);

    public TodoDto getTask(long userId, long todoId);

    public void deleteTask(long userId, long todoId);

    public TodoDto reassignTask(long userId, long todoId);
}
