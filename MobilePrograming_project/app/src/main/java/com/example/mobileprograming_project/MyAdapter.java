package com.example.mobileprograming_project;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Item> mDataset = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener  {
        public TextView i_name;
        public TextView i_size;
        public TextView i_date;
        public MyViewHolder(View v) {
            super(v);
            i_name=v.findViewById(R.id.i_name);
            i_size=v.findViewById(R.id.i_size);
            i_date=v.findViewById(R.id.i_date);
            v.setOnCreateContextMenuListener(this);
        }
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "상세정보");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1001:
//                        (() item).startActivityForResult(new Intent("com.android.settings.xxxxx"), 0);

                        break;
                    case 1002:
                        mDataset.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mDataset.size());
                        break;
                }
                return true;
            }
        };

    };

    // 데이터를 받아온다 배열로
    public MyAdapter(ArrayList<Item> myDataset) {
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
        holder.i_date.setText(mDataset.get(position).date);
        holder.i_name.setText(mDataset.get(position).name);
        holder.i_size.setText(mDataset.get(position).size);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return mDataset.size();}

}