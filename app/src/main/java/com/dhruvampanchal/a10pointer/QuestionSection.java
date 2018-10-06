package com.dhruvampanchal.a10pointer;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuestionSection extends AppCompatActivity {


    private ArrayList<String> myDataset = new ArrayList<String>();
    private DatabaseReference myRef;
    private ListView QuestionsView;
    private String Question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_section);

        //TODO:Add info into DataSet.

        myRef = FirebaseDatabase.getInstance().getReference().child("subjects").child("physics");

        myDataset.clear();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int x = 0;
                Toast.makeText(QuestionSection.this, dataSnapshot.getKey().toString(), Toast.LENGTH_SHORT).show();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Toast.makeText(QuestionSection.this, "Inside Child!", Toast.LENGTH_SHORT).show();
                    Log.i("CHILD ACTIVITY", child.getKey().toString());

                    //Adding to myDataset
                    myDataset.add(x,child.getValue().toString());
                    Log.i("CHILD Value", child.getValue().toString());
                    Toast.makeText(QuestionSection.this, "Question Added!", Toast.LENGTH_SHORT).show();
                    Log.i("myDataSet",myDataset.get(x).toString());
                    x=x+1;
                }
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


        QuestionsView = (ListView) findViewById(R.id.QList);

        CustomAdapter customAdapter = new CustomAdapter();
        QuestionsView.setAdapter(customAdapter);



    }

    class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return myDataset.size();
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
            convertView = getLayoutInflater().inflate(R.layout.listitem, null);

            TextView Questions = (TextView) convertView.findViewById(R.id.TVText);
            Questions.setText(myDataset.get(position));

            return convertView;
        }
    }

}

