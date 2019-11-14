package com.example.mobileprograming_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    public firebase() {mDatabase = FirebaseDatabase.getInstance();}
    public void dbWrite(Item item,String cnt,String userID) {
        HashMap<String, String> result = new HashMap<>();
        mReference=mDatabase.getReference();
        result.put("NAME", item.name);
        result.put("DATE", item.date);
        result.put("SIZE", item.size);
        result.put("LINK", item.link);
        result.put("REMARK", item.remark);
        mReference.child("USERS").child(userID).child("Item"+cnt).setValue(result);
    }
    public void dbDelete(String userID) {
        mReference=mDatabase.getReference();
        mReference.child("USERS").child(userID).setValue(null);
    }

    public void dbRead(final ArrayList<Item> myDataset,String userID) {
        mReference = mDatabase.getReference("USERS/"+userID+"/");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String name=data.child("NAME").getValue().toString();
                    String size=data.child("SIZE").getValue().toString();
                    String date=data.child("DATE").getValue().toString();
                    String link=data.child("LINK").getValue().toString();
                    String remark=data.child("REMARK").getValue().toString();
                    Item item = new Item(name,date,size,link,remark);
                    myDataset.add(item);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}

class Item {
    String size;
    String name;
    String date;
    String link;
    String remark;
    Item(){}
    Item(String name,String date,String size,String link,String remark) {
        this.name=name;
        this.date=date;
        this.size=size;
        this.link=link;
        this.remark=remark;
    }
    public void setName(String name){this.name=name;}
    public void setLink(String link){this.link=name;}
    public void setDate(String date){this.date=date;}
    public void setSize(String size){this.size=size;}
    public void setRemark(String remark){this.remark=remark;}

}

class myIntent{
    public myIntent(){}
    public Item getData(Intent intent){
        String name = intent.getStringExtra("NAME");
        String size = intent.getStringExtra("SIZE");
        String date = intent.getStringExtra("DATE");
        String link = intent.getStringExtra("LINK");
        String remark = intent.getStringExtra("REMARK");
        Item item=new Item(name,date,size,link,remark);
        return item;
    }
}

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Item> myDataset=new ArrayList<>();
    firebase db=new firebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_view);
//        db.dbDelete();
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new FABClickListener());
        mAdapter = new MyAdapter(this,myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    class FABClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent2 = new Intent(MainActivity.this, RegistWindow.class);
            startActivityForResult(intent2, 0);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    firebase db=new firebase();
                    myIntent mIntent=new myIntent();
                    Item item1 = mIntent.getData(data);
                    myDataset.add(item1);
                    db.dbDelete("user1");
                    for(int i=0;i<myDataset.size();i++){
                        String postion=Integer.toString(i);
                        Item temp=new Item(myDataset.get(i).name,myDataset.get(i).date,myDataset.get(i).size,myDataset.get(i).link,myDataset.get(i).remark);
                        db.dbWrite(temp,postion,"user1");
                    }
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
                    break;
                case 1:
                    db=new firebase();
                    mIntent=new myIntent();
                    Item item2=mIntent.getData(data);
                    String pos=data.getStringExtra("POSITION");
                    int position=Integer.parseInt(pos);
                    myDataset.set(position,item2);
                    db.dbDelete("user1");
                    for(int i=0;i<myDataset.size();i++){
                        String postion=Integer.toString(i);
                        Item temp = new Item(myDataset.get(i).name,myDataset.get(i).date,myDataset.get(i).size,myDataset.get(i).link,myDataset.get(i).remark);
                        db.dbWrite(temp,postion,"user1");
                    }
                    mAdapter = new MyAdapter(this,myDataset);
                    recyclerView.setAdapter(mAdapter);
            }
        }
    }
}

