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

public class SignUpActivity extends AppCompatActivity {

    private EditText SignUpEmail;
    private EditText SignUpName;
    private EditText SignUpPassword;
    private FirebaseAuth mAuth;
    private Button mSignUpButton;

    private static final String TAG = "StudentLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUpEmail = (EditText) findViewById(R.id.useremailsignup);
                SignUpName = (EditText) findViewById(R.id.usernamesignup);
                SignUpPassword = (EditText) findViewById(R.id.userpasswordsignup);
                mSignUpButton = (Button) findViewById(R.id.signupbtn);

                String strSignUpEmail = SignUpEmail.toString();
                String strSignUpPassword = SignUpPassword.toString();

                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(strSignUpEmail, strSignUpPassword)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //TODO: Use Intent to go to profile page
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }


}
