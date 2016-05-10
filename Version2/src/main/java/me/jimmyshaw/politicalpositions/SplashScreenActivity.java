package me.jimmyshaw.politicalpositions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = IssueListActivity.newIntent(this, "none");
        startActivity(intent);
        finish();
    }
}
