package ch.manoj.tasktracker.service;

import ch.manoj.tasktracker.dto.TodoDto;
import ch.manoj.tasktracker.entity.Todo;
import ch.manoj.tasktracker.entity.Users;
import ch.manoj.tasktracker.exception.MismatchException;
import ch.manoj.tasktracker.exception.TaskNotFoundException;
import ch.manoj.tasktracker.exception.UserNotFoundException;
import ch.manoj.tasktracker.repository.TodoRepository;
import ch.manoj.tasktracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TodoDto addTask(long userId, TodoDto todoDto) {
        Todo task = mapper.map(todoDto,Todo.class);
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found",userId)));
        task.setUsers(user);
        return mapper.map(todoRepository.save(task),TodoDto.class);
    }

    @Override
    public List<TodoDto> retrieveAllTasks(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found",userId)));
        List<Todo> tasks = todoRepository.findAllByUsersId(userId);
        return tasks.stream()
                .map(t -> mapper.map(t,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto getTask(long userId, long todoId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found",userId)));
        todoRepository.findById(todoId).orElseThrow(() -> new TaskNotFoundException(String.format("Todo Id %d not found",todoId)));
        Todo task = todoRepository.findTodoByIdAndUsersId(todoId, userId).orElseThrow(() -> new MismatchException(String.format("Todo Id %d is belong to User Id %d",todoId,userId)));
        return mapper.map(task,TodoDto.class);
    }

    @Override
    public void deleteTask(long userId, long todoId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found",userId)));
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TaskNotFoundException(String.format("Todo Id %d not found",todoId)));
        if(user.getId() != todo.getUsers().getId()){
            throw new MismatchException(String.format("Todo Id %d is belong to User Id %d",todoId,userId));
        }
        todoRepository.deleteById(todoId);
    }

    @Override
    public TodoDto reassignTask(long userId, long todoId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User Id %d not found",userId)));
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new TaskNotFoundException(String.format("Todo Id %d not found",todoId)));
        todo.setUsers(user);
        return mapper.map(todoRepository.save(todo),TodoDto.class);
    }
}
