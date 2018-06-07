package com.example.ayalla.myapplication.presenters;

import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.repository.TodoItemRepository;
import com.example.ayalla.myapplication.views.TodoListMainView;

import java.util.ArrayList;
import java.util.List;

public class TodoListMainPresenter {
    private TodoListMainView view;
    private TodoItemRepository repository;
    private List<TodoItem> todoList;

    public TodoListMainPresenter(TodoListMainView view, TodoItemRepository repository) {
        this.view = view;
        todoList = new ArrayList<>();
        this.repository = repository;
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    public void init() {
        todoList = repository.getAll();
        view.renderList(todoList);
    }

    public void getAllDoneTodos() {
        todoList = repository.getAllDone();
        view.renderList(todoList);
    }

    public void getAllNotDoneTodos() {
        todoList = repository.getAllNotDone();
        view.renderList(todoList);
    }

    public void addToTodoList(TodoItem todoItem) {
        todoList.add(todoItem);
    }

    public void saveItem(TodoItem todoItem) {
        repository.saveTodoItem(todoItem);
    }

    public void markTodoAsDone(String position) {
        TodoItem item = todoList.get(Integer.parseInt(position));
        item.setDone(true);
        repository.updateIsDoneColumnForTheRecord(item);

    }

    public void deleteTodo(String position) {
        TodoItem item = todoList.get(Integer.parseInt(position));
        repository.deleteRecord(item);
    }


}
