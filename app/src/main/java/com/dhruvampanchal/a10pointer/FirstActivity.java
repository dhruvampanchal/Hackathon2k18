package com.dhruvampanchal.a10pointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button StudentLogin;
    private Button TeacherLogin;
    private Button SignUpLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //TODO: Add Intent for Student, Teacher and SignUp.

        StudentLogin = (Button) findViewById(R.id.buttonstudent);
        TeacherLogin = (Button) findViewById(R.id.buttonteacher);
        SignUpLogin = (Button) findViewById(R.id.buttonsignup);

        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StudentIntent = new Intent(FirstActivity.this, StudentLoginActivity.class);
                startActivity(StudentIntent);
            }
        });

        TeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TeacherIntent = new Intent(FirstActivity.this, TeacherLoginActivity.class);
                startActivity(TeacherIntent);
            }
        });
    }
}
