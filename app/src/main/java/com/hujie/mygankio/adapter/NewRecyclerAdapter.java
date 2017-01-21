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

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<ItemType> data;

    public NewRecyclerAdapter(Context context, ArrayList<ItemType> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemType.TYPE_TITILE:
                View title = inflater.inflate(R.layout.item_new_title, parent, false);
                return new TitleHolder(title);
            case ItemType.TYPE_IMAGE:
                View image = inflater.inflate(R.layout.item_new_image, parent, false);
                return new ImageHolder(context, image);
            case ItemType.TYPE_SUBTITLE:
                View subTitle = inflater.inflate(R.layout.item_new_image, parent, false);
                return new SubTitleHolder(subTitle);
            case ItemType.TYPE_CONTENT:
                View content = inflater.inflate(R.layout.item_new_content, parent, false);
                return new ContentHolder(content);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {
        holder.fill(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


    public static class ImageHolder extends NewViewHolder {

        private ImageView imageView;
        private Context context;

        public ImageHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            imageView = (ImageView) itemView.findViewById(R.id.image_new);
        }

        @Override
        public void fill(ItemType data) {
            NewItemBean bean = (NewItemBean) data.getData();
            String url = bean.getUrl();
            Glide.with(context).load(url).into(imageView);
        }
    }

    public static class ContentHolder extends NewViewHolder {
        private TextView content;

        public ContentHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content_new);
        }

        @Override
        public void fill(ItemType data) {
            NewItemBean bean = (NewItemBean) data.getData();
            content.setText(bean.getDesc());
        }
    }

    public static class SubTitleHolder extends NewViewHolder {
        private TextView subtitle;

        public SubTitleHolder(View itemView) {
            super(itemView);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle_new);
        }

        @Override
        public void fill(ItemType data) {
            subtitle.setText((String) data.getData());
        }
    }

    public static class TitleHolder extends NewViewHolder {
        private TextView title;

        public TitleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_new);
        }

        @Override
        public void fill(ItemType data) {
            title.setText((String) data.getData());
        }
    }


    public static abstract class NewViewHolder extends RecyclerView.ViewHolder {
        public NewViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void fill(ItemType data);
    }
}
