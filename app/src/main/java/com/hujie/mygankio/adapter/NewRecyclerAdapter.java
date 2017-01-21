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
import com.hujie.mygankio.javabean.NewItemBean;
import com.hujie.mygankio.latest.ItemType;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by hujie on 2017/1/20.
 */

public class NewRecyclerAdapter extends RecyclerView.Adapter<NewRecyclerAdapter.NewViewHolder> {

    private Context context;
    private ArrayList<ItemType> data;
    private final LayoutInflater inflater;

    public NewRecyclerAdapter(Context context, ArrayList<ItemType> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemType.TYPE_TITILE:
                View title = inflater.inflate(R.layout.item_new_title, parent, false);
                return new TitleHolder(title);
            case ItemType.TYPE_IMAGE:
                View image = inflater.inflate(R.layout.item_new_image, parent, false);
                return new ImageHolder(context,image);
            case ItemType.TYPE_SUBTITLE:
                View subTitle = inflater.inflate(R.layout.item_new_subtitle, parent, false);
                return new SubTitleHolder(subTitle);
            case ItemType.TYPE_CONTENT:

        }

        return null;
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public static class TitleHolder extends NewViewHolder {
        TextView titleNew;

        public TitleHolder(View itemView) {
            super(itemView);
            titleNew= (TextView) itemView.findViewById(R.id.title_new);
        }

        @Override
        public void fill(ItemType type) {
            titleNew.setText((String)type.getData());
        }
    }

    public static class ImageHolder extends NewViewHolder {
        ImageView imageNew;
        private Context context;

        public ImageHolder(Context context,View itemView) {
            super(itemView);
            this.context=context;
            imageNew= (ImageView) itemView.findViewById(R.id.image_new);
        }

        @Override
        public void fill(ItemType type) {
            NewItemBean bean = (NewItemBean) type.getData();
            String url = bean.getUrl();
            Glide.with(context).load(url).into(imageNew);
        }
    }

    public static class SubTitleHolder extends NewViewHolder {
        TextView subTitleNew;

        public SubTitleHolder(View itemView) {
            super(itemView);
            subTitleNew= (TextView) itemView.findViewById(R.id.subtitle_new);
        }

        @Override
        public void fill(ItemType type) {
            subTitleNew.setText((String)type.getData());
        }
    }

    public static class ContentHolder extends NewViewHolder {
        TextView contentNew;

        public ContentHolder(View itemView) {
            super(itemView);
            contentNew= (TextView) itemView.findViewById(R.id.content_new);
        }

        @Override
        public void fill(ItemType type) {
            contentNew.setText((String)type.getData());
        }
    }


    public static abstract class NewViewHolder extends RecyclerView.ViewHolder {

        public NewViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void fill(ItemType type);

    }
}
