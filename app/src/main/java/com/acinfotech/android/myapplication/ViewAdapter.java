package com.acinfotech.android.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by @Shakthi R on 11/6/15.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {


    private LayoutInflater mInflater;

    List<Information> data = Collections.emptyList();


    public ViewAdapter(Context context, List<Information> data) {

        mInflater =LayoutInflater.from(context);
        this.data =data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View viewObject = mInflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(viewObject);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Information  current = data.get(position);
        holder.title.setText(current.mTitle);
        holder.img.setImageResource(current.mItemId);


    }

    @Override
    public int getItemCount() {
        return 0;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.listTitle);
            img = (ImageView) itemView.findViewById(R.id.listImg);

        }
    }
}
