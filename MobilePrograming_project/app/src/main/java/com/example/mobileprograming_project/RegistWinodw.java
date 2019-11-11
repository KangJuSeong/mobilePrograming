package com.example.mobileprograming_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RegistWinodw extends AppCompatActivity {
    EditText name;
    EditText date;
    EditText link;
    EditText size;
    EditText remark;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_item);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        link = findViewById(R.id.link);
        size = findViewById(R.id.size);
        remark = findViewById(R.id.remark);
        btn = findViewById(R.id.btn);

        btn.setClickable(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistWinodw.this,MainActivity.class);
                String str_name=name.getText().toString();
                String str_date=date.getText().toString();
                String str_size=size.getText().toString();
                String str_link=link.getText().toString();
                String str_remark=remark.getText().toString();
                intent.putExtra("NAME",str_name);
                intent.putExtra("DATE",str_date);
                intent.putExtra("SIZE",str_size);
                intent.putExtra("LINK",str_link);
                intent.putExtra("REMARK",str_remark);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}
