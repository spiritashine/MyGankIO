package com.hujie.mygankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hujie.mygankio.R;
import com.hujie.mygankio.base.TimeUtils;
import com.hujie.mygankio.javabean.ResultsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hujie on 2017/1/13.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<ResultsBean> data;
    LayoutInflater inflater;
    private static final int NO_IMG=0;
    private static final int WITH_IMG=1;

    //接口回调 view
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    public MyRecyclerViewAdapter(Context context, ArrayList<ResultsBean> data) {
        this.context = context;
        this.data = data;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        //根据ViewType填充不同的布局
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
            ((NoImgViewHolder) holder).who_no_img.setText("var "+bean.getWho());
            //获取显示的时间
            String createdAt = bean.getCreatedAt();
            String time = TimeUtils.getTime(createdAt);
            ((NoImgViewHolder) holder).createdAt_no_img.setText(time);
        }else if (holder instanceof ImgViewHolder){
            ((ImgViewHolder) holder).desc_img.setText(bean.getDesc());
            ((ImgViewHolder) holder).who_img.setText("var "+bean.getWho());
            //获取显示的时间
            String createdAt = bean.getCreatedAt();
            String time = TimeUtils.getTime(createdAt);
            ((ImgViewHolder) holder).createdAt_img.setText(time);
            //Glide下载图片
//            Glide.with(context).load(bean.getImages().get(0)).into(((ImgViewHolder) holder).img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据images是否为空设置ViewType
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


    /**
     * 由于两种不同的ViewType所以有两种ViewHolder
     * 给两个ViewHolder分别添加监听
     */
    class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
           itemView.setOnClickListener(this);
       }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onItemClick(v);
            }
        }
    }

    class NoImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView desc_no_img;
        TextView who_no_img;
        TextView createdAt_no_img;

        public NoImgViewHolder(View itemView) {
            super(itemView);
            desc_no_img= (TextView) itemView.findViewById(R.id.desc_noimg);
            who_no_img= (TextView) itemView.findViewById(R.id.who_noimg);
            createdAt_no_img= (TextView) itemView.findViewById(R.id.createdAt_noimg);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onItemClick(v);
            }
        }
    }

}
