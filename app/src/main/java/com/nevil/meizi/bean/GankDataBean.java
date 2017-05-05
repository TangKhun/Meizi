package com.nevil.meizi.bean;

/**
 * Created by Tangkun on 2017/5/3.
 */

public class GankDataBean {
    String _id;
    String createdAt;
    String desc;
    String publishedAt;
    String source;
    String type;
    String url;
    String used;
    String who;

    public GankDataBean() {
    }

    public GankDataBean(String _id, String createdAt, String desc, String publishedAt, String source, String type, String url, String used, String who) {
        this._id = _id;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
    }

    public String get_id() {
        return _id;
    }

    public GankDataBean set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public GankDataBean setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public GankDataBean setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public GankDataBean setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public String getSource() {
        return source;
    }

    public GankDataBean setSource(String source) {
        this.source = source;
        return this;
    }

    public String getType() {
        return type;
    }

    public GankDataBean setType(String type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public GankDataBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsed() {
        return used;
    }

    public GankDataBean setUsed(String used) {
        this.used = used;
        return this;
    }

    public String getWho() {
        return who;
    }

    public GankDataBean setWho(String who) {
        this.who = who;
        return this;
    }

    @Override
    public String toString() {
        return "GankDataBean{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used='" + used + '\'' +
                ", who='" + who + '\'' +
                '}';
    }
}
