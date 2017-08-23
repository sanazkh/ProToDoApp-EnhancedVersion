package com.example.myapp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sanazk on 8/18/17.
 */

public class TodoItemsDbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "taskDetailsManager.db";

    // Contacts table name
    private static final String TABLE_TASKS = "tasks";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_DUE = "due_date";
    private static final String KEY_PRIO = "priority";
    private static final String KEY_STATUS = "status";


    public TodoItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DESC + " TEXT," +  KEY_DUE + " TEXT," + KEY_PRIO + " TEXT," + KEY_STATUS + " TEXT" + ")";


        db.execSQL(CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        onCreate(db);
    }


    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESC, task.getDesc());
        values.put(KEY_DUE, task.getDueDate());
        values.put(KEY_PRIO, task.getPrio());
        values.put(KEY_STATUS, task.getStatus());
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }


   public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESC, KEY_DUE, KEY_PRIO, KEY_STATUS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return task;
    }


   public Task getTask(String taskName) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESC, KEY_DUE, KEY_PRIO, KEY_STATUS }, KEY_NAME + "=?",
                new String[] { String.valueOf(taskName) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return task;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setDesc(cursor.getString(2));
                task.setDueDate(cursor.getString(3));
                task.setPrio(cursor.getString(4));
                task.setStatus(cursor.getString(5));

                taskList.add(task);
            } while (cursor.moveToNext());
        }


        return taskList;
    }
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_DESC, task.getDesc());
        values.put(KEY_DUE, task.getDueDate());
        values.put(KEY_PRIO, task.getPrio());
        values.put(KEY_STATUS, task.getStatus());

        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    public int updateTask(String name, String desc, String due, String prio, String status , String newName ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        Log.v("name", name);
        values.put(KEY_DESC, desc);
        values.put(KEY_DUE, due);
        values.put(KEY_PRIO, prio);
        values.put(KEY_STATUS, status);

        return db.update(TABLE_TASKS, values, KEY_NAME + " = ?",
                new String[] { String.valueOf(newName) });
    }


    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
        db.close();
    }

    public void deleteTask(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(TABLE_TASKS, KEY_NAME + " = ?", new String[] { String.valueOf(name) });
        db.close();

    }



    public int getTaskCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();


        return cursor.getCount();
    }


}
