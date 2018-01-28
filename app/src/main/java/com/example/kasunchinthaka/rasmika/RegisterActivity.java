package com.example.kasunchinthaka.rasmika;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kasunchinthaka.rasmika.db.LastLastDataSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    EditText editTextUserName, editTextUserEmail, editTextPassword;
    Button btnCreateAccount;
    Context context = this;
    private LastLastDataSource mDataSource;    //have to understand
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDataSource = new LastLastDataSource(this);    //have to understand
        mDataSource.open();

        addListenerOnSpinnerItemSelection();
        //have to understand
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnCreateAccount = findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String userEmail = editTextUserEmail.getText().toString();
                String position = String.valueOf(spinner1.getSelectedItem());


                if (userName.equals("") || password.equals("") || userEmail.equals("") || position.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vacant",
                            Toast.LENGTH_LONG).show();

                    editTextUserName.setText("");
                    editTextUserName.setError("Field vacant!");
                    editTextPassword.setText("");
                    editTextPassword.setError("Field vacant!");
                    editTextUserEmail.setText("");
                    editTextUserEmail.setError("Field vacant!");

                } else if (isEmailValid(userEmail)) {

                    mDataSource.insertEntry(userName, password, userEmail, position);   //  have to understand
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    finish(); // add finish to finish current activity
                    Intent i = new Intent(RegisterActivity.this,
                            LoginActivity.class);
                    startActivity(i);


                } else {
//                        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    editTextUserEmail.setText("");
                    editTextUserEmail.setError("Invalid Email Address");
                    Toast.makeText(RegisterActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();


                }
            }
        });
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

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void signup(View view) {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        mDataSource.close();
        super.onPause();
    }

    public void onBackPressed() {

        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}

class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        Toast.makeText(parent.getContext(),
//                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
