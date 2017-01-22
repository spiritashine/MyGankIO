package com.hujie.mygankio.classify;

import java.util.List;

/**
 * Created by hujie on 2017/1/14.
 */

public class ResultsBean {


    /**
     * _id : 587724fd421aa93161103dfa
     * createdAt : 2017-01-12T14:41:01.560Z
     * desc : [译]Android Application启动流程分析
     * images : ["http://img.gank.io/be9571a9-99d7-4537-a291-0c772ec2f08f"]
     * publishedAt : 2017-01-13T11:58:16.991Z
     * source : chrome
     * type : Android
     * url : http://www.jianshu.com/p/a5532ecc8377
     * used : true
     * who : lxxself
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
