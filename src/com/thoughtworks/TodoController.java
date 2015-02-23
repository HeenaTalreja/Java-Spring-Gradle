package com.thoughtworks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TodoController {
    Map<Integer, Todo> todos;

    int idGenerator;

    public TodoController() {
        todos = new HashMap<Integer, Todo>();
        idGenerator = 0;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public @ResponseBody List<Todo> getAll() {
        return new ArrayList<Todo>(todos.values());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public @ResponseBody Todo createTodo(@RequestBody final Todo todo, HttpServletRequest request) {
        int id = idGenerator++;

        String contextPath = String.valueOf(request.getRequestURL() + "/");
        todo.setUrl(URI.create(contextPath + id));

        todos.put(id, todo);
        return todo;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public @ResponseBody void delete() {
        todos.clear();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody Todo getTodoById(@PathVariable int id) {
        return todos.get(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public @ResponseBody Todo patchTodo(@PathVariable int id, @RequestBody Todo todo) {
        Todo existingTodo = todos.get(id);

        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }

        if (todo.isCompleted() != null) {
            existingTodo.setCompleted(todo.isCompleted());
        }

        if (todo.getOrder() != null) {
            existingTodo.setOrder(todo.getOrder());
        }

        return existingTodo;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public @ResponseBody void deleteTodo(@PathVariable int id) {
        todos.remove(id);
    }
}
