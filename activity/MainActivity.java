package com.example.add_activity2;

import android.os.Bundle;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Animation add_open, add_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_open);
        add_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_close);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        add = (FloatingActionButton) findViewById(R.id.add);

        fab.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                //Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                anim();
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void anim() {
        if (isFabOpen) {
            add.startAnimation(add_close);
            add.setClickable(false);
            isFabOpen = false;
        } else {
            add.startAnimation(add_open);
            add.setClickable(true);
            isFabOpen = true;
        }
    }
}

