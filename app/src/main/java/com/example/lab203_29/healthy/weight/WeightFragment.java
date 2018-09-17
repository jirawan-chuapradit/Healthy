package com.example.lab203_29.healthy.weight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab203_29.healthy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;


/**
 * Created by LAB203_37 on 27/8/2561.
 */

public class WeightFragment extends Fragment {


    ArrayList<Weight> weights = new ArrayList<>();

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        weights.add(new Weight("02 Jan 2018", 64 , "Down"));
        weights.add(new Weight("03 Jan 2018", 63 , "UP"));

        ListView _weightList = (ListView) getView().findViewById(R.id.weight_list);
        final WeightAdapter _weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights
        );

        _weightList.setAdapter(_weightAdapter);
        initAddWeightBtn();

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        String _user = auth.getCurrentUser().getUid();


        firestore.collection("myfitness")
                .document(_user)
                .collection("weight")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        for (QueryDocumentSnapshot doc: queryDocumentSnapshots){
                            if((doc.get("date") != null) || (doc.get("status") != null) || (doc.get("weight") != null)){
                                weights.add(doc.toObject(Weight.class));
                            }
                        }
                        _weightAdapter.notifyDataSetChanged();
                    }
                })

    }

    private void initAddWeightBtn() {
        Button _addWeightBtn = (Button) getView().findViewById(R.id.add_weight_btn);
        _addWeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new WeightFormFragment())
                        .commit();
                Log.d("USER", "GOTO Weight Form");
            }
        });
    }



}
