package com.example.yang.smallfavor;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = new Intent(MenuActivity.this, Login_Activity.class);

        startActivity(intent);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
