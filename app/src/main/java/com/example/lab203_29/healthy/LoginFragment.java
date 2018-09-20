package com.example.lab203_29.healthy;

//import android.app.Fragment;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by LAB203_29 on 20/8/2561.
 */

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login,container, false);
    }

    //CHECK IS ALREADY LOGIN
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if(FirebaseAuth.getInstance().getCurrentUser()!= null){

            Log.d("USER", "USER ALREADY LOG IN");
            Log.d("USER", "GOTO Menu");
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view,new MenuFragment())
                    .commit();
            return;
        }

        initRegisterBtn();

        TextView _loginBtn = (TextView) getView().findViewById(R.id.login_login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //GET INPUT FROM FRAGMENT LOGIN
                EditText _userId = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText)getView().findViewById(R.id.login_password);
                String _userIdStr = _userId.getText().toString();
                String _passwordStr = _password.getText().toString();

                if(_userIdStr.isEmpty()||_passwordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "USERNAME OR PASSWORD IS EMPTY",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "USER OR PASSWORD IS EMPTY");
                }else if (_userIdStr.equals("admin") && _passwordStr.equals("admin")){
                    Log.d("USER", "GOTO Menu");
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new MenuFragment())
                            .commit();

                } else {

                    //FIREBASE AUTH USE EMAIL/PASSWORD SIGN IN
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(_userIdStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            sendVeriFiedEmail(authResult.getUser());

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("USER", "INVALID USER OR PASSWORD");
                            Toast.makeText(getContext(),"ERROR = "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }}
                });
            }

     void sendVeriFiedEmail(final FirebaseUser _user) {
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //USER CONFIRM EMAIL
                if(_user.isEmailVerified()){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view,new MenuFragment())
                            .commit();
                    Log.d("USER", "GOTO Menu");
                }else{
                    Log.d("USER", "EMAIL IS NOT VERIFIED");
                    Toast.makeText(getContext(),"EMAIL IS NOT VERIFIED",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"ERROR = " + e.getMessage()
                        ,Toast.LENGTH_SHORT)
                        .show();
                Log.d("SYSTEM", "ERROR = " + e.getMessage());
            }
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



