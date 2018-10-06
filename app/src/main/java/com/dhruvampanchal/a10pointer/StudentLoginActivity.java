package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.support.v4.app.FragmentActivity;

import org.w3c.dom.Text;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSignIn;

    private static final String TAG = "StudentLoginActivity";
    private FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //TODO: Redirect to profile using Intent
        if (currentUser != null)
        {
            Intent StudentProfile = new Intent(this, ProfileActivity.class);
            StudentProfile.putExtra("Student Name", currentUser.getDisplayName());
            startActivity(StudentProfile);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        mAuth = FirebaseAuth.getInstance();


        //Checking Email format validity in if statement.


        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmailAddress = (EditText) findViewById(R.id.useremail);
                mPassword = (EditText) findViewById(R.id.userpassword);
                mSignIn = (Button) findViewById(R.id.signinbtn);

                String strEmailAddress = mEmailAddress.toString();
                String strPassword = mPassword.toString();

                mAuth.signInWithEmailAndPassword(strEmailAddress, strPassword)
                        .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //TODO: Add Intent to Profile Page
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(StudentLoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                        });
            }
        });


    }

}
