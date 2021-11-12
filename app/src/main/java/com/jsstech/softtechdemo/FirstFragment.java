package com.jsstech.softtechdemo;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FirstFragment extends Fragment {
    RecyclerView recyclerView;
    private LandScapeAdapter adapter;
    private List<MyModel> list;
    private RequestQueue requestQueue;


    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Landscape Mode");
        list = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        parseJSON();


        View v = inflater.inflate(R.layout.fragment_first,container,false);
        recyclerView = v.findViewById(R.id.landScapeRv);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);

//        GridLayoutManager gridLayout=new GridLayoutManager(getContext(),2);
//        recyclerView.setLayoutManager(gridLayout);

        recyclerView.setLayoutManager(new
                GridLayoutManager(getContext(), gridColumnCount));

        return v;

    }

    private void parseJSON() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading");
        progressDialog.show();

        String url = "https://jsonplaceholder.typicode.com/users";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        MyModel model = new MyModel();
                        model.setName(object.getString("name"));
                        model.setUsername(object.getString("username"));
                        model.setEmail(object.getString("email"));

                        list.add(model);



                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }

                adapter=new LandScapeAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();



            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Log.d("tag","onErrorResponse"+error.getMessage());

            }
        });
        requestQueue.add(jsonArrayRequest);


    }
    @NonNull
    @Override
    public String toString() {

        return "ProtraitFragment";
    }

}