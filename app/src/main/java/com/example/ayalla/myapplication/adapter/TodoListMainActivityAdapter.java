package com.example.ayalla.myapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayalla.myapplication.R;
import com.example.ayalla.myapplication.activities.TodoItemActivity;
import com.example.ayalla.myapplication.constants.Constants;
import com.example.ayalla.myapplication.models.TodoItem;

import java.util.List;

import static com.example.ayalla.myapplication.constants.Constants.OPEN_TODO_REQUEST_CODE;

public class TodoListMainActivityAdapter extends RecyclerView.Adapter<TodoListMainActivityAdapter.TodoListItemViewHolder> {

    private List<TodoItem> todoList;

    public TodoListMainActivityAdapter(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    @Override
    public TodoListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_todo_item, parent, false);
        return new TodoListItemViewHolder(productItemView);
    }

    @Override
    public void onBindViewHolder(TodoListItemViewHolder holder, int position) {
        TodoItem product = todoList.get(position);
        holder.titleView.setText(product.getTitle());
        if (product.getDone())
            holder.titleView.setBackgroundColor(Color.parseColor("#c5e1a5"));
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView titleView;

        public TodoListItemViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.todoTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            TodoItem product = todoList.get(getAdapterPosition());
            ((Activity) context).startActivityForResult(getIntentForStartActivity(product,context), OPEN_TODO_REQUEST_CODE);
        }

        private Intent getIntentForStartActivity(TodoItem product, Context context) {
            Intent intent = new Intent(context.getApplicationContext(), TodoItemActivity.class);
            intent.putExtra(Constants.TITLE_KEY, product.getTitle());
            intent.putExtra(Constants.DESCRIPTION_KEY, product.getDescription());
            intent.putExtra(Constants.IMAGE_KEY,product.getImage());
            intent.putExtra(Constants.POSITION_KEY, String.valueOf(getAdapterPosition()));
            return intent;
        }
    }
}
