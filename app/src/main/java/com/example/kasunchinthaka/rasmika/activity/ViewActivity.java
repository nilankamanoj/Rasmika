package com.example.kasunchinthaka.rasmika.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kasunchinthaka.rasmika.R;

public class ViewActivity extends AppCompatActivity {
    private TextView textViewFrom,textViewSubject,textViewProjName,textViewDesc;
    private Button buttonReply;
    public String to,from,subject,project_name;
    public Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        textViewFrom = findViewById(R.id.textViewFrom);
        textViewSubject=findViewById(R.id.textViewSubject);
        textViewProjName = findViewById(R.id.textViewProjName);
        textViewDesc = findViewById(R.id.textViewDesc);
        buttonReply =findViewById(R.id.buttonReply);

        Bundle bundle = getIntent().getExtras();
        to = bundle.getString("email");
        from= bundle.getString("from");
        subject = bundle.getString("subject");
        project_name = bundle.getString("project_name");
        String description = bundle.getString("description");

        textViewFrom.setText("From :"+from);
        textViewSubject.setText("Subject :"+subject);
        textViewProjName.setText("Project :"+project_name);
        textViewDesc.setText(description);

        buttonReply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("send mail");
                Intent intent = new Intent(context, SendMailActivity.class);
                intent.putExtra("from",to);
                intent.putExtra("to",from);
                intent.putExtra("subject",subject);
                intent.putExtra("projName",project_name);
                startActivity(intent);
            }
        });
    }
}
