package com.example.mobileprograming_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class SignUp extends AppCompatActivity {
    EditText id;
    EditText pw;
    Button correct;
    Button cancle;
    firebase db = new firebase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        correct=findViewById(R.id.correct);
        cancle=findViewById(R.id.cancle);
        id=findViewById(R.id.new_id);
        pw=findViewById(R.id.new_pw);

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });

    }
    //파이어베이스에 회원정보를 등록해주는 함수
    private void registerUser() {
        String email = id.getText().toString().trim();
        String password = pw.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }
        db.mUser.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(SignUp.this, "이미 존재하는 이메일 입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}