package com.example.lab203_29.healthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_29.healthy.weight.WeightFragment;

import java.util.ArrayList;

/**
 * Created by LAB203_37 on 27/8/2561.
 */

public class MenuFragment extends Fragment {


    ArrayList<String> _menu = new ArrayList<>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Setup");

        ArrayAdapter<String> _menuAdoapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,_menu
        );


        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdoapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterVeiw, View view, int i,long l) {
                if (_menu.get(i).contains("Weight")){
                    Log.d("Menu", "Click on menu ="+_menu.get(i));
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                }else if(_menu.get(i).contains("BMI")){
                    Log.d("Menu", "Click on menu ="+_menu.get(i));
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);


    }
}

