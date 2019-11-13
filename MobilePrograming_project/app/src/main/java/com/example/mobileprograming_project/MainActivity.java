package com.example.mobileprograming_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

class firebase{
    public DatabaseReference mDatabase;
    public firebase(){ mDatabase = FirebaseDatabase.getInstance().getReference(); }
    public void dbWrite(String userID,String name,String date,String size,String link,String remark){
        HashMap<String, String> result = new HashMap<>();
        result.put("DATE", date);
        result.put("SIZE", size);
        result.put("LINK", link);
        result.put("REMARK", remark);
        mDatabase.child("USERS").child(userID).child(name).setValue(result);
    }
    public void dbDelete(String name){ mDatabase.child("USERS").child("user1").child(name).setValue(null);}
}

class Item {
    String size;
    String name;
    String date;
    String link;
    String remark;
    Item(String name,String date,String size,String link,String remark) {
        this.name=name;
        this.date=date;
        this.size=size;
        this.link=link;
        this.remark=remark;
    }
};

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button addBtn;
    ArrayList<Item> myDataset=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_view);
        recyclerView = findViewById(R.id.my_recycler_view);
        addBtn = findViewById(R.id.addBtn);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addBtn.setClickable(true);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistWindow.class);
                startActivityForResult(intent, 0); //첫 인자는 인텐트 두번쨰 인자는 요청코드 번호
            }
        });
//        FloatingActionButton fab = findViewById(R.id.addBtn);
//        fab.setOnClickListener(new FABClickListener());
//
//    }
//    class FABClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            Intent intent2 = new Intent(MainActivity.this, RegistWindow.class);
//            startActivity(intent2);
//        }
//    }
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
                    String link = data.getStringExtra("LINK");
                    String remark = data.getStringExtra("REMARK");
                    Item i = new Item(name,date,size,link,remark);
                    myDataset.add(i);
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
                    break;
                case 1:
                    size = data.getStringExtra("SIZE");
                    name = data.getStringExtra("NAME");
                    date = data.getStringExtra("DATE");
                    link = data.getStringExtra("LINK");
                    remark = data.getStringExtra("REMARK");
                    String pos=data.getStringExtra("POSITION");
                    int position=Integer.parseInt(pos);
                    i = new Item(name,date,size,link,remark);
                    myDataset.set(position,i);
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
            }
        }
    };
}

