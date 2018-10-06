package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionDiscussion extends AppCompatActivity {

    TextView questionView = (TextView) findViewById(R.id.questionview);
    TextView descriptionView = (TextView) findViewById(R.id.descriptionview);
    ListView Answers = (ListView) findViewById(R.id.answerview);
    Button AnswerIntent;
    private DatabaseReference myRef;

    Intent intent = getIntent();
    String id = intent.getStringExtra("id");
    String subject = intent.getStringExtra("subject");

    private ArrayList<String> mAnswers = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_discussion);

        AnswerIntent = (Button) findViewById(R.id.AnswerBtn);

        AnswerIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionDiscussion.this, AddAnswer.class);
                intent.putExtra("id", id);
                intent.putExtra("subject",subject);
                startActivity(intent);
            }
        });

        final QuestionDiscussion.CustomAdapter customAdapter = new QuestionDiscussion.CustomAdapter();

        myRef = FirebaseDatabase.getInstance().getReference().child("subjects").child(subject).child(id).child("Answers");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArrayList<String> value = dataSnapshot.getValue(ArrayList.class);
                mAnswers = value;
                Answers.setAdapter(customAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef = FirebaseDatabase.getInstance().getReference().child("subjects").child(subject).child(id).child("Question");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                questionView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        myRef = FirebaseDatabase.getInstance().getReference().child("subjects").child(subject).child(id).child("Description");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                descriptionView.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mAnswers.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.listitemanswers, null);

            TextView Questions = (TextView) convertView.findViewById(R.id.answerTV);
            Questions.setText(mAnswers.get(position));

            return convertView;
        }
    }
}
