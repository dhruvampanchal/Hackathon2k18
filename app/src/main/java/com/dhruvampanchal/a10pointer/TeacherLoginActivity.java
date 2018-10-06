package com.dhruvampanchal.a10pointer;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPassword;
    private Button mSignIn;

    private static final String TAG = "TeacherLoginActivity";
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!=null)
        {
            //TODO: Redirect to profile using Intent
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        mAuth = FirebaseAuth.getInstance();



        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mEmailAddress = (EditText) findViewById(R.id.useremail);
                mPassword = (EditText) findViewById(R.id.userpassword);
                mSignIn = (Button) findViewById(R.id.signinbtn);
                //Checking Email format validity in if statement.

                String strEmailAddress = mEmailAddress.toString();
                String strPassword = mPassword.toString();

                mAuth.signInWithEmailAndPassword(strEmailAddress, strPassword)
                        .addOnCompleteListener(TeacherLoginActivity.this, new OnCompleteListener<AuthResult>() {
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
                                    Toast.makeText(TeacherLoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }
}
