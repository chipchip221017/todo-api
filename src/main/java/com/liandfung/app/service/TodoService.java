package com.liandfung.app.service;

import com.liandfung.app.dto.Todo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TodoService {
    List<Todo> getTodo(String name);
    Todo save(Todo todo);
}
