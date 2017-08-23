package com.example.myapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanazk.todolistapplicationwithdb.R;
import com.example.myapp.models.Task;
import com.example.myapp.adapters.TodoItemsAdapter;
import com.example.myapp.models.TodoItemsDbHelper;

import java.util.ArrayList;

public class CreateTodoItemActivity extends AppCompatActivity {

    TodoItemsDbHelper dbHelper;
    TodoItemsAdapter todoItemsAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new TodoItemsDbHelper(this);
        listView = (ListView) findViewById(R.id.taskList);
        loadTaskList();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                ArrayList<Task> taskList = dbHelper.getAllTasks();
                Context context = view.getContext();
                LinearLayout linearLayout = new LinearLayout(context);
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (20*scale + 0.5f);

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
                final TextView taskName = new TextView(context);
                taskName.setText("Task Name");
                taskName.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName);

                final EditText name = new EditText(context);
                name.setText(taskList.get(position).getName());
                name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                if(name.getText().toString().length() == 0){
                    name.setError("Task name is required.");
                }
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(10);
                name.setFilters(filterArray);

                final String originalValue = taskList.get(position).getName().toString();
                linearLayout.addView(name);

                final TextView taskName1 = new TextView(context);
                taskName1.setText("Task Description");
                taskName1.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName1);



                final EditText desc = new EditText(context);
                desc.setText(taskList.get(position).getDesc());
                desc.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);


                linearLayout.addView(desc);




                final TextView taskName2 = new TextView(context);
                taskName2.setText("Task Due Date");
                taskName2.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName2);



                final EditText due = new EditText(context);
                due.setText(taskList.get(position).getDueDate());
                due.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                linearLayout.addView(due);

                final TextView taskName3 = new TextView(context);
                taskName3.setText("Task Priority");
                taskName3.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName3);


                ArrayList<String> spinnerArray1 = new ArrayList<String>();
                spinnerArray1.add("High");
                spinnerArray1.add("Medium");
                spinnerArray1.add("Low");
                final Spinner spinner1 = new Spinner(context);
                ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerArray1);
                spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(spinnerArrayAdapter1);
                int spinnerPosition = spinnerArrayAdapter1.getPosition(taskList.get(position).getPrio());
                spinner1.setSelection(spinnerPosition);
                linearLayout.addView(spinner1);

                final TextView taskName4 = new TextView(context);
                taskName4.setText("Task Status");
                taskName4.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName4);

                ArrayList<String> spinnerArray = new ArrayList<String>();
                spinnerArray.add("To Do");
                spinnerArray.add("Done");
                final Spinner spinner = new Spinner(context);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerArray);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
                int spinnerPosition1 = spinnerArrayAdapter.getPosition(taskList.get(position).getStatus());
                spinner.setSelection(spinnerPosition1);
                linearLayout.addView(spinner);


                AlertDialog dialog = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle).setTitle("Edit the task").setView(linearLayout)
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getApplicationContext();
                                if (!desc.getText().toString().isEmpty() && !name.getText().toString().isEmpty()
                                        && !due.getText().toString().isEmpty()) {
                                    Task task = new Task(name.getText().toString(), desc.getText().toString(), due.getText().toString(),
                                            spinner1.getSelectedItem().toString(), spinner.getSelectedItem().toString());

                                    dbHelper.updateTask(name.getText().toString(), desc.getText().toString(), due.getText().toString(),
                                            spinner1.getSelectedItem().toString(), spinner.getSelectedItem().toString(), originalValue);

                                    dbHelper.close();
                                    Toast.makeText(context, "Task Has Been Edited", Toast.LENGTH_SHORT).show();
                                    loadTaskList();
                                } else{
                                    Toast.makeText(context, "Task Cannot be Edited", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getApplicationContext();
                                if (!name.getText().toString().isEmpty()) {
                                    dbHelper.deleteTask(name.getText().toString());
                                    dbHelper.close();

                                    Toast.makeText(context, "Task Has Been Deleted", Toast.LENGTH_SHORT).show();
                                    loadTaskList();
                                } else{
                                    Toast.makeText(context, "Task Cannot Be Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNeutralButton("Cancel", null).create();
                dialog.show();
                return true;
            }
        });

    }

    private void loadTaskList() {

        ArrayList<Task> taskList = dbHelper.getAllTasks();
        if(todoItemsAdapter == null){
            todoItemsAdapter = new TodoItemsAdapter(this, taskList);
            listView.setAdapter(todoItemsAdapter);

        } else{
            todoItemsAdapter.clear();
            todoItemsAdapter.addAll(taskList);
            todoItemsAdapter.notifyDataSetChanged();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu, menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.action_add_todo_task:
                LinearLayout linearLayout = new LinearLayout(this);
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (20*scale + 0.5f);

                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                final TextView taskName = new TextView(this);
                taskName.setText("Task Name");
                taskName.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName);
                final EditText name = new EditText(this);
                name.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                if(name.getText().toString().length() == 0){
                    name.setError("Task name is required.");
                }
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(10);
                name.setFilters(filterArray);
                name.setHint("Max of 10 characters");
                linearLayout.addView(name);


                final TextView taskName1 = new TextView(this);
                taskName1.setText("Task Description");
                taskName1.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName1);
                final EditText desc = new EditText(this);
                desc.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                if(desc.getText().toString().length() == 0){
                    desc.setError("Task description is required.");
                }
                linearLayout.addView(desc);
                final TextView taskName2 = new TextView(this);
                taskName2.setText("Task Due Date");
                taskName2.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName2);
                final EditText due = new EditText(this);
                due.getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

                if(due.getText().toString().length() == 0){
                    due.setError("Task due date is required.");
                }
                due.setHint("mm/dd/yyyy");
                linearLayout.addView(due);
                final TextView taskName3 = new TextView(this);
                taskName3.setText("Task Priority");
                taskName3.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName3);
                ArrayList<String> spinnerArray1 = new ArrayList<String>();
                spinnerArray1.add("High");
                spinnerArray1.add("Medium");
                spinnerArray1.add("Low");
                final Spinner spinner1 = new Spinner(this);
                ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray1);
                spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(spinnerArrayAdapter1);
                linearLayout.addView(spinner1);
                final TextView taskName4 = new TextView(this);
                taskName4.setText("Task Status");
                taskName4.setTextColor(Color.parseColor("#e95905"));
                linearLayout.addView(taskName4);
                ArrayList<String> spinnerArray = new ArrayList<String>();
                spinnerArray.add("To Do");
                spinnerArray.add("Done");
                final Spinner spinner = new Spinner(this);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
                linearLayout.addView(spinner);

                AlertDialog dialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle).setTitle("Add a New Task").setView(linearLayout)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getApplicationContext();

                                if(!desc.getText().toString().isEmpty() && !name.getText().toString().isEmpty()
                                        && !due.getText().toString().isEmpty()) {
                                    dbHelper.addTask(new Task(name.getText().toString(), desc.getText().toString(), due.getText().toString(),
                                            spinner1.getSelectedItem().toString(), spinner.getSelectedItem().toString()));

                                    dbHelper.close();

                                    Toast.makeText(context, "Task Has Been Added.", Toast.LENGTH_SHORT).show();
                                    loadTaskList();
                                }
                                else{
                                    Toast.makeText(context, "Task Cannot be Added.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("Cancel", null).create();
                dialog.show();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) findViewById(R.id.taskName);
        String taskName = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(dbHelper.getTask(taskName));
        loadTaskList();

    }

}
