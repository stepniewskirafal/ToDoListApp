package pl.rstepniewski.todolistapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.rstepniewski.todolistapp.models.TodoItem;
import pl.rstepniewski.todolistapp.repositories.TodoItemRepository;

@Component
public class TodoItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("Write an application in Spring Boot.");
            todoItem1.setComplete(true);
            TodoItem todoItem2 = new TodoItem("Do a running workout.");
            TodoItem todoItem3 = new TodoItem("Stretch by practicing yoga.");


            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2);
            todoItemRepository.save(todoItem3);
        }

        logger.info("Number of TodoItems: {}", todoItemRepository.count());
    }

}