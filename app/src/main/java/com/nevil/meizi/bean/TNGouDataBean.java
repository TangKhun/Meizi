package com.nevil.meizi.bean;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouDataBean {
    int count;
    int fcount;
    int galleryclass;
    int id;
    String img;
    int rcount;
    int size;
    long time;
    String title;

    public TNGouDataBean() {
    }

    public TNGouDataBean(int count, int fcount, int galleryclass, int id, String img, int rcount, int size, long time, String title) {
        this.count = count;
        this.fcount = fcount;
        this.galleryclass = galleryclass;
        this.id = id;
        this.img = img;
        this.rcount = rcount;
        this.size = size;
        this.time = time;
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public TNGouDataBean setCount(int count) {
        this.count = count;
        return this;
    }

    public int getFcount() {
        return fcount;
    }

    public TNGouDataBean setFcount(int fcount) {
        this.fcount = fcount;
        return this;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public TNGouDataBean setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
        return this;
    }

    public int getId() {
        return id;
    }

    public TNGouDataBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getImg() {
        return img;
    }

    public TNGouDataBean setImg(String img) {
        this.img = img;
        return this;
    }

    public int getRcount() {
        return rcount;
    }

    public TNGouDataBean setRcount(int rcount) {
        this.rcount = rcount;
        return this;
    }

    public int getSize() {
        return size;
    }

    public TNGouDataBean setSize(int size) {
        this.size = size;
        return this;
    }

    public long getTime() {
        return time;
    }

    public TNGouDataBean setTime(long time) {
        this.time = time;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TNGouDataBean setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "TNGouDataBean{" +
                "count=" + count +
                ", fcount=" + fcount +
                ", galleryclass=" + galleryclass +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", rcount=" + rcount +
                ", size=" + size +
                ", time=" + time +
                ", title='" + title + '\'' +
                '}';
    }
}
