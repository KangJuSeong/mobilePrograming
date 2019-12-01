package com.example.mobileprograming_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Animation fab_open, fab_close, rotate_open, rotate_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, addBtn, logout;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Item> myDataset=new ArrayList<>();
    firebase db=new firebase();

    @Override
    protected void onRestart() {
        super.onRestart();
        try{
            db.mReference=db.mDatabase.getReference("ITEMS/" + db.userID+"/");
            db.mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    myDataset.clear();
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        String name=data.child("NAME").getValue().toString();
                        String size=data.child("SIZE").getValue().toString();
                        String date=data.child("DATE").getValue().toString();
                        String link=data.child("LINK").getValue().toString();
                        String remark=data.child("REMARK").getValue().toString();
                        Item item = new Item(name,date,size,link,remark);
                        myDataset.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        catch (Exception e){}
    }
    @Override
    protected void onResume() {
        super.onResume();
        try{
            db.mReference=db.mDatabase.getReference("ITEMS/" + db.userID+"/");
            db.mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    myDataset.clear();
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        String name=data.child("NAME").getValue().toString();
                        String size=data.child("SIZE").getValue().toString();
                        String date=data.child("DATE").getValue().toString();
                        String link=data.child("LINK").getValue().toString();
                        String remark=data.child("REMARK").getValue().toString();
                        Item item = new Item(name,date,size,link,remark);
                        myDataset.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        catch (Exception e){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemlist_view);

        Intent userInfo = getIntent();
        db.userID = userInfo.getExtras().getString("userID");

        Toolbar tb = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        addBtn = findViewById(R.id.addBtn);
        logout = findViewById(R.id.logout);

        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        tb.setTitle(R.string.myAppName);
        tb.setBackgroundColor(Color.rgb(255, 111, 97));
        setSupportActionBar(tb);

        rotate_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_open);
        rotate_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab.setOnClickListener(new FABClickListener());
        addBtn.setOnClickListener(new FABClickListener());
        logout.setOnClickListener(new FABClickListener());
        //처음 화면이 켜지면 데이터를 한번 불러옴
        try{
            db.mReference=db.mDatabase.getReference("ITEMS/" + db.userID+"/");
            db.mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    myDataset.clear();
                    for(DataSnapshot data : dataSnapshot.getChildren()){
                        String name=data.child("NAME").getValue().toString();
                        String size=data.child("SIZE").getValue().toString();
                        String date=data.child("DATE").getValue().toString();
                        String link=data.child("LINK").getValue().toString();
                        String remark=data.child("REMARK").getValue().toString();
                        Item item = new Item(name,date,size,link,remark);
                        myDataset.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
        catch (Exception e){}

        mAdapter = new MyAdapter(this,myDataset,db.userID);
        recyclerView.setAdapter(mAdapter);
    }
    //fab버튼을 통해 각각의 화면으로 이동함
    class FABClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.fab:
                    anim();
                    break;
                case R.id.addBtn:
                    anim();
                    Intent intent2 = new Intent(MainActivity.this, RegistWindow.class);
                    intent2.putExtra("userID",db.userID);
                    startActivityForResult(intent2, 0);
                    break;
                case R.id.logout:
                    anim();
                    Intent intent3 = new Intent(MainActivity.this,Login.class);
                    startActivity(intent3);
                    break;
            }
        }
    }
    public void anim() {
        if (isFabOpen) {
            fab.startAnimation(rotate_close);
            addBtn.startAnimation(fab_close);
            logout.startAnimation(fab_close);
            addBtn.setClickable(false);
            logout.setClickable(false);
            isFabOpen = false;
        } else {
            fab.startAnimation(rotate_open);
            addBtn.startAnimation(fab_open);
            logout.startAnimation(fab_open);
            addBtn.setClickable(true);
            logout.setClickable(true);
            isFabOpen = true;
        }
    }
    //다른 액티비티로부터 가져온 데이터를 파이어베이스에 쓰는 역할을 함
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
                    db.userID=data.getStringExtra("userID");
                    Item item1=new Item(name,date,size,link,remark);
                    myDataset.add(item1);
                    int i=myDataset.size()-1;
                    String postion=Integer.toString(i);
                    Item temp=new Item(myDataset.get(i).NAME,myDataset.get(i).DATE,myDataset.get(i).SIZE,myDataset.get(i).LINK,myDataset.get(i).REMARK);
                    db.dbWrite(temp,postion,db.userID);
                    mAdapter = new MyAdapter(this,myDataset,db.userID);
                    recyclerView.setAdapter(mAdapter);
                    break;
                case 1:
                    db=new firebase();
                    name = data.getStringExtra("NAME");
                    size = data.getStringExtra("SIZE");
                    date = data.getStringExtra("DATE");
                    link = data.getStringExtra("LINK");
                    remark = data.getStringExtra("REMARK");
                    String position=data.getStringExtra("POSITION");
                    db.userID=data.getStringExtra("userID");
                    Item item=new Item(name,date,size,link,remark);
                    i=Integer.parseInt(position);
                    myDataset.set(i,item);
                    temp = new Item(myDataset.get(i).NAME,myDataset.get(i).DATE,myDataset.get(i).SIZE,myDataset.get(i).LINK,myDataset.get(i).REMARK);
                    db.dbWrite(temp,position,db.userID);
                    mAdapter = new MyAdapter(this,myDataset,db.userID);
                    recyclerView.setAdapter(mAdapter);
                    break;
            }
        }
        else{
            mAdapter = new MyAdapter(this,myDataset,db.userID);
            recyclerView.setAdapter(mAdapter);
        }
    }
}

