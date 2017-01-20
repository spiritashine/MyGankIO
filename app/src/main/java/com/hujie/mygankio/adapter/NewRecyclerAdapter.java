package com.hujie.mygankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
                View view = inflater.inflate(R.layout.item_new_title, parent, false);
                return new TitleHolder(view);
            case ItemType.TYPE_IMAGE:

                break;
            case ItemType.TYPE_SUBTITLE:

                break;
            case ItemType.TYPE_CONTENT:

                break;
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

        public ImageHolder(View itemView) {
            super(itemView);
            imageNew= (ImageView) itemView.findViewById(R.id.image_new);
        }

        @Override
        public void fill(ItemType type) {
            NewItemBean bean = (NewItemBean) type.getData();
        }
    }


    public static abstract class NewViewHolder extends RecyclerView.ViewHolder {

        public NewViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void fill(ItemType type);

    }
}
