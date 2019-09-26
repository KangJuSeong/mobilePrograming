package com.example.test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

class Student{
    String id;
    String name;
    String age;
    String gender;

    Student(String id,String name,String age,String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
};

public class RecyclerViewMainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Student> myDataset=new ArrayList<>();
    public int itemSize=0;
    String v_id,v_name,v_age,v_gender;
    private Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);
        addBtn = findViewById(R.id.btn);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addBtn.setClickable(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewMainActivity.this , addItem.class);
                startActivityForResult(intent,0); //첫 인자는 인텐트 두번쨰 인자는 요청코드 번호
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    v_id = data.getStringExtra("ID");
                    v_name = data.getStringExtra("NAME");
                    v_age = data.getStringExtra("AGE");
                    v_gender = data.getStringExtra("GENDER");
                    myDataset.add(new Student(v_id,v_name,v_age,v_gender));
                    mAdapter = new MyAdapter(myDataset);
                    recyclerView.setAdapter(mAdapter);
                    break;
            }
        }
    };

}
