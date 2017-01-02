package com.example.akil.recyclerswipable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by akil on 1/2/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements ItemTouchHelperAdapter{

    Context context;
    List<String> listItem;
    ViewHolder viewHolder;
    View view;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public Adapter(Context context, List<String> listItem) {
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(listItem.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You touched " + listItem.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    @Override
    public void onItemMove(int fromPostion, int toPosition) {
        if(fromPostion<toPosition){
            for(int i = fromPostion; i < toPosition; i++){
                Collections.swap(listItem, i, i+1);
            }
        }else {
            for(int i = fromPostion; i > toPosition; i--){
                Collections.swap(listItem, i, i-1);
            }
        }
        notifyItemMoved(fromPostion, toPosition);
    }

    @Override
    public void onItemDimiss(int Position) {
        listItem.remove(Position);
        notifyItemRemoved(Position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txtItem);
        }
    }

    public void removeItem(int postion){
        listItem.remove(postion);
        notifyItemRemoved(postion);
        notifyDataSetChanged();
    }

    public void addItem(String item){
        listItem.add(item);
        notifyDataSetChanged();
    }

}
