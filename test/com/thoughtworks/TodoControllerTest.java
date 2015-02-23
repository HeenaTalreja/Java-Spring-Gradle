package com.thoughtworks;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class TodoControllerTest {

    @Test
    public void shouldEchoBackATodo(){
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("a todo");

	Todo responseTodo = controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());
        assertThat(responseTodo.getTitle(), equalTo("a todo"));
    }
    @Test
    public void shouldRespondOkWhenDeletingATodo(){
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("a todo");

	Todo responseTodo = controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());
        assertThat(responseTodo.getTitle(), equalTo("a todo"));
    }

    @Test
    public void shouldHaveTodoPresentInCollectionAfterCreation() {
        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("lol i exist forever");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        List<Todo> aBunchOfTodos = controller.getAll();

        assertThat(aBunchOfTodos.size(), is(1));
        assertThat(aBunchOfTodos.get(0).getTitle(), is(todo.getTitle()));
    }

    @Test
    public void shouldDeleteAllTodosIfNoIdIsSpecified() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
	todo.setTitle("Delete all the things");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());
        controller.delete();

        List<Todo> aBunchOfTodos = controller.getAll();

        assertThat(aBunchOfTodos.size(), is(0));
    }

    @Test
    public void shouldHavePropertyCompletedAsFalse(){

        TodoController controller = new TodoController();
        Todo todo = new Todo();
        todo.setTitle("A new todo I'm never going to do");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        assertFalse(controller.getTodoById(0).isCompleted());
    }

    @Test
    public void shouldHaveUrl(){
        TodoController controller = new TodoController();

        Todo todo = new Todo();
        todo.setTitle("A new todo I'm never going to do");

	MockHttpServletRequest mockRequest = new MockHttpServletRequest();
	controller.createTodo(todo, mockRequest, new MockHttpServletResponse());

	URI url = controller.getTodoById(0).getUrl();
	assertThat(url.toString(), is(mockRequest.getRequestURL() + "/0"));
    }

    @Test
    public void shouldReturnTheTodoIdentifiedByItsId() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
        todo.setTitle("A new todo I'm definitely going to do");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        Todo savedTodo = controller.getTodoById(0);
        assertThat(savedTodo.getTitle(), is(todo.getTitle()));
    }


    @Test public void shouldUpdateTheTitleOfAnExistingTodo() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
	todo.setTitle("Buy Dan Brunch");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());


        Todo patch = new Todo();
	patch.setTitle("Buy Dan Lunch");
        patch.setCompleted(null);
        patch.setUrl(null);

        controller.patchTodo(0, patch);

        Todo updated = controller.getTodoById(0);
        assertThat(updated.getTitle(), is(patch.getTitle()));
        assertThat(updated.isCompleted(), is(false));
    }

    @Test public void shouldUpdateTheCompletednessOfAnExistingTodo() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
	todo.setTitle("Get Coffee");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        Todo patch = new Todo();
        patch.setTitle(null);
        patch.setCompleted(true);
        patch.setUrl(null);

        controller.patchTodo(0, patch);

        Todo updated = controller.getTodoById(0);
        assertThat(updated.isCompleted(), is(true));
    }

    @Test public void shouldUpdateTheOrderOfAnExistingTodo() {
        TodoController controller = new TodoController();

        Todo todo = new Todo();
	todo.setTitle("Do Laundry");
	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        Todo patch = new Todo();
        patch.setTitle(null);
        patch.setCompleted(null);
        patch.setUrl(null);
        patch.setOrder(100);

        controller.patchTodo(0, patch);

        Todo updated = controller.getTodoById(0);
        assertThat(updated.getOrder(), is(100));
    }

    @Test
    public void shouldDeleteById(){
        TodoController controller = new TodoController();

        Todo todo = new Todo();
	todo.setTitle("Start Vagrant");

	controller.createTodo(todo, new MockHttpServletRequest(), new MockHttpServletResponse());

        controller.deleteTodo(0);

        assertThat(controller.getAll().size(), is(0));
        assertNull(controller.getTodoById(0));
    }
}
