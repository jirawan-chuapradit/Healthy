package com.example.lab203_29.healthy.sleep;

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

public class SleepFragment extends Fragment implements View.OnClickListener {

    private Button backBtn,addBtn;

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

        backBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
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
