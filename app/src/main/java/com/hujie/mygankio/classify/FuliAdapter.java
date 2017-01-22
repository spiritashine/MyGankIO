package com.hujie.mygankio.classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hujie.mygankio.R;

import java.util.ArrayList;

/**
 * Created by hujie on 2017/1/18.
 */

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ResultsBean> mData;
    LayoutInflater inflater;

    public FuliAdapter(Context context, ArrayList<ResultsBean> mData) {
        this.context = context;
        this.mData = mData;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fuli, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Glide.with(context).load(mData.get(position).getUrl()).into(holder.fuliImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView fuliImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            fuliImage= (ImageView) itemView.findViewById(R.id.fuli_image);
        }
    }

}
