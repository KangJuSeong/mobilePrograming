package com.example.mobileprograming_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

class firebase {
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    DatabaseReference mReference2;

    public firebase() {
        mDatabase = FirebaseDatabase.getInstance();
    }
    public void dbWrite(String userID, String name, String date, String size, String link, String remark,String cnt) {
        HashMap<String, String> result = new HashMap<>();
        mReference=mDatabase.getReference();
        result.put("NAME", name);
        result.put("DATE", date);
        result.put("SIZE", size);
        result.put("LINK", link);
        result.put("REMARK", remark);
        mReference.child("USERS").child(userID).child("Item"+cnt).setValue(result);
    }
    public void dbDelete() {
        mReference=mDatabase.getReference();
        mReference.child("USERS").child("user1").setValue(null);
    }

    public void dbRead(final Item item) {
        mReference = mDatabase.getReference("USERS/user1/item0");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String a=data.getValue().toString();
                    item.name=a;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
class Item {
    String size;
    String name;
    String date;
    String link;
    String remark;
    Item(){

    }
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
    TextView view;
    Button addBtn;
    ArrayList<Item> myDataset=new ArrayList<>();
    firebase db=new firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_view);
        db.dbDelete();
        Item i = new Item();
        db.dbRead(i);
        view=findViewById(R.id.view);
        recyclerView = findViewById(R.id.my_recycler_view);
        addBtn = findViewById(R.id.addBtn);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        view.setText(i.name);

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
                    firebase db=new firebase();
                    String name = data.getStringExtra("NAME");
                    String size = data.getStringExtra("SIZE");
                    String date = data.getStringExtra("DATE");
                    String link = data.getStringExtra("LINK");
                    String remark = data.getStringExtra("REMARK");
                    Item item1 = new Item(name,date,size,link,remark);
                    myDataset.add(item1);
                    db.dbDelete();
                    for(int i=0;i<myDataset.size();i++){
                        String postion=Integer.toString(i);
                        db.dbWrite("user1",myDataset.get(i).name,myDataset.get(i).date,myDataset.get(i).size,myDataset.get(i).link,myDataset.get(i).remark,postion);
                    }
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
                    break;
                case 1:
                    db=new firebase();
                    name = data.getStringExtra("NAME");
                    size = data.getStringExtra("SIZE");
                    date = data.getStringExtra("DATE");
                    link = data.getStringExtra("LINK");
                    remark = data.getStringExtra("REMARK");
                    String pos=data.getStringExtra("POSITION");
                    int position=Integer.parseInt(pos);
                    Item item2 = new Item(name,date,size,link,remark);
                    myDataset.set(position,item2);
                    db.dbDelete();
                    for(int i=0;i<myDataset.size();i++){
                        String postion=Integer.toString(i);
                        db.dbWrite("user1",myDataset.get(i).name,myDataset.get(i).date,myDataset.get(i).size,myDataset.get(i).link,myDataset.get(i).remark,postion);
                    }
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
            }
        }
    };
}

