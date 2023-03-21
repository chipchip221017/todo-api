package com.liandfung.app.controller;

import com.liandfung.app.dto.Todo;
import com.liandfung.app.exception.ResourceNotFoundException;
import com.liandfung.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/users/{name}/todo")
    public ResponseEntity<List<Todo>> getTodo(@PathVariable String name) {
        List<Todo> todos = todoService.getTodo(name);
        if (CollectionUtils.isEmpty(todos)) {
            throw new ResourceNotFoundException("Todo not found");
        }
        return ResponseEntity.ok().body(todos);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo todoResult = todoService.save(todo);
        return new ResponseEntity<>(todoResult, HttpStatus.CREATED);
    }
}
