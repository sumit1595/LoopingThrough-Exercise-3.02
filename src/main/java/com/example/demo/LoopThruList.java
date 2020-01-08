package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class LoopThruList {
    @Autowired
    TodoRepository todoRepository;
    @RequestMapping("/")
    public String listTodo(Model model){
        model.addAttribute("todo", todoRepository.findAll());
        return"TodoList";
    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new Todo());
        return "TodoForm";
    }
    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "TodoForm";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }
}
