package com.example.lab203_29.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;

public class SleepFormFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }


    SQLiteDatabase myDB;
    ContentValues _row;
    //Firebase
    private FirebaseAuth fbAuth;
    private String uid;

    private Button backBtn,saveBtn;
    private  EditText _date,_sleepTime,_wakeTime;
    private String date, sleep_time, wake_up_time,update_date,update_sleep,update_wake;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        backBtn = getView().findViewById(R.id.sleep_form_back_btn);
        saveBtn = getView().findViewById(R.id.saveBtn);
        
        backBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        fbAuth = FirebaseAuth.getInstance();
        //GET VALUDE FROM FIREBASE
        uid = fbAuth.getCurrentUser().getUid();

        //get Notify count
        SharedPreferences prefs = getContext().getSharedPreferences("Healthy",Context.MODE_PRIVATE);
        update_date = prefs.getString(uid+"_s_date", "none");
        update_sleep = prefs.getString(uid+"_s_time", "none");
        update_wake = prefs.getString(uid+"_w_time","none");

        if(!update_date.equals("none")||!update_sleep.equals("none")||!update_wake.equals("none")){
           getParameter();
           setParameter(update_date,update_sleep,update_wake);
        }


    }

    private void setParameter(String update_date, String update_sleep, String update_wake) {
        _date.setText(update_date);
        _sleepTime.setText(update_sleep);
        _wakeTime.setText(update_wake);

        //setNone
        SharedPreferences.Editor prefs = getContext().getSharedPreferences("Healthy",MODE_PRIVATE).edit();
        prefs.putString(uid+"_s_date", "none");
        prefs.putString(uid+"_s_time","none");
        prefs.putString(uid+"_w_time", "none");
        prefs.apply();
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
        converseToString();

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


        if(!update_date.equals("none")||!update_sleep.equals("none")||!update_wake.equals("none")){
            saveUpdateParameter();
        }
    }

    private void saveUpdateParameter() {

    }

    private void converseToString() {
        date = _date.getText().toString();
        sleep_time = _sleepTime.getText().toString();
        wake_up_time = _wakeTime.getText().toString();
    }


    private void getParameter() {
        //GET INPUT FROM FRAGMENT WEIGHTFORMFRAGEMNT
         _date = getView().findViewById(R.id.sleep_form_date);
         _sleepTime = getView().findViewById(R.id.sleep_form_sleep_time);
         _wakeTime = getView().findViewById(R.id.sleep_form_wake_up_time);

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
