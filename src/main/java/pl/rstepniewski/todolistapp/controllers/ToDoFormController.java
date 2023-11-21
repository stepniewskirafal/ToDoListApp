package pl.rstepniewski.todolistapp.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rstepniewski.todolistapp.models.ToDoItem;
import pl.rstepniewski.todolistapp.repositories.ToDoItemRepository;

@Controller
public class ToDoFormController {
    private final Logger logger = LoggerFactory.getLogger(ToDoFormController.class);

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem todoItem,
                                 Model model){
        model.addAttribute("todoItem", new ToDoItem());
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    String showUpdateForm(@PathVariable long id, Model model){
        ToDoItem toDoItem = toDoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + "not found"));
        model.addAttribute("todo", toDoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable(name = "id") long id,
                                 Model model){
        ToDoItem toDoItem = toDoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + "not found"));
        toDoItemRepository.delete(toDoItem);
        return "redirect:/";
    }

}
