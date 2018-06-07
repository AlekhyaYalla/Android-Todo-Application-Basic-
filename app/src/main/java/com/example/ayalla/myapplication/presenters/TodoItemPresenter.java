package com.example.ayalla.myapplication.presenters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.views.TodoItemView;

public class TodoItemPresenter {

    private TodoItemView view;

    public TodoItemPresenter(TodoItemView view) {
        this.view = view;
    }

    public void displayItem(TodoItem todoItem) {
        view.displayItem(todoItem);
    }

    public void markItAsDone() {
        view.markAsDone();
    }

    public void close() {
        view.close();
    }

    public void delete() {
        view.delete();
    }

    public Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
