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
import org.springframework.web.servlet.ModelAndView;
import pl.rstepniewski.todolistapp.models.TodoItem;
import pl.rstepniewski.todolistapp.repositories.TodoItemRepository;

import java.time.Instant;
import java.time.ZoneId;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("request to GET index page");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now()
                                                            .atZone(ZoneId.systemDefault())
                                                            .toLocalDate()
                                                            .getDayOfWeek()
                                                            .toString().toLowerCase());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem toDoItem, BindingResult result, Model model ){
        if (result.hasErrors()){
            return "add-todo-item";
        }
        toDoItem.setCreatedDate(Instant.now());
        toDoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(toDoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable(name = "id") long id,
                                 @Valid TodoItem toDoItem,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            TodoItem todoItem = todoItemRepository
                    .findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + "not found"));
            model.addAttribute("todo", todoItem);
            model.addAttribute("result", result);

            return "update-todo-item";
        }
        toDoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(toDoItem);
        return "redirect:/";
    }

}
