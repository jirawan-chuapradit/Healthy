package com.example.lab203_29.healthy;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LAB203_29 on 20/8/2561.
 */

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRegisterBtn();
    }








    void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.login_register_btn);
        _regBtn.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){

                // should private method Id  by jj
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText)getView().findViewById(R.id.login_password);
                EditText _name = (EditText)getView().findViewById(R.id.name);
                EditText _age = (EditText)getView().findViewById(R.id.age);

                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();
                String _nameStr = _name.getText().toString();
                String _ageStr = _age.getText().toString();

                //compare empty?
                if(_userIdStr.isEmpty()||_passwordStr.isEmpty() || _nameStr.isEmpty() || _ageStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "empy naja",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD OR name or age IS EMPTY");
                }else{

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new BMIFragment())
                        .commit();
                Log.d("USER", "GOTO BMI");
                }
            }
        });
    }
}
