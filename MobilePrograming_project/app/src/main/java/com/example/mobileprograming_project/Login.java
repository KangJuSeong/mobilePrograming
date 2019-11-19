package com.example.mobileprograming_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText id;
    EditText pw;
    Button login;
    Button signup;
    firebase db = new firebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        id=findViewById(R.id.login_id);
        pw=findViewById(R.id.login_pw);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID=id.getText().toString();
                String PW=pw.getText().toString();
                boolean flag=db.dbCheckUser(ID,PW);
                if(flag==true){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("userID",ID);
                    startActivity(intent);
                }
                else{Toast.makeText(getApplicationContext(),"ID 또는 PW가 틀립니다.", Toast.LENGTH_SHORT);}
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}