package com.example.kasunchinthaka.rasmika.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kasunchinthaka.rasmika.R;

import org.json.JSONObject;

public class SaveExtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_ext);

    }


    public void connectDB(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mail-client.000webhostapp.com/user.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SaveExtActivity.this, response.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("output", response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("output", error.toString());

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void receiveData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mail-client.000webhostapp.com/new.php";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SaveExtActivity.this, response.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("output", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
// Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    public void connectDB(View view) {
        connectDB();
    }

    public void receiveJSON(View view) {
        receiveData();
    }

    public void addToDB(View view) {
        addData();
    }

    public void addData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://mail-client.000webhostapp.com/add.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SaveExtActivity.this, response.toString(),Toast.LENGTH_SHORT).show();
                Log.d("output", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("output", error.toString());

            }
        });

        queue.add(stringRequest);
    }
}
