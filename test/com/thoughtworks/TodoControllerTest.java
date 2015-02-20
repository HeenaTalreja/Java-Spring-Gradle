package com.thoughtworks;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoControllerTest {
    @Test
    public void itShouldRespondToGet(){

    }

    @Test
    public void shouldEchoBackATodo(){
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("a todo");

        Todo responseTodo = controller.createTodo(todo);
        assertThat(responseTodo.getTitle(), equalTo("a todo"));
    }
    @Test
    public void shouldRespondOkWhenDeletingATodo(){
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("a todo");

        Todo responseTodo = controller.createTodo(todo);
        assertThat(responseTodo.getTitle(), equalTo("a todo"));
    }
}
