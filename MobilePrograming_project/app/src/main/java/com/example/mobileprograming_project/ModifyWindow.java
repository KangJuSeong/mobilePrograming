package com.example.mobileprograming_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyWindow extends AppCompatActivity {
    EditText name;
    EditText date;
    EditText link;
    EditText size;
    EditText remark;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_item);

    }
};