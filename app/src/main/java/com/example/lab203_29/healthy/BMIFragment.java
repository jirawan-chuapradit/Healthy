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

public class BMIFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi,container,false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRegisterBtn();
        initBackBtn();
    }

    private void initBackBtn() {
        Button _backBtn = (Button) getView().findViewById(R.id.back_btn);
        _backBtn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO Menu");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view,new MenuFragment())
                        .commit();
            }
        }));
    }

    void initRegisterBtn() {

        TextView _calBtn = (TextView) getView().findViewById(R.id.cal_btn);
        _calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // should private method Id  by jj
                EditText _height = (EditText) getView().findViewById(R.id.hight);
                EditText _weight = (EditText) getView().findViewById(R.id.weight);

                String _heightStr = _height.getText().toString();
                String _weightStr = _weight.getText().toString();

                float _heightFloat = Float.parseFloat(_heightStr);
                float _weightFloat = Float.parseFloat(_weightStr);


                //compare empty?
                if (_weightStr.isEmpty() || _heightStr.isEmpty()) {
                    Toast.makeText(
                            getActivity(),
                            "FIELD NAME IS EMPTY",
                            Toast.LENGTH_SHORT
                    ).show();
                    Log.d("USER", "FIELD NAME IS EMPTY");
                } else {
                    Log.d("USER", "BMI IS VALUE");
                    TextView _resultVeiw = (TextView) getView().findViewById(R.id.result_view);
                    float _total = _weightFloat/ ((_heightFloat/100)*(_heightFloat/100));
                    _resultVeiw.setText(_total + "");


                }

            }


        });
    }}
