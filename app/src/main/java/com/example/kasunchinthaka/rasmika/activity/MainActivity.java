package com.example.kasunchinthaka.rasmika.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kasunchinthaka.rasmika.R;

public class MainActivity extends Activity {

    Button buttonSendEmail, buttonCheck, buttonLogout;
    Context context =this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        Bundle bundle = getIntent().getExtras();
        final String position = bundle.getString("position");
        final String email = bundle.getString("email");
        TextView textView = findViewById(R.id.positiontxt);
        textView.setText("You are logged in as a " + position);


        buttonSendEmail = findViewById(R.id.buttonSendEmail);
        buttonCheck = findViewById(R.id.buttonCheck);
        buttonLogout = findViewById(R.id.buttonLogout);


        buttonSendEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("send mail");
                Intent intent = new Intent(context, SendMailActivity.class);
                intent.putExtra("from",email);
                intent.putExtra("position",position);
                startActivity(intent);


            }
        });

        buttonCheck.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("check mail");
                Intent intent = new Intent(context, RecieveMailActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("position",position);
                startActivity(intent);


            }
        });

        buttonLogout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("logout");
                Toast.makeText(context, "logout Successfull", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);


            }
        });


    }
    public void onBackPressed() {



    }

}