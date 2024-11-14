package com._TechSoft.TodoChatgpt.controller;

import com._TechSoft.TodoChatgpt.entity.Todo;
import com._TechSoft.TodoChatgpt.entity.TodoStatus;
import com._TechSoft.TodoChatgpt.exception.ResourceNotFoundException;
import com._TechSoft.TodoChatgpt.todorepo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Long id, @RequestParam TodoStatus status) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));

        todo.setStatus(status); // Update the status
        Todo updatedTodo = todoRepository.save(todo); // Save the updated todo
        return ResponseEntity.ok(updatedTodo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found"));

        todoRepository.delete(todo);
        return ResponseEntity.noContent().build();
    }
}
