package com.example.kasunchinthaka.rasmika.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendMailActivity extends AppCompatActivity {
    private EditText editTextSender,editTextReciever,editTextSubject,editTextDescription,editTextProjectName;
    private Button buttonSend;
    private Context context =this;
    private Boolean inHome = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        Bundle bundle = getIntent().getExtras();
        String reciever = bundle.getString("to");
        String sender= bundle.getString("from");
        String subject = bundle.getString("subject");
        String projName = bundle.getString("projName");
        final String position =bundle.getString("position");

        editTextSender= findViewById(R.id.editTextSender);
        editTextReciever=findViewById(R.id.editTextReciever);
        editTextSubject=findViewById(R.id.editTextSubject);
        editTextDescription=findViewById(R.id.editTextDescription);
        editTextProjectName=findViewById(R.id.editTextProjectName);
        buttonSend = findViewById(R.id.buttonSend);

        if(sender!=null)editTextSender.setText(sender);
        if(reciever!=null){
            editTextReciever.setText(reciever);

        }
        if(subject!=null)editTextSubject.setText(subject);
        if(projName!=null)editTextProjectName.setText(projName);

        buttonSend.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String to = editTextReciever.getText().toString();
                        String from = editTextSender.getText().toString();
                        String subject = editTextSubject.getText().toString();
                        String description = editTextDescription.getText().toString();
                        String project_name = editTextProjectName.getText().toString();

                        if (to.equals("") || from.equals("") || subject.equals("") || description.equals("")) {

                            Toast.makeText(getApplicationContext(), "Field Vacant",
                                    Toast.LENGTH_LONG).show();
                            if (to.equals("")) editTextReciever.setError("Field vacant");
                            if (from.equals("")) editTextSender.setError("Field vacant");
                            if (subject.equals("")) editTextSubject.setError("Field vacant");
                            if (description.equals(""))
                                editTextDescription.setError("Field vacant");


                        }
                        else if (!isEmailValid(to)){
                            editTextReciever.setError("invalid email");
                        }
                        else if(!isEmailValid(from)){
                            editTextSender.setError("invalid email");
                        }

                        else{

                            String url= Constraints.sendEmailUrl;
                            System.out.println(url);
                            JSONObject jObject=new JSONObject();
                            try {
                                jObject.put("account_type",position);
                                jObject.put("to",to);
                                jObject.put("from",from);
                                jObject.put("subject",subject);
                                jObject.put("description",description);
                                jObject.put("project_name",project_name);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            RequestQueue queue = Volley.newRequestQueue(context);
                            JsonObjectRequest jobReq = new JsonObjectRequest(Request.Method.POST, url, jObject,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            try {
                                                String success=jsonObject.getString("success");
                                                String message=jsonObject.getString("message");

                                                if(success.equals("valid")){
                                                    Toast.makeText(getApplicationContext(), "Successfully sent",
                                                            Toast.LENGTH_LONG).show();
                                                    finish();

                                                }
                                                else{
                                                    Toast.makeText(getApplicationContext(), "error occurred, try later! ",
                                                            Toast.LENGTH_LONG).show();
                                                    finish();

                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
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
                }

        );





    }
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }



}
