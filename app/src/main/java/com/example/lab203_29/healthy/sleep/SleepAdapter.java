package com.example.lab203_29.healthy.sleep;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_29.healthy.R;

import java.util.ArrayList;
import java.util.List;
/********************************************************
 *   intent:set parameter for each sleep item           *
 *   pre-condition: query value from sql lite           *
 *   post-condition: show parameter for each sleep item *
 ********************************************************/

public class SleepAdapter extends ArrayAdapter<Sleep> {

    List<Sleep> sleeps = new ArrayList<Sleep>();
    Context context;


    public SleepAdapter(@NonNull Context context, int resource, @NonNull List<Sleep> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sleeps = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View _sleepItem = LayoutInflater.from(context).inflate( R.layout.fragment_sleep_item ,parent,false);

        TextView _date = (TextView) _sleepItem.findViewById(R.id.sleep_item_date);
        TextView _timeRange = (TextView) _sleepItem.findViewById(R.id.sleep_item_time_range);
        TextView _totalTime = (TextView) _sleepItem.findViewById(R.id.sleep_item_total_time);

        Sleep _row = sleeps.get(position);
        _date.setText(_row.getDate());
        _timeRange.setText(_row.getSleepTime() + " - " + _row.getWakeUpTime());
        _totalTime.setText(_row.getDiffTime());

        return _sleepItem;
    }

}
