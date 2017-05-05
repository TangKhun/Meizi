package com.nevil.meizi.bean;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouImageBean {
    int count;
    int fcount;
    int galleryclass;
    int id;
    String img;
    List<TNGouImageDataBean> list;

    public TNGouImageBean() {
    }

    public TNGouImageBean(int count, int fcount, int galleryclass, int id, String img, List<TNGouImageDataBean> list) {
        this.count = count;
        this.fcount = fcount;
        this.galleryclass = galleryclass;
        this.id = id;
        this.img = img;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public TNGouImageBean setCount(int count) {
        this.count = count;
        return this;
    }

    public int getFcount() {
        return fcount;
    }

    public TNGouImageBean setFcount(int fcount) {
        this.fcount = fcount;
        return this;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public TNGouImageBean setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
        return this;
    }

    public int getId() {
        return id;
    }

    public TNGouImageBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getImg() {
        return img;
    }

    public TNGouImageBean setImg(String img) {
        this.img = img;
        return this;
    }

    public List<TNGouImageDataBean> getList() {
        return list;
    }

    public TNGouImageBean setList(List<TNGouImageDataBean> list) {
        this.list = list;
        return this;
    }

    @Override
    public String toString() {
        return "TNGouImageBean{" +
                "count=" + count +
                ", fcount=" + fcount +
                ", galleryclass=" + galleryclass +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", list=" + list +
                '}';
    }
}
