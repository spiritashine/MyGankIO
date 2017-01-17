package com.hujie.mygankio.javabean;

/**
 * Created by hujie on 2017/1/16.
 */

public class BaseReslut<T> {

    private boolean error;
    private T results;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
