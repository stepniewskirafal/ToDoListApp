package pl.rstepniewski.todolistapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.rstepniewski.todolistapp.models.ToDoItem;

@Repository
public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
}
