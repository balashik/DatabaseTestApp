package com.example.com.butler.mybutler;

import com.example.com.butler.mybutler.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddNewTask extends Activity {
	DBAdapter mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_add_new_task);
	        //Setting references to view components.
	        Button btAddNewTask = (Button)findViewById(R.id.addToDB);
	        
	        //Open DB connection.
	        openDB();
	        
	        //'Add new task' button onCLickListener
	        btAddNewTask.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView twTitle = (TextView)findViewById(R.id.taskTitle);
				    TextView twDescription = (TextView)findViewById(R.id.taskDescription);
					String s_taskTitle = twTitle.getText().toString();
			        String s_taskDescription = twDescription.getText().toString();
			        
			        mydb.insertRow(s_taskTitle, s_taskDescription);
			        finish();
				}
			});
	        
	        
	        
	}

	private void openDB() {
		mydb = new DBAdapter(this);
		mydb.open();
	}

}
