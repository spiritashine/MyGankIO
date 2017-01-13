package com.hujie.mygankio;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hujie on 2017/1/13.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> data;
    LayoutInflater inflater;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size()!=0?data.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

       public MyViewHolder(View itemView) {
           super(itemView);
           textView= (TextView) itemView.findViewById(android.R.id.text1);
       }
   }

}
