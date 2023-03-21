package com.liandfung.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liandfung.app.controller.TodoController;
import com.liandfung.app.dto.Todo;
import com.liandfung.app.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {TodoController.class})
@WebMvcTest
class TodoApplicationTests {

	@MockBean
	private TodoService todoService;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void getTodo() throws Exception {
		Todo todo1 = new Todo();
		todo1.setId(1);
		todo1.setUser("linh");
		todo1.setDescription("learn Python");
		todo1.setCompleted(true);

		Todo todo2 = new Todo();
		todo2.setId(2);
		todo2.setUser("linh");
		todo2.setDescription("do machine learning");
		todo2.setCompleted(false);

		List<Todo> todoList = new ArrayList<>();
		todoList.add(todo1);
		todoList.add(todo2);

		when(todoService.getTodo(anyString())).thenReturn(todoList);
		mvc.perform(get("/users/linh/todo")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].user").value("linh"))
				.andExpect(jsonPath("$[0].description").value("learn Python"))
				.andExpect(jsonPath("$[0].completed").value(true))
				.andExpect(jsonPath("$[1].description").value("do machine learning"))
				.andExpect(jsonPath("$[1].completed").value(false));
	}

	@Test
	public void createTodo() throws Exception {
		Todo todo = new Todo();
		todo.setUser("chip");
		todo.setDescription("learn English");
		todo.setCompleted(false);
		mvc.perform(post("/todo")
						.content(asJsonString(todo))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
