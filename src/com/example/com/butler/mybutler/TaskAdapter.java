package com.example.com.butler.mybutler;

import java.util.ArrayList;
import java.util.List;

import com.example.com.butler.mybutler.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<Task> {
private Activity activity;
private ArrayList<Task> lTasks;
private static LayoutInflater inflater;

public TaskAdapter(Activity activity, int textViewResourceId, ArrayList<Task> _lTask){
    super(activity, textViewResourceId, _lTask);
    try{
    	this.activity = activity;
    	this.lTasks =_lTask;
    	inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	} catch (Exception e) {
	
	}
}

public int getCount() {
    return lTasks.size();
}


public long getItemId(int position) {
    return position;
}

public static class ViewHolder {
    public TextView display_title;
    public TextView display_description;             

}

public View getView(int position, View convertView, ViewGroup parent) {
    View vi = convertView;
    final ViewHolder holder;
    try {
        if (convertView == null) {
        	vi = inflater.inflate(R.layout.task_list_component, null);
            holder = new ViewHolder();

            holder.display_title = (TextView) vi.findViewById(R.id.largeTitle);
            holder.display_description = (TextView) vi.findViewById(R.id.smallDescription);


            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }



        holder.display_title.setText(lTasks.get(position).getTitle());
        holder.display_description.setText(lTasks.get(position).getDescription());


    } catch (Exception e) {


    }
    return vi;
}
}