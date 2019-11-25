package com.example.mobileprograming_project;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class firebase {
    public boolean check;
    public FirebaseDatabase mDatabase;
    public DatabaseReference mReference;
    public String userID;
    public FirebaseAuth mUser;
    public firebase() {
        mDatabase = FirebaseDatabase.getInstance();
        mUser = FirebaseAuth.getInstance();
    }

    public void dbWrite(Item item,String cnt,String userID) {
        HashMap<String, String> result = new HashMap<>();
        mReference=mDatabase.getReference();
        result.put("NAME", item.NAME);
        result.put("DATE", item.DATE);
        result.put("SIZE", item.SIZE);
        result.put("LINK", item.LINK);
        result.put("REMARK", item.REMARK);
        mReference.child("ITEMS").child(userID).child("Item"+cnt).setValue(result);
    }
    public void dbDelete(String userID) {
        mReference=mDatabase.getReference();
        mReference.child("ITEMS").child(userID).setValue(null);
    }
}
