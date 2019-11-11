package com.example.mobileprograming_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyWindow extends AppCompatActivity {
    EditText r_name;
    TextView r_date;
    EditText r_link;
    EditText r_size;
    EditText r_remark;
    Button r_btn;
    String position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_item);
        r_name=findViewById(R.id.r_name);
        r_date=findViewById(R.id.r_date);
        r_link=findViewById(R.id.r_link);
        r_size=findViewById(R.id.r_size);
        r_remark=findViewById(R.id.r_remark);
        r_btn=findViewById(R.id.r_btn);

        Intent intent = getIntent();
        r_name.setText(intent.getStringExtra("NAME"));
        r_date.setText(intent.getStringExtra("DATE"));
        r_link.setText(intent.getStringExtra("LINK"));
        r_size.setText(intent.getStringExtra("SIZE"));
        r_remark.setText(intent.getStringExtra("REMARK"));
        position=intent.getStringExtra("POSITION");

        r_btn.setClickable(true);
        r_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyWindow.this,MainActivity.class);
                String str_name=r_name.getText().toString();
                String str_date=r_date.getText().toString();
                String str_size=r_size.getText().toString();
                String str_link=r_link.getText().toString();
                String str_remark=r_remark.getText().toString();
                intent.putExtra("NAME",str_name);
                intent.putExtra("DATE",str_date);
                intent.putExtra("SIZE",str_size);
                intent.putExtra("LINK",str_link);
                intent.putExtra("REMARK",str_remark);
                intent.putExtra("POSITION",position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
};