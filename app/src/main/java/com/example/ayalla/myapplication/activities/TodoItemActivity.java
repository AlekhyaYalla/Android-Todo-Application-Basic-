package com.example.ayalla.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayalla.myapplication.R;
import com.example.ayalla.myapplication.models.TodoItem;
import com.example.ayalla.myapplication.presenters.TodoItemPresenter;
import com.example.ayalla.myapplication.views.TodoItemView;

import static com.example.ayalla.myapplication.constants.Constants.DELETE_TODO_REQUEST_CODE;
import static com.example.ayalla.myapplication.constants.Constants.DESCRIPTION_KEY;
import static com.example.ayalla.myapplication.constants.Constants.IMAGE_KEY;
import static com.example.ayalla.myapplication.constants.Constants.POSITION_KEY;
import static com.example.ayalla.myapplication.constants.Constants.TITLE_KEY;

public class TodoItemActivity extends Activity implements TodoItemView {

    private TodoItemPresenter presenter;
    private TodoItem currentTodo;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_todo_item);
        presenter = new TodoItemPresenter(this);

        currentTodo = getPassedIntentExtras();
        presenter.displayItem(currentTodo);
    }

    private TodoItem getPassedIntentExtras() {
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(TITLE_KEY);
        String description = extras.getString(DESCRIPTION_KEY);
        String imageStr = extras.getString(IMAGE_KEY);
        position = Integer.parseInt(extras.getString(POSITION_KEY));
       return new TodoItem(title, description,imageStr);
    }

    @Override
    public void displayItem(TodoItem todoItem) {
        TextView todoTitle = (TextView) findViewById(R.id.title);
        todoTitle.setText(todoItem.getTitle());

        TextView todoDescription = (TextView) findViewById(R.id.description);
        todoDescription.setText(todoItem.getDescription());

        ImageView todoImage = (ImageView) findViewById(R.id.image);
        Bitmap photo = presenter.stringToBitMap(todoItem.getImage());
        todoImage.setImageBitmap(photo);

    }

    @Override
    public void markAsDone() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(POSITION_KEY,String.valueOf(position));
        setResult(RESULT_OK,resultIntent);
        finish();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void delete() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(POSITION_KEY,String.valueOf(position));
        setResult(DELETE_TODO_REQUEST_CODE,resultIntent);
        finish();
    }

    public void markTodoDone(View view) {
        presenter.markItAsDone();
    }

    public void closeTodo(View view) {
        presenter.close();
    }

    public void deleteTodo(View view) {
        presenter.delete();
    }
}
