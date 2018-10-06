package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    Intent intent = getIntent();
    String subject = intent.getStringExtra("courseName");

    private String[] myDataset = new String[10];
    private ArrayList<String> ids = new ArrayList<String>();
    private DatabaseReference myRef;
    private ListView QuestionsView;
    private String Question;
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_section);

        //TODO:Add info into DataSet.
        QuestionsView = (ListView) findViewById(R.id.QList);

        final CustomAdapter customAdapter = new CustomAdapter();

        myRef = FirebaseDatabase.getInstance().getReference().child("subjects").child(subject);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                ids.add(value);

                Toast.makeText(QuestionSection.this, dataSnapshot.getKey().toString(), Toast.LENGTH_SHORT).show();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Toast.makeText(QuestionSection.this, "Inside Child!", Toast.LENGTH_SHORT).show();
                    Log.i("CHILD ACTIVITY", child.getKey().toString());
                    if (child.getKey().toString() == "Question")
                    {
                        //Adding to myDataset
                        myDataset[x] = child.getValue().toString();
                        Log.i("CHILD Value", child.getValue().toString());
                        Toast.makeText(QuestionSection.this, "Question Added!", Toast.LENGTH_SHORT).show();
                        Log.i("myDataSet",myDataset[x].toString());
                        x=x+1;
                    }

                }
                QuestionsView.setAdapter(customAdapter);
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

        QuestionsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(QuestionSection.this, QuestionDiscussion.class);
                String idsend = ids.get(position);
                intent.putExtra("id", idsend);
                intent.putExtra("subject", subject);
                startActivity(intent);

            }
        });
    }



    public class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return myDataset.length;
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
            Questions.setText(myDataset[position]);

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.question_section_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Search:
                return true;
        }
        return true;
    }
}

