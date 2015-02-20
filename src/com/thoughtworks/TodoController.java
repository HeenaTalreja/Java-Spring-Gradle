package com.thoughtworks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodoController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public @ResponseBody Todo[] index() {
        return  new Todo[0];
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public @ResponseBody Todo createTodo(@RequestBody final Todo todo) {
        return todo;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public @ResponseBody void delete() {

    }
}
