package com.example.ayalla.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ayalla.myapplication.R;
import com.example.ayalla.myapplication.adapter.TodoListMainActivityAdapter;
import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.presenters.TodoListMainPresenter;
import com.example.ayalla.myapplication.repository.TodoItemRepository;
import com.example.ayalla.myapplication.views.TodoListMainView;

import java.util.List;

import static com.example.ayalla.myapplication.constants.Constants.ADD_TODO_ITEM_REQUEST_CODE;
import static com.example.ayalla.myapplication.constants.Constants.DELETE_TODO_REQUEST_CODE;
import static com.example.ayalla.myapplication.constants.Constants.DESCRIPTION_KEY;
import static com.example.ayalla.myapplication.constants.Constants.IMAGE_KEY;
import static com.example.ayalla.myapplication.constants.Constants.OPEN_TODO_REQUEST_CODE;
import static com.example.ayalla.myapplication.constants.Constants.POSITION_KEY;
import static com.example.ayalla.myapplication.constants.Constants.TITLE_KEY;

public class TodoListMainActivity extends AppCompatActivity implements TodoListMainView {

    private TodoListMainPresenter presenter;
    private TodoItemRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new TodoItemRepository(getApplicationContext());
        presenter = new TodoListMainPresenter(this, repository);
        presenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.headermenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addButton) {
            Intent intent = new Intent(getApplicationContext(), AddTodoItemActivity.class);
            startActivityForResult(intent, ADD_TODO_ITEM_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TODO_ITEM_REQUEST_CODE && resultCode == RESULT_OK) {
            TodoItem todoItem = new TodoItem(data.getStringExtra(TITLE_KEY), data.getStringExtra(DESCRIPTION_KEY),data.getStringExtra(IMAGE_KEY));
            presenter.saveItem(todoItem);
            presenter.init();
        }
        else if (requestCode == OPEN_TODO_REQUEST_CODE && resultCode == RESULT_OK) {
            String position = data.getStringExtra(POSITION_KEY);
            presenter.markTodoAsDone(position);
            presenter.getAllDoneTodos();
        }

        else if(requestCode == OPEN_TODO_REQUEST_CODE && resultCode == DELETE_TODO_REQUEST_CODE){
            String position = data.getStringExtra(POSITION_KEY);
            presenter.deleteTodo(position);
            presenter.init();
        }

    }

    @Override
    public void renderList(List<TodoItem> todoList) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new TodoListMainActivityAdapter(todoList));
        recyclerView.setLayoutManager(new LinearLayoutManager(TodoListMainActivity.this));
    }

    public void filterAll(View view) {
        presenter.init();
    }

    public void filterDone(View view) {
        presenter.getAllDoneTodos();
    }

    public void filterNotDone(View view) {
        presenter.getAllNotDoneTodos();
    }
}
