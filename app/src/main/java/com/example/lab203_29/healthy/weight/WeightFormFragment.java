package com.example.lab203_29.healthy.weight;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab203_29.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class WeightFormFragment extends Fragment {

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        initSaveBtn();
        initBackBtn();
    }

    private void initBackBtn() {
        Button _backBtn = (Button) getView().findViewById(R.id.back_btn);
        _backBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO WeightFragment");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new WeightFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }));
    }

    void initSaveBtn() {
         Button _saveBtn = (Button) getView().findViewById(R.id.saveBtn);
         _saveBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //GET INPUT FROM FRAGMENT WEIGHTFORMFRAGEMNT
                 EditText dateWeight = (EditText) getView().findViewById(R.id.dateWeight);
                 EditText weight = (EditText) getView().findViewById(R.id.weightForm);

                 //CONVERSE TO STRING
                 String dateWeightStr = dateWeight.getText().toString();
                 String weightStr = weight.getText().toString();

                 if(dateWeightStr.isEmpty()||weightStr.isEmpty()){
                     Toast.makeText(
                             getActivity(),
                             "Date or Weight are empty",
                             Toast.LENGTH_SHORT
                     ).show();
                     Log.d("USER", "DATE OR WEIGHT ARE EMPTY");
                 }else{
                     String _user = auth.getCurrentUser().getUid();
                     Weight data = new Weight(dateWeightStr,Integer.parseInt(weightStr)," ");

                     firestore.collection("myFitness")
                             .document(_user)
                             .collection("weight")
                             .document(dateWeightStr)
                             .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                             Toast.makeText(
                                     getActivity(),
                                     "your value has been saved!!!",
                                     Toast.LENGTH_SHORT
                             ).show();
                             Log.d("USER", "VALUE HAS BEEN SAVED");

                             getActivity().getSupportFragmentManager()
                                     .beginTransaction()
                                     .replace(R.id.main_view,new WeightFragment())
                                     .commit();
                             Log.d("USER", "GOTO WEIGHT");
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Log.d("SYSTEM", "ERRROR =" + e.getMessage());
                             Toast.makeText(getContext(),"ERROR = "+e.getMessage(),Toast.LENGTH_SHORT).show();
                         }
                     });
                 }

             }
         });

    }


}
