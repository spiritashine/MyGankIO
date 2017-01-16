package com.hujie.mygankio.javabean;

/**
 * Created by hujie on 2017/1/16.
 */

public class BaseBean<T> {
    private boolean error;
    private T result;
    private String msg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
