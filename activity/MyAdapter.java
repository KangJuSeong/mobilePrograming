package com.example.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.*;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Student> mDataset = new ArrayList<>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView print_id;
        public TextView print_name;
        public TextView print_age;
        public TextView print_gender;
        // 배열에 있는 요소의 갯수만큼 반복
        public MyViewHolder(View v) {
            super(v);
            print_id = v.findViewById(R.id.print_id);
            print_name = v.findViewById(R.id.print_name);
            print_age = v.findViewById(R.id.print_age);
            print_gender = v.findViewById(R.id.print_gender);
        }
    }

    // 데이터를 받아온다 배열로
    public MyAdapter(ArrayList<Student> myDataset) {
        mDataset=myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // 반복한 mDataset의 position 인덱스의 요소를 보여준다.
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.print_id.setText(mDataset.get(position).id);
        holder.print_name.setText(mDataset.get(position).name);
        holder.print_age.setText(mDataset.get(position).age);
        holder.print_gender.setText(mDataset.get(position).gender);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return mDataset.size();}

}