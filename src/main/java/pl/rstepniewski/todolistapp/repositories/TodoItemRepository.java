package pl.rstepniewski.todolistapp.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rstepniewski.todolistapp.models.TodoItem;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
}