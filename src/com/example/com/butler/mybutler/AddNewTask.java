package com.example.com.butler.mybutler;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import com.example.com.butler.mybutler.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.CalendarContract.CalendarAlerts;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewTask extends Activity {
	DBAdapter mydb;

	static final int dialog_id = 1;
	String date;
	//Calendar myCal = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_task);
		// Setting references to view components.

		Button btAddNewTask = (Button) findViewById(R.id.addToDB);
		

		// Open DB connection.
		openDB();

		// 'Add new task' button onCLickListener
		btAddNewTask.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DateFormat df= new SimpleDateFormat();
				TextView twTitle = (TextView) findViewById(R.id.taskTitle);
				TextView twDescription = (TextView) findViewById(R.id.taskDescription);
				String s_taskTitle = twTitle.getText().toString();
				String s_taskDescription = twDescription.getText().toString();
				System.out.println(date.toString());
				mydb.insertRow(s_taskTitle, s_taskDescription,date);
				finish();
			}
		});

	}

	private void openDB() {
		mydb = new DBAdapter(this);
		mydb.open();
	}

	// add date
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			
			date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
			Toast.makeText(getBaseContext(), date, Toast.LENGTH_LONG).show();
		}
	};
	protected Dialog onCreateDialog(int id) {

		if (id == dialog_id)
			return new DatePickerDialog(this, mDateSetListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		return null;
	}

	

	public void setHour(View v) {
		System.out.println("setHour");
	}

	public void setDate(View v) {

//		cDay = Calendar.get(Calendar.DAY_OF_MONTH);
//		cMonth = myCal.get(Calendar.MONTH)+1;
//		cYear = myCal.get(Calendar.YEAR);
		//System.out.println("day:" + cDay + "month" + cMonth + "year" + cYear);
		showDialog(dialog_id);

	}

}
