package com.nevil.meizi.bean;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouImageDataBean {
    int gallery;
    int id;
    String src;


    public TNGouImageDataBean() {
    }

    public TNGouImageDataBean(int gallery, int id, String src) {
        this.gallery = gallery;
        this.id = id;
        this.src = src;
    }

    public int getGallery() {
        return gallery;
    }

    public TNGouImageDataBean setGallery(int gallery) {
        this.gallery = gallery;
        return this;
    }

    public int getId() {
        return id;
    }

    public TNGouImageDataBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public TNGouImageDataBean setSrc(String src) {
        this.src = src;
        return this;
    }

    @Override
    public String toString() {
        return "TNGouImageDataBean{" +
                "gallery=" + gallery +
                ", id=" + id +
                ", src='" + src + '\'' +
                '}';
    }
}
