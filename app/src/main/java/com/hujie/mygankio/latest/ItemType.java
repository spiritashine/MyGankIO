package com.hujie.mygankio.latest;

/**
 * 首页大标题下的布局，分为四种
 * Created by hujie on 2017/1/20.
 */

public class ItemType {
    public static final int TYPE_TITILE=0;
    public static final int TYPE_IMAGE=1;
    public static final int TYPE_SUBTITLE=2;
    public static final int TYPE_CONTENT=3;

    private int type;
    private Object data;

    public ItemType(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
