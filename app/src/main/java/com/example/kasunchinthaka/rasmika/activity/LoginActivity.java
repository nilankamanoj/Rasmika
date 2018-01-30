package com.example.kasunchinthaka.rasmika.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
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


public class LoginActivity extends AppCompatActivity {
    Context context = this;
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addListenerOnSpinnerItemSelection();
    }

    public void login(View view) {

        final EditText editTextUserEmail = findViewById(R.id.editTextUserEmailToLogin);
        final EditText editTextPassword = findViewById(R.id.editTextPasswordToLogin);

        final String userEmail = editTextUserEmail.getText().toString(); // String userName
        String password = editTextPassword.getText().toString();
        final String position = String.valueOf(spinner1.getSelectedItem());

        if (userEmail.equals("") || password.equals("")) {


            if(userEmail.equals("") )editTextUserEmail.setError("Field required!");
            if(password.equals(""))editTextPassword.setError("Field required!");

        }
        else if(position.equals("Select your position")){
            Toast.makeText(getApplicationContext(), "Select Position",
                    Toast.LENGTH_LONG).show();

        }

        else if(isEmailValid(userEmail)){
            System.out.println(userEmail+","+password+","+position);
            String url= Constraints.loginUrl;
            System.out.println(url);
            JSONObject jObject=new JSONObject();
            try {

                jObject.put("email",userEmail);
                jObject.put("account_type",position);
                jObject.put("password",password);
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
                                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                                    finish();
                                    Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                    main.putExtra("email", userEmail);
                                    main.putExtra("position", position);
                                    startActivity(main);

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),
                                            "Invalid Credentials!", Toast.LENGTH_LONG)
                                            .show();

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
        else {
            editTextUserEmail.setError("invalid email!");
        }
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

    public void createAccount(View view) {
        finish();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // super.onBackPressed();
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
