package com.anselmo.chemoparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anselmo.chemoparse.ui.HomeActivity;
import com.anselmo.chemoparse.ui.LoginActivity;
import com.parse.ParseUser;

/**
 * Created by Anselmo Hernandez on 11/3/15.
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if( currentUser != null ) {
            redirectTo(HomeActivity.class);
        } else {
            redirectTo(LoginActivity.class);
        }
    }
    
    private void redirectTo(Class<?> ActivityClass) {
        Intent intent = new Intent(this, ActivityClass);
        startActivity(intent);
        finish();
    }
}