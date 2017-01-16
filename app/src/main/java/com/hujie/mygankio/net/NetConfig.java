package com.hujie.mygankio.net;

/**
 * Created by hujie on 2017/1/16.
 */

public interface NetConfig {
    void onResponse(Object data);
    void onError(String msg);
}
