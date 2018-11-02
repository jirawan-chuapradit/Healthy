package com.example.lab203_29.healthy.sleep;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab203_29.healthy.MenuFragment;
import com.example.lab203_29.healthy.R;

import java.util.ArrayList;

public class SleepFragment extends Fragment implements View.OnClickListener {

    private Button backBtn,addBtn;
    SQLiteDatabase myDB;
    ListView _sleepList;
    ArrayList<Sleep> sleeps = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backBtn = getView().findViewById(R.id.back_btn);
        addBtn = getView().findViewById(R.id.add_sleep);
        _sleepList = (ListView) getView().findViewById(R.id.sleep_list);

        backBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        SleepAdapter _sleepAdapter = new SleepAdapter(
                getActivity(),
                R.layout.fragment_sleep_item,
                sleeps
        );

        //open to use db
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        //create table if not exist
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS sleeps (_id INTEGER PRIMARY KEY AUTOINCREMENT, sleep VARCHAR(5), wake VARCHAR(5), date VARCHAR(11))"
        );

        //delete
//        myDB.delete("sleeps", "_id=6", null);
//        Log.d("sleeps ", "DELETE ID = 6");


        //query data
        Cursor myCursor = myDB.rawQuery("SELECT * FROM sleeps", null);

        _sleepAdapter.clear();

        while(myCursor.moveToNext()){
            String _timeSleep = myCursor.getString(1);
            String _timeWake = myCursor.getString(2);
            String _date = myCursor.getString(3);

            Log.d("SLEEP", "_id : "+myCursor.getInt(0)+" sleep : "+_timeSleep+" wake : "+_timeWake+" date : "+_date);

            sleeps.add(new Sleep(_timeSleep, _timeWake, _date));
        }

        _sleepList.setAdapter(_sleepAdapter);

        _sleepList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id = _sleepList.getItemIdAtPosition(position);
                Log.d("SLEEP", "Position = "+position+" _id = "+(id+1));




            }
        });

        myCursor.close();

        /****************************************************************************
         *   Cursor myCursor =                                                      *
         *           myDB.rawQuery("select name, age, is_single from sleeps", null);  *
         *while(myCursor.moveToNext()) {                                            *
         *      String name = myCursor.getString(0);                                *
         *     int age = myCursor.getInt(1);                                        *
         *    boolean isSingle = (myCursor.getInt(2)) == 1 ? true:false;            *
         *}                                                                         *
         *   myCursor.close;                                                        *
         ****************************************************************************/


    }

    @Override
    public void onClick(View v) {
        if(v == backBtn){
            Log.d("SLEEP", "CLICK BACK");
            back();
        }
        else if(v == addBtn){
            Log.d("SLEEP", "CLICK ADD SLEEP");
            addSleep();
        }else if(v == _sleepList){

        }
    }

    private void addSleep() {
        Log.d("SLEEP", "GOTO SLEEP FORM");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new SleepFormFragment())
                .addToBackStack(null)
                .commit();
    }

    private void back() {
        Log.d("SLEEP", "GOTO MENU");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }
}
