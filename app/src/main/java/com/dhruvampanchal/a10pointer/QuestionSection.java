package com.dhruvampanchal.a10pointer;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionSection extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RelativeLayout[] myDataset;
    private DatabaseReference myRef;
    private ArrayList<String> mUsername = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_section);

        //TODO:Add info into DataSet.

        myRef = FirebaseDatabase.getInstance().getReference().child(subjects).child(physics);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                mUsername.add(value);
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
        })


        mRecyclerView = (RecyclerView) findViewById(R.id.QuestionsView);

        //Setting fixed size of Recycler View
        mRecyclerView.setHasFixedSize(true);

        //use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify an Adapter
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    public RelativeLayout RelativeLayoutProducer(String Question, String Name){

        RelativeLayout TempLayout = (RelativeLayout) findViewById(R.id.RecyclerComponent);
        TextView QuestionText = (TextView) findViewById(R.id.QuestionText);
        TextView NameText = (TextView) findViewById(R.id.NameText);

        QuestionText.setText(Question);
        NameText.setText(Name);
    }
}
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private RelativeLayout[] mDataset;

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout mTextView;

            public MyViewHolder(RelativeLayout v) {
                super(v);
                mTextView = v;
            }
        }

        public MyAdapter(RelativeLayout[] myDataset) {
            mDataset = myDataset;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            //create a new view
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclercomponent, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView = mDataset[position];
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
