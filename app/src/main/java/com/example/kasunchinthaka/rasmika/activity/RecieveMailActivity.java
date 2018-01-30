package com.example.kasunchinthaka.rasmika.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kasunchinthaka.rasmika.R;
import com.example.kasunchinthaka.rasmika.util.Constraints;
import org.json.JSONException;
import org.json.JSONObject;

public class RecieveMailActivity extends AppCompatActivity {
    Context context =this;
    public int c=1;



    public JSONObject jsonObject;

    public JSONObject getJsonObjectIN() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject){
        this.jsonObject =jsonObject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_mail);
        Bundle bundle = getIntent().getExtras();
        final String email = bundle.getString("email");

        String url= Constraints.recieveUrl;
        System.out.println(url);
        final JSONObject jObject=new JSONObject();
        try {

            jObject.put("email",email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, url, jObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        setJsonObject(jsonObject);

                        LinearLayout detailListView = (LinearLayout) findViewById(R.id.linearLayoutRecieve);
                        while(true) {

                            try {
                                System.out.println(jsonObject.getJSONObject(String.valueOf(c)).getString("from"));

                                final TextView textView = new TextView(context);

                                textView.setText(String.valueOf(c)+"-"+jsonObject.getJSONObject(String.valueOf(c)).getString("from")+" :"+jsonObject.getJSONObject(String.valueOf(c)).getString("subject"));
                                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                                        ,ViewGroup.LayoutParams.WRAP_CONTENT));
                                textView.setHeight(100);
                                textView.setTextSize(20);
                                ShapeDrawable sd = new ShapeDrawable();
                                sd.setShape(new RectShape());
                                sd.setIntrinsicWidth(10000);
                                sd.getPaint().setColor(Color.GRAY);
                                sd.getPaint().setStrokeWidth(10f);
                                sd.getPaint().setStyle(Paint.Style.STROKE);
                                textView.setBackground(sd);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        System.out.println("clicked on" +String.valueOf(textView.getText()));
                                        String txt= (textView.getText().toString());
                                        String ref =String.valueOf(txt.charAt(0));

                                        try {
                                            String from = getJsonObjectIN().getJSONObject(String.valueOf(ref)).getString("from");
                                            String subject = getJsonObjectIN().getJSONObject(String.valueOf(ref)).getString("subject");
                                            String description= getJsonObjectIN().getJSONObject(String.valueOf(ref)).getString("description");
                                            String project_name = getJsonObjectIN().getJSONObject(String.valueOf(ref)).getString("project_name");

                                            Intent intent = new Intent(context, ViewActivity.class);
                                            intent.putExtra("email",email);
                                            intent.putExtra("from",from);
                                            intent.putExtra("subject",subject);
                                            intent.putExtra("description",description);
                                            intent.putExtra("project_name",project_name);
                                            startActivity(intent);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });
                                detailListView.addView(textView);

                                c++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                break;
                            }
                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println(volleyError);
                        Toast.makeText(getApplicationContext(), "check internet connectivity",
                                Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jobReq);


    }
}
