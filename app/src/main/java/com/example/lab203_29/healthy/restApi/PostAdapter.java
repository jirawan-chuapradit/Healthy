package com.example.lab203_29.healthy.restApi;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.example.lab203_29.healthy.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter {


    ArrayList<JSONObject> postList;
    Context context;

    public PostAdapter( @NonNull Context context, int resource,  @NonNull ArrayList<JSONObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.postList = objects;
    }

    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItem = LayoutInflater.from(context).inflate(R.layout.fragment_post_item, parent, false);
        JSONObject postObj = postList.get(position);
        TextView postHeader = postItem.findViewById(R.id.post_list_item_header);
        TextView postBody  = postItem.findViewById(R.id.post_list_item_body);
        try
        {
            postHeader.setText(postObj.getString("id") + " : " + postObj.getString("title"));
            postBody.setText(postObj.getString("body"));
        }
        catch (JSONException e)
        {
            Log.d("test", "catch JSONException : " + e.getMessage());
        }
        return postItem;
    }
}
