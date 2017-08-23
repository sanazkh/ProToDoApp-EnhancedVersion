package com.example.myapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapp.models.Task;
import com.example.sanazk.todolistapplicationwithdb.R;

import java.util.ArrayList;

/**
 * Created by sanazk on 8/18/17.
 */

public class TodoItemsAdapter extends ArrayAdapter<Task> {

    private static class ViewHolder {
        TextView name;
        TextView priority;
        TextView dueDate;
    }

    public TodoItemsAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.taskName);
            viewHolder.dueDate = (TextView) convertView.findViewById(R.id.taskDuedate);
            viewHolder.priority = (TextView) convertView.findViewById(R.id.taskPriority);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(task.getName());
        viewHolder.dueDate.setText(task.getDueDate());
        viewHolder.priority.setText(task.getPrio());
        return convertView;
    }
}
