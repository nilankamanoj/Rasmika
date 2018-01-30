package com.example.kasunchinthaka.rasmika.activity;

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

import com.example.kasunchinthaka.rasmika.R;
import com.example.kasunchinthaka.rasmika.db.LastLastDataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    EditText editTextFName, editTextUserEmail, editTextPassword,editTextLName,editTextTelePhone,editTextDOB;
    Button btnCreateAccount;
    Context context = this;
    private LastLastDataSource mDataSource;    //have to understand
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDataSource = new LastLastDataSource(this);    //have to understand
        mDataSource.open();

        addListenerOnSpinnerItemSelection();
        addListenerOnSpinnerItemSelection2();
        //have to understand
        editTextFName = findViewById(R.id.editTextFName);
        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnCreateAccount = findViewById(R.id.buttonCreateAccount);
        editTextLName =findViewById(R.id.editTextLName);
        editTextTelePhone = findViewById(R.id.editTextTelePhone);
        editTextDOB=findViewById(R.id.editTextDOB);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                String FName = editTextFName.getText().toString();

                String Lname = editTextLName.getText().toString();
                String DOB = editTextDOB.getText().toString();
                String teleNo=editTextTelePhone.getText().toString();

                String gender = String.valueOf(spinner2.getSelectedItem());

                String password = editTextPassword.getText().toString();
                String userEmail = editTextUserEmail.getText().toString();
                String position = String.valueOf(spinner1.getSelectedItem());
                if (FName.equals("") || password.equals("") || userEmail.equals("")|| Lname.equals("") || teleNo.equals("") || DOB.equals("dd-mm-yyyy")) {

                    Toast.makeText(getApplicationContext(), "Field Vacant",
                            Toast.LENGTH_LONG).show();

                    if(FName.equals(""))editTextFName.setError("Field vacant!");
                    if(password.equals(""))editTextPassword.setError("Field vacant!");
                    if(userEmail.equals(""))editTextUserEmail.setError("Field vacant!");
                    if(Lname.equals("") )editTextLName.setError("Field vacant!");
                    if(teleNo.equals("") )editTextTelePhone.setError("Field vacant!");
                    if(DOB.equals("dd-mm-yyyy"))editTextDOB.setError("Field vacant!");


                }
                else if(!isThisDateValid(DOB,"dd-mm-yyyy")){
                    editTextDOB.setError("invalid date!");
                    editTextDOB.setText("dd-mm-yyyy");
                }
                else if(!teleNo.matches("\\d*") || teleNo.length()!=10){
                   editTextTelePhone.setText("");
                   editTextTelePhone.setError("Invalid phone");
                }
                else if (isEmailValid(userEmail)) {
/**
                    mDataSource.insertEntry(FName, password, userEmail, position);   //  have to understand
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    finish(); // add finish to finish current activity
                    Intent i = new Intent(RegisterActivity.this,
                            LoginActivity.class);
                    startActivity(i);
*/

                } else {
//                        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    editTextUserEmail.setText("");
                    editTextUserEmail.setError("Invalid Email Address");
                    Toast.makeText(RegisterActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    public boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
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
    public void addListenerOnSpinnerItemSelection2() {
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
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
