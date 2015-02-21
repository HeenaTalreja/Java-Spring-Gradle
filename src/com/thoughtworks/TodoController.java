package com.thoughtworks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TodoController {
    Map<Integer, Todo> todos;

    String servicePath = "http://localhost:8080/webapp/";

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
    public @ResponseBody Todo createTodo(@RequestBody final Todo todo) {
        int id = idGenerator++;
        todo.setUrl(servicePath + id);

        todos.put(id, todo);
        return todo;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public @ResponseBody void delete() {
        todos.clear();
    }

    @RequestMapping(method = RequestMethod.GET, value= "/{id}")
    public @ResponseBody Todo getTodoById(@PathVariable int id) {
        return todos.get(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value="/{id}")
    public @ResponseBody Todo patchTodo(@PathVariable int id, @RequestBody Todo todo) {
        Todo existing = todos.get(id);

        if (todo.getTitle() != null) {
            existing.setTitle(todo.getTitle());
        }

        if(todo.isCompleted() != null) {
            existing.setCompleted(todo.isCompleted());
        }


        return existing;

    }

}
