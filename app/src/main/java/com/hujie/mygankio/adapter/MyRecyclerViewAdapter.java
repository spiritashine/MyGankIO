package com.hujie.mygankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hujie.mygankio.R;
import com.hujie.mygankio.ResultsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ResultsBean> data;
    LayoutInflater inflater;
    private static final int NO_IMG=0;
    private static final int WITH_IMG=1;

    public MyRecyclerViewAdapter(Context context, ArrayList<ResultsBean> data) {
        this.context = context;
        this.data = data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==MyRecyclerViewAdapter.NO_IMG){
            view=inflater.inflate(R.layout.item_no_img,parent,false);
            return new NoImgViewHolder(view);
        }else {
            view=inflater.inflate(R.layout.item_with_img,parent,false);
            return new ImgViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResultsBean bean = data.get(position);
        if (holder instanceof NoImgViewHolder){
            ((NoImgViewHolder) holder).desc_no_img.setText(bean.getDesc());
            ((NoImgViewHolder) holder).who_no_img.setText(bean.getWho());
            ((NoImgViewHolder) holder).createdAt_no_img.setText(bean.getCreatedAt());
        }else if (holder instanceof ImgViewHolder){
            ((ImgViewHolder) holder).desc_img.setText(bean.getDesc());
            ((ImgViewHolder) holder).who_img.setText(bean.getWho());
            ((ImgViewHolder) holder).createdAt_img.setText(bean.getCreatedAt());
            Glide.with(context).load(bean.getImages().get(0)).into(((ImgViewHolder) holder).img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        List<String> images = data.get(position).getImages();
        if (images==null){
            return MyRecyclerViewAdapter.NO_IMG;
        }else {
            return MyRecyclerViewAdapter.WITH_IMG;
        }
    }

    @Override
    public int getItemCount() {
        return data.size() !=0 ? data.size() : 0;
    }

    class ImgViewHolder extends RecyclerView.ViewHolder{
        TextView desc_img;
        TextView who_img;
        TextView createdAt_img;
        ImageView img;

       public ImgViewHolder(View itemView) {
           super(itemView);
           desc_img= (TextView) itemView.findViewById(R.id.desc_img);
           who_img= (TextView) itemView.findViewById(R.id.who_img);
           createdAt_img= (TextView) itemView.findViewById(R.id.createdAt_img);
           img= (ImageView) itemView.findViewById(R.id.img);
       }
   }

    class NoImgViewHolder extends RecyclerView.ViewHolder{
        TextView desc_no_img;
        TextView who_no_img;
        TextView createdAt_no_img;

        public NoImgViewHolder(View itemView) {
            super(itemView);
            desc_no_img= (TextView) itemView.findViewById(R.id.desc_noimg);
            who_no_img= (TextView) itemView.findViewById(R.id.who_noimg);
            createdAt_no_img= (TextView) itemView.findViewById(R.id.createdAt_noimg);
        }
    }

}
