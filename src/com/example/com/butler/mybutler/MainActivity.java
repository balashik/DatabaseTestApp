package com.example.com.butler.mybutler;

import java.util.ArrayList;

import com.example.com.butler.mybutler.R;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	DBAdapter mydb;
	TaskAdapter taskAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b_addNew = (Button)findViewById(R.id.addButton);
        b_addNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddNewTask.class);
				startActivity(intent);
			}
		});
        populateTaskList();
    }

	private void openDB() {
		mydb = new DBAdapter(this);
		mydb.open();
	}

	private void populateTaskList() {
		// open Database connection
		openDB();
		//create an ArrayList of tasks and populate it
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		Cursor cursor = mydb.getAllRows();
		if(cursor.moveToFirst()){
				do{
					int id = cursor.getInt(DBAdapter.COL_ROWID);
					String title = cursor.getString(DBAdapter.COL_TASKTITLE);
					String description = cursor.getString(DBAdapter.COL_TASKDESCRIPTION);
					String date = cursor.getString(DBAdapter.COL_DATE);
					String time = cursor.getString(DBAdapter.COL_TIME);
					
					tasks.add(new Task(id, title,description,date,time));
					//System.out.println("title: "+ title + " description: "+ description +"date:"+ date);
					
				}while(cursor.moveToNext());
			}
		//close cursor and database connection
		cursor.close();
		mydb.close();
		
		//Build adapter
		taskAdapter = new TaskAdapter(MainActivity.this, 0, tasks);
		//configure the list view
		ListView list = (ListView)findViewById(R.id.list_of_tasks);
		list.setAdapter(taskAdapter);
		}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		populateTaskList();
	}
}

