package com.neurobreach.classroomorganizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    public ArrayList<NoticeItem> myValues;
    Context ctx;

    public NoticeAdapter (Context ctx, ArrayList<NoticeItem> myValues){
        this.myValues= myValues;
        this.ctx=ctx;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notice, parent, false);
        return new MyViewHolder(listItem);    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.cardName.setText(myValues.get(position).getT());
        holder.cardDesc.setText(myValues.get(position).getDesc());



    }

    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cardName,cardDesc;
        public MyViewHolder(View itemView) {
            super(itemView);

            cardName=itemView.findViewById(R.id.title_notice);
            cardDesc=itemView.findViewById(R.id.desc_notice);




        }
    }
}
