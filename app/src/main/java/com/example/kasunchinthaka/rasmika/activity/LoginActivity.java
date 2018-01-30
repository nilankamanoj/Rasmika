package com.example.kasunchinthaka.rasmika.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.kasunchinthaka.rasmika.R;



public class LoginActivity extends AppCompatActivity {
    private Spinner spinner1;
    Button btnSignIn, btnSignUp;

   // private LastLastDataSource mDataSource;   //have to understand

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addListenerOnSpinnerItemSelection();
    }

    public void login(View view) {
       // mDataSource = new LastLastDataSource(this);   //have to understand
       // mDataSource.open();                             //have to understand

        final EditText editTextUserEmail = findViewById(R.id.editTextUserEmailToLogin);
        final EditText editTextPassword = findViewById(R.id.editTextPasswordToLogin);

        String userEmail = editTextUserEmail.getText().toString(); // String userName
        String password = editTextPassword.getText().toString();
        String position = String.valueOf(spinner1.getSelectedItem());

        //String storedPassword = mDataSource.getSingleEntry(userEmail);   //have to understand

        //String storedPosition = mDataSource.getSingleEntryy(password);   //have to understand

        if (userEmail.equals("") || password.equals("")) {

            editTextUserEmail.setText("");
            editTextUserEmail.setError("Field required!");
            editTextPassword.setText("");
            editTextPassword.setError("Field required!");

        } else {
            System.out.println(userEmail+","+password+","+position);
            /**
            if () {

                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    finish();
                    Intent main = new Intent(LoginActivity.this, SendMailActivity.class);
                    main.putExtra("position", storedPosition);
                    startActivity(main);
                }
            } else {
                Toast.makeText(LoginActivity.this,
                        "Email or Password does not match",
                        Toast.LENGTH_LONG).show();
            }
             */
        }
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
