package com.dhruvampanchal.a10pointer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profilemenuaddsubject)
        {
            //TODO:Add a Subject to User Profile
        }

        if (id==R.id.profilemenuremovesubject)
        {
            //TODO:Remove a Subject from a User Profile
        }

        return super.onOptionsItemSelected(item);
    }
}
