package com.thoughtworks;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;

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

    @Test
    public void shouldHaveTodoPresentInCollectionAfterCreation() {
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("lol i exist forever");

        controller.createTodo(todo);

        List<Todo> aBunchOfTodos = controller.getAll();

        assertThat(aBunchOfTodos.size(), is(1));
        assertThat(aBunchOfTodos.get(0).getTitle(), is(todo.getTitle()));
    }

    @Test
    public void shouldDeleteAllTodosIfNoIdIsSpecified() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
        todo.setTitle("lol i exist forever");

        controller.createTodo(todo);
        controller.delete();

        List<Todo> aBunchOfTodos = controller.getAll();

        assertThat(aBunchOfTodos.size(), is(0));


        //controller.createTodo(todo);
        //assertThat(controller.getTodoById(0).getUrl(), is()
    }

    @Test
    public void shouldHavePropertyCompletedAsFalse(){

        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("A new todo I'm never going to do");

        controller.createTodo(todo);

        assertFalse(controller.getTodoById(0).isCompleted());
    }

    @Test
    public void shouldHaveUrl(){
        TodoController controller = new TodoController();

        Todo todo = new Todo();
        todo.setTitle("A new todo I'm never going to do");

        controller.createTodo(todo);

        String url = controller.getTodoById(0).getUrl();
        assertThat(url, is("http://localhost:8080/webapp/0"));
    }

    @Test
    public void shouldReturnTheTodoIdentifiedByItsId() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
        todo.setTitle("A new todo I'm definitely going to do");

        controller.createTodo(todo);

        Todo savedTodo = controller.getTodoById(0);
        assertThat(savedTodo.getTitle(), is(todo.getTitle()));
    }
}
