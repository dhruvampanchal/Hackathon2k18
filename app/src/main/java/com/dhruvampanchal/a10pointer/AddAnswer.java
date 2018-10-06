package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddAnswer extends AppCompatActivity {

    EditText AnswerPlace;
    Button SubmitAnswer;
    DatabaseReference ref;
    ArrayList<String> InputArray;
    String questionID;
    String questionSubject;
    ArrayList<String> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);

        AnswerPlace = (EditText) findViewById(R.id.answerWriter);
        SubmitAnswer = (Button) findViewById(R.id.answerSubmitButton);
        Intent intent=getIntent();
        questionID=intent.getStringExtra("id");
        questionSubject = intent.getStringExtra("subject");

        ref = FirebaseDatabase.getInstance().getReference().child("subjects").child(questionSubject).child(questionID).child("Answers");



        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                temp = dataSnapshot.getValue(ArrayList.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("getValue", "Failed to read value.", error.toException());
            }
        });
        temp.add(AnswerPlace.getText().toString());
        ref.setValue(temp);

    }
}
