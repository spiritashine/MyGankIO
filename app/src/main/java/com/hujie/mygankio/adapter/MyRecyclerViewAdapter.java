package com.hujie.mygankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hujie.mygankio.R;
import com.hujie.mygankio.ResultsBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            ((NoImgViewHolder) holder).who_no_img.setText("var "+bean.getWho());
            String createdAt = bean.getCreatedAt();
            Log.i("=============", "onBindViewHolder: "+createdAt);
            String time = getTime(createdAt);
            ((NoImgViewHolder) holder).createdAt_no_img.setText(time);
        }else if (holder instanceof ImgViewHolder){
            ((ImgViewHolder) holder).desc_img.setText(bean.getDesc());
            ((ImgViewHolder) holder).who_img.setText("var "+bean.getWho());
            String createdAt = bean.getCreatedAt();
            Log.i("=============", "onBindViewHolder: "+createdAt);
            String time = getTime(createdAt);
            ((ImgViewHolder) holder).createdAt_img.setText(time);
//            Glide.with(context).load(bean.getImages().get(0)).into(((ImgViewHolder) holder).img);
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

    public String getTime(String createdAt){
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//获取年份
        int month=ca.get(Calendar.MONTH)+1;//获取月份
        int day=ca.get(Calendar.DAY_OF_MONTH);//获取日
        int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
        int minute=ca.get(Calendar.MINUTE);//分

        int yearNow=Integer.parseInt(createdAt.substring(0,4));
        int monthNow=Integer.parseInt(createdAt.substring(5,7));
        int dayNow=Integer.parseInt(createdAt.substring(8,10));
        int hourNow=Integer.parseInt(createdAt.substring(11,13));
        int minNow=Integer.parseInt(createdAt.substring(14,16));

        if (month!=monthNow || year!=yearNow){
            return yearNow+"-"+monthNow+"-"+dayNow;
        }else if (month==monthNow && year==yearNow){
            if (day - dayNow > 10) {
                return yearNow + "-" + monthNow + "-" + dayNow;
            } else if (day - dayNow <=10 && day - dayNow > 2) {
                return day - dayNow+"天前";
            } else if (day - dayNow == 2) {
                return "前天";
            } else if (day - dayNow == 1) {
                return "昨天";
            } else if (day == dayNow) {
                return hour + ":" + minute;
            }
        }
        return null;
    }

}
