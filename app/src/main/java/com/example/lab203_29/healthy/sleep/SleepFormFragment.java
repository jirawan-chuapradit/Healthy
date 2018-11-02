package com.example.lab203_29.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab203_29.healthy.R;

public class SleepFormFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }


    SQLiteDatabase myDB;
    ContentValues _row;

    private Button backBtn,saveBtn;
    private String date, sleep_time, wake_up_time;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        backBtn = getView().findViewById(R.id.sleep_form_back_btn);
        saveBtn = getView().findViewById(R.id.saveBtn);
        
        backBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==backBtn){
            Log.d("SLEEP FORM", "CLICK BACK");
            back();
        }
        else if(v==saveBtn){
            Log.d("SLEEP FORM", "CLICK SAVE");
            save();
        }
    }

    private void save() {

        // Gets the data repository in write mode
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);


        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS sleeps (_id INTEGER PRIMARY KEY AUTOINCREMENT, sleep VARCHAR(5), wake VARCHAR(5), date VARCHAR(11))"
        );

        getParameter();

        Sleep _itemSleep = new Sleep();
        _itemSleep.setContent(sleep_time, wake_up_time, date);

        _row = _itemSleep.getContent();

        myDB.insert("sleeps", null, _row);

        Log.d("SLEEP", "GOTO SLEEP");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new SleepFragment())
                .addToBackStack(null)
                .commit();


    }

    private void getParameter() {
        //GET INPUT FROM FRAGMENT WEIGHTFORMFRAGEMNT
        EditText _date = getView().findViewById(R.id.sleep_form_date);
        EditText _sleepTime = getView().findViewById(R.id.sleep_form_sleep_time);
        EditText _wakeTime = getView().findViewById(R.id.sleep_form_wake_up_time);

        date = _date.getText().toString();
        sleep_time = _sleepTime.getText().toString();
        wake_up_time = _wakeTime.getText().toString();

    }

    private void back() {
        Log.d("SLEEP", "GOTO SLEEP");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new SleepFragment())
                .addToBackStack(null)
                .commit();
    }
}
