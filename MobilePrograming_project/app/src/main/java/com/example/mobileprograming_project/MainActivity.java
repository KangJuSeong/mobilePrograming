package com.example.mobileprograming_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

class Item {
    String size;
    String name;
    String date;
    String link;
    String remark;
    Item(String name,String date,String size) {
        this.name=name;
        this.date=date;
        this.size=size;
    }
};

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Item> myDataset=new ArrayList<>();
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_view);
        addBtn=findViewById(R.id.addBtn);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addBtn.setClickable(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistWinodw.class);
                startActivityForResult(intent,0); //첫 인자는 인텐트 두번쨰 인자는 요청코드 번호
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    String size = data.getStringExtra("SIZE");
                    String name = data.getStringExtra("NAME");
                    String date = data.getStringExtra("DATE");
                    Item i = new Item(name,date,size);
                    myDataset.add(i);
                    mAdapter = new MyAdapter(myDataset);
                    recyclerView.setAdapter(mAdapter);
                    break;
            }
        }
    };
}

