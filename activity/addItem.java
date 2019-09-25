package com.example.test2;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addItem extends AppCompatActivity {
    EditText value;
    Button btn2;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        value = findViewById(R.id.value);
        btn2 = findViewById(R.id.btn2);

        btn2.setClickable(true);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addItem.this,RecyclerViewMainActivity.class);
                str=value.getText().toString();
                intent.putExtra("key",str);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
