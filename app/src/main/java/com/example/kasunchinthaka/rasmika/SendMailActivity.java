package com.example.kasunchinthaka.rasmika;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SendMailActivity extends Activity {

    Button buttonSendEmail, buttonReceiveEmail, buttonFroward;
    TextView textTo;
    TextView textSubject;
    TextView textMessage;
    String item, mail_body;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        Bundle bundle = getIntent().getExtras();
        String position = bundle.getString("position");
        TextView textView = findViewById(R.id.positiontxt);
        textView.setText("You are logged in as a " + position);


        buttonSendEmail = findViewById(R.id.buttonSendEmail);
        buttonReceiveEmail = findViewById(R.id.buttonReceiveEmail);
        // buttonFroward = findViewById(R.id.buttonFroward);
//        textTo = (TextView) findViewById(R.id.editTextTo);
//        textSubject = (TextView) findViewById(R.id.editTextSubject);
//        textMessage = (TextView) findViewById(R.id.editTextMessage);


//        textTo.setText("kasunchinthaka555@gmail.com");
//        textSubject.setText("YUMMY ONLIE ORDERING");
//        final String body = "CUSTOMER: "+Patient.username +"\n"+ "Order: "+Patient.MSGbody+"\n"+"DELIVERY COST: "+Patient.deliverycost+"\n"+"CUSTOMER CONTACT NUMBER: "+Patient.Contact_Number;
//        textMessage.setText(body);

        buttonSendEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "your_email"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    //TODO smth
                }


            }
        });
        buttonReceiveEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                startActivity(mailClient);


//                String to = "kasunchinthaka555@gmail.com";
//                String subject = "YUMMY ONLIE ORDERING";
//                String message = "Heei How are you!";
//
//                Intent email = new Intent(Intent.ACTION_SEND);
//                //  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
//                //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
//                //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
//                //   email.putExtra(Intent.EXTRA_SUBJECT, subject);
//             //   email.putExtra(Intent.EXTRA_TEXT, message);
////
//                //need this to prompts email client only
//                email.setType("message/rfc822");
//                email.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                startActivity(Intent.createChooser(email, "Choose an Email client :"));


            }
        });
//        buttonFroward.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                try{
//                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "your_email"));
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "your_subject");
//                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
//                    startActivity(intent);
//                }catch(ActivityNotFoundException e){
//                    //TODO smth
//                }
//            }
//        });
    }

    public void forward(View view) {
        Button button = findViewById(R.id.buttonForward);
        button.setVisibility(View.GONE);
        Button button1 = findViewById(R.id.buttonReceiveEmail);
        button1.setVisibility(View.VISIBLE);
    }

    public void saveExternal(View view) {
        Intent intent = new Intent(this, SaveExtActivity.class);
        startActivity(intent);
    }

}