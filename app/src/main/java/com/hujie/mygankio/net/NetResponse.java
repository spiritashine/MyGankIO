package com.hujie.mygankio.net;

/**
 * Created by hujie on 2017/1/16.
 */

public interface NetResponse {
    void onResponse(Object data);
    void onError(String msg);
}
