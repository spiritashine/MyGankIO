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
            NoImgViewHolder noImgViewHolder = new NoImgViewHolder(view);
            return noImgViewHolder;
        }else {
            view=inflater.inflate(R.layout.item_with_img,parent,false);
            ImgViewHolder imgViewHolder = new ImgViewHolder(view);
            return imgViewHolder;
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
            String time = getTime(createdAt);
            ((NoImgViewHolder) holder).createdAt_no_img.setText(time);
        }else if (holder instanceof ImgViewHolder){
            ((ImgViewHolder) holder).desc_img.setText(bean.getDesc());
            ((ImgViewHolder) holder).who_img.setText("var "+bean.getWho());
            //获取显示的时间
            String createdAt = bean.getCreatedAt();
            String time = getTime(createdAt);
            ((ImgViewHolder) holder).createdAt_img.setText(time);
            //Glide下载图片
            Glide.with(context).load(bean.getImages().get(0)).into(((ImgViewHolder) holder).img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //根据images是否唯恐设置ViewType
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


    /**
     * 对比 “createdAt” 的时间和现在的时间
     * 1.由于要显示 “几天前”，所以要转化为int类型
     * 2.分别对比月份，年，天，返回不同的时间显示格式。
     * @param createdAt
     * @return
     */
    public String getTime(String createdAt){
        Calendar ca = Calendar.getInstance();
        int yearNow = ca.get(Calendar.YEAR);//获取年份
        int monthNow=ca.get(Calendar.MONTH)+1;//获取月份
        int dayNow=ca.get(Calendar.DAY_OF_MONTH);//获取日
        int hourNow=ca.get(Calendar.HOUR_OF_DAY);//小时
        int minuteNow=ca.get(Calendar.MINUTE);//分

        int year=Integer.parseInt(createdAt.substring(0,4));
        int month=Integer.parseInt(createdAt.substring(5,7));
        int day=Integer.parseInt(createdAt.substring(8,10));
        int hour=Integer.parseInt(createdAt.substring(11,13));
        int minute=Integer.parseInt(createdAt.substring(14,16));

        if (month!=monthNow || year!=yearNow){
            return yearNow+"-"+monthNow+"-"+dayNow;
        }else if (month==monthNow && year==yearNow){
            if (dayNow - day > 10) {
                return yearNow + "-" + monthNow + "-" + dayNow;
            } else if (dayNow - day <=10 && dayNow - day > 2) {
                return dayNow - day+"天前";
            } else if (dayNow - day == 2) {
                return "前天";
            } else if (dayNow - day == 1) {
                return "昨天";
            } else if (day == dayNow) {
                return hour + ":" + minute;
            }
        }
        return null;
    }

}
