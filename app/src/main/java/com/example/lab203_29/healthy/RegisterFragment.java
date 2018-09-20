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

public class RegisterFragment extends Fragment {

    private FirebaseAuth fbAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fbAuth = FirebaseAuth.getInstance();

        initRegisterBtn();
    }



    void initRegisterBtn(){
        Button _regBtn = (Button) getView().findViewById(R.id.registerBtn);
        _regBtn.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){

                // GET INPUT FROM FRAGMENT_REGISTER
                EditText _email = (EditText) getView().findViewById(R.id.eMail);
                EditText _rePassword = (EditText)getView().findViewById(R.id.login_repassword);
                EditText _password = (EditText)getView().findViewById(R.id.login_password);
                // CONVESE INPUT TO STRING
                String _passwordStr = _password.getText().toString();
                String _rePasswordStr = _rePassword.getText().toString();
                String _eMailStr = _email.getText().toString();

                if(_eMailStr.isEmpty()||_passwordStr.isEmpty() || _rePasswordStr.isEmpty()){
                    Toast.makeText(
                            getActivity(),
                            "FIELD NAME IS EMPTY",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                }else if(!_passwordStr.equals(_rePasswordStr)){
                    Toast.makeText(
                            getActivity(),
                            "Password is not equals Re-Password",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER","PASSWORD IS NOT EQUALS RE-PASSWORD" );
                }else if(_passwordStr.length() < 6){
                    Toast.makeText(
                            getActivity(),
                            "Password should more than 5",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER","PASSWORD LESS THAN 6" );
                } else{
                    fbAuth.createUserWithEmailAndPassword(_eMailStr,_passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            sendVerifiedEmail(authResult.getUser());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("USER","USER ALREADY EXIST" );
                            Toast.makeText(getActivity(),"ERROR = " + e.getMessage()
                                    ,Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });
    }


    void sendVerifiedEmail(FirebaseUser _user){
        _user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getActivity(),"YOU HAS BEEN REGISTER PLEASE CONFIRM YOUR E-MAIL"
                        ,Toast.LENGTH_SHORT)
                        .show();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new LoginFragment())
                        .commit();
                Log.d("USER", "GOTO LOGIN");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),"ERROR = " + e.getMessage()
                        ,Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

}
