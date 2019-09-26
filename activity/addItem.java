package com.example.test2;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class addItem extends AppCompatActivity {
    Button btn;
    EditText id,name,age,gender;
    String str_id,str_name,str_age,str_gender;
    private DatabaseReference mPostReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        id = findViewById(R.id.Id);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        btn = findViewById(R.id.btn);


        btn.setClickable(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addItem.this,RecyclerViewMainActivity.class);
                str_id=id.getText().toString();
                str_name=name.getText().toString();
                str_age=age.getText().toString();
                str_gender=gender.getText().toString();
                intent.putExtra("ID",str_id);
                intent.putExtra("NAME",str_name);
                intent.putExtra("AGE",str_age);
                intent.putExtra("GENDER",str_gender);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
