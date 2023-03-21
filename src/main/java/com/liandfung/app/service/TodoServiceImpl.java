package com.liandfung.app.service;

import com.liandfung.app.dto.Todo;
import com.liandfung.app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getTodo(String name) {
        return todoRepository.findByUser(name);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }
}
