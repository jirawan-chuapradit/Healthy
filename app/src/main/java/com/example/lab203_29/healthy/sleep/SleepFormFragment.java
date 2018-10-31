package com.example.lab203_29.healthy.sleep;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab203_29.healthy.MenuFragment;
import com.example.lab203_29.healthy.R;

import static android.content.Context.MODE_PRIVATE;

public class SleepFormFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form,container,false);
    }
    
    private Button backBtn,saveBtn;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        backBtn = getView().findViewById(R.id.back_btn);
        saveBtn = getView().findViewById(R.id.saveBtn);
        
        backBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        SQLiteDatabase sleepDB = SQLiteDatabase
                .openDatabase("sleep.db", MODE_PRIVATE, null);
        
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
