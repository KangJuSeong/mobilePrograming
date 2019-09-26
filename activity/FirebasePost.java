package com.example.test2;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebasePost {
    public String id;
    public String name;
    public Long age;
    public String gender;

    public FirebasePost(){}

    public FirebasePost(String id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("age", age);
        result.put("gender", gender);
        return result;
    }

}
