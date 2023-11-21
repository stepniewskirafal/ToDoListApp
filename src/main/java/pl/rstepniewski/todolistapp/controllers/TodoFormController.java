package pl.rstepniewski.todolistapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.rstepniewski.todolistapp.models.TodoItem;
import pl.rstepniewski.todolistapp.repositories.TodoItemRepository;

@Controller
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem,
                                 Model model){
        model.addAttribute("todoItem", new TodoItem());
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    String showUpdateForm(@PathVariable long id, Model model){
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + "not found"));
        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable(name = "id") long id,
                                 Model model){
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + "not found"));
        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }

}
