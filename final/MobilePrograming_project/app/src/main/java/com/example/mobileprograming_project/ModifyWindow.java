package com.example.mobileprograming_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ModifyWindow extends AppCompatActivity {
    EditText r_name;
    TextView r_date;
    EditText r_link;
    EditText r_size;
    EditText r_remark;
    Button r_btn;
    Button c_btn;
    String position;
    int mYear, mMonth, mDay;
    Calendar cal = new GregorianCalendar();
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
        c_btn=findViewById(R.id.c_btn);

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        UpdateNow();

        Intent intent = getIntent();
        final String userID=intent.getStringExtra("userID");

        r_name.setText(intent.getStringExtra("NAME"));
        r_date.setText(intent.getStringExtra("DATE"));
        r_link.setText(intent.getStringExtra("LINK"));
        r_size.setText(intent.getStringExtra("SIZE"));
        r_remark.setText(intent.getStringExtra("REMARK"));
        position=intent.getStringExtra("POSITION");

        c_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(ModifyWindow.this,MainActivity.class);
                setResult(0,intent);
                finish();
            }
        });

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
                intent.putExtra("userID",userID);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    UpdateNow();
                }
            };
    public void OnClickbtn(View v){
        switch(v.getId()){
            case R.id.choice_date:
                new DatePickerDialog(ModifyWindow.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
        }

    }
    private void UpdateNow() { r_date.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay)); }
};