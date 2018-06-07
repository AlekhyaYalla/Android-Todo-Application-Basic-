package com.example.ayalla.myapplication.presenters;

import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.views.TodoItemView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemPresenterTest {

    @Mock
    private TodoItemView view = mock(TodoItemView.class);
    private TodoItemPresenter presenter;
    private TodoItem item;

    @Before
    public void setup() {
        presenter = new TodoItemPresenter(view);
        item = new TodoItem("title", "description");
    }

    @Test
    public void shoudlCallViewDisplayItem() {
        presenter.displayItem(item);
        verify(view).displayItem(item);
    }

    @Test
    public void shoudlCallViewMarkItAsDOne() {
        presenter.markItAsDone();
        verify(view).markAsDone();
    }

    @Test
    public void shoudlCallViewCloseItem() {
        presenter.close();
        verify(view).close();
    }

    @Test
    public void shoudlCallViewDeleteItem() {
        presenter.delete();
        verify(view).delete();
    }


}