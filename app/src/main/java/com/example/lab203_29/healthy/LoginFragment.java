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

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        initRegisterBtn();

        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // should private method Id  by jj
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText)getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();

                //compare empty?
                if(_userIdStr.isEmpty()||_passwordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "empy naja",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                }else if (_userIdStr.equals("admin") && _passwordStr.equals("admin")){
                    Log.d("USER", "GOTO Menu");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new MenuFragment())
                            .commit();

                }else {
                    Log.d("USER", "INVALID USERNAME OR PASSWORD");
                    Toast.makeText(
                            getActivity(),
                            "INVALID USERNAME OR PASSWORD",
                            Toast.LENGTH_SHORT
                    ).show();
                }}
                });
            }


    private void initRegisterBtn(){
        TextView _regBtn = (TextView) getView().findViewById(R.id.login_register_btn);
        _regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new RegisterFragment())
                        .commit();
                Log.d("USER", "GOTO REGISTER");
            }
        });
    }

    }



