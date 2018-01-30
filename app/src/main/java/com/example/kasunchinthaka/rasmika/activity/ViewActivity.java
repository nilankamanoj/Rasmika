package com.example.kasunchinthaka.rasmika.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.kasunchinthaka.rasmika.R;

public class ViewActivity extends AppCompatActivity {
    private TextView textViewFrom,textViewSubject,textViewProjName,textViewDesc;
    private Button buttonReply;

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
        String to = bundle.getString("email");
        String from= bundle.getString("from");
        String subject = bundle.getString("subject");
        String project_name = bundle.getString("project_name");
        String description = bundle.getString("description");

        textViewFrom.setText(from);
        textViewSubject.setText(subject);
        textViewProjName.setText(project_name);
        textViewDesc.setText(description);
    }
}
