package com.example.lab203_29.healthy;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lab203_29.healthy.sleep.SleepFragment;
import com.example.lab203_29.healthy.weight.WeightFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by LAB203_37 on 27/8/2561.
 */

public class MenuFragment extends Fragment {

    SQLiteDatabase myDB;
    ArrayList<String> _menu = new ArrayList<>();


    //Firebase
    private FirebaseAuth fbAuth;
    private String uid;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        fbAuth = FirebaseAuth.getInstance();
        //GET VALUDE FROM FIREBASE
        uid = fbAuth.getCurrentUser().getUid();

        //setNone
        SharedPreferences.Editor prefs = getContext().getSharedPreferences("Healthy",MODE_PRIVATE).edit();
        prefs.putString(uid+"_s_date", "none");
        prefs.putString(uid+"_s_time","none");
        prefs.putString(uid+"_w_time", "none");
        prefs.putInt(uid+"_id", -1);
        prefs.apply();

//////        // Gets the data repository in write mode
//        myDB = getActivity().openOrCreateDatabase("my.db",Context.MODE_PRIVATE, null);
//
//
//        myDB.execSQL(
//                "DROP TABLE IF  EXISTS sleeps");

        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Sleep");
        _menu.add("Setup");
        _menu.add("Sign Out");

        ArrayAdapter<String> _menuAdoapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,_menu
        );



        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdoapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterVeiw, View view, int i,long l) {
                Log.d("Menu", "Click on menu ="+_menu.get(i));
                if (_menu.get(i).contains("Weight")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO WEIGHT");
                }else if(_menu.get(i).contains("BMI")){
                   getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new BMIFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO BMI");
                }
                else if(_menu.get(i).contains("Sleep")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new SleepFragment())
                            .addToBackStack(null)
                            .commit();
                }
                else if(_menu.get(i).contains("Sign Out")){
                    FirebaseAuth.getInstance().signOut();
                   getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("USER", "ACCOUNT HAS BEEN SIGN OUT");
                    Log.d("USER", "GOTO LOG IN");
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

