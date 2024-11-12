package ch.manoj.tasktracker.repository;

import ch.manoj.tasktracker.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    public List<Todo> findAllByUsersId(long userId);

    public Optional<Todo> findTodoByIdAndUsersId(long taskId, long userId);
}
