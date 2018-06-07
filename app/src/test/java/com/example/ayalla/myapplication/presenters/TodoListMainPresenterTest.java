package com.example.ayalla.myapplication.presenters;

import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.repository.TodoItemRepository;
import com.example.ayalla.myapplication.views.TodoListMainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TodoListMainPresenterTest {

    @Mock
    private TodoListMainView view = mock(TodoListMainView.class);
    @Mock
    private TodoItemRepository repository = mock(TodoItemRepository.class);
    private TodoListMainPresenter presenter;
    private List<TodoItem> list = new ArrayList<>();
    private TodoItem todo;

    @Before
    public void setup() {
        presenter = new TodoListMainPresenter(view, repository);
         todo = new TodoItem("todo","description");
    }

    @Test
    public void shouldCallRenderInView(){
        presenter.init();
        verify(view).renderList(list);
    }

    @Test
    public void shouldAddItemToTheList(){
        int size  = presenter.getTodoList().size();
        System.out.println(size);
        presenter.addToTodoList(todo);
        assertEquals(list.size(),size);
    }

    @Test
    public void shouldCheckRepositorySaveIsGettingCalled(){
        presenter.saveItem(todo);
        verify(repository).saveTodoItem(todo);
    }

    @Test
    public void checkIfSettingIsDoneAsTrueAndCallToRepository(){
        presenter.getTodoList().add(todo);
        presenter.markTodoAsDone("0");
        assertTrue(todo.getDone());
        verify(repository).updateIsDoneColumnForTheRecord(todo);
    }

    @Test
    public void checkCallToRepositoryForDeletionOfTodo(){
        presenter.getTodoList().add(todo);
        presenter.deleteTodo("0");
        verify(repository).deleteRecord(todo);
    }

}