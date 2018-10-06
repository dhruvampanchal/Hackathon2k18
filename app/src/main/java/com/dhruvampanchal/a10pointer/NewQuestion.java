package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewQuestion extends AppCompatActivity {

    EditText mQuestion;
    EditText mDescription;
    Button mPublish;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question)
        mQuestion = (EditText) findViewById(R.id.QuestionQuery);
        mDescription = (EditText) findViewById(R.id.DescriptionQuery);
        mPublish = (Button) findViewById(R.id.PublishButton);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        Intent intent=getIntent();
        String courseName=intent.getExtras().toString();
        ref.child("subjects").child(courseName).
    }
}
