package com.hujie.mygankio.javabean;

/**
 * Created by hujie on 2017/1/16.
 */

public class BaseHeaderReslut<T> {

    private boolean error;
    private T results;

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
