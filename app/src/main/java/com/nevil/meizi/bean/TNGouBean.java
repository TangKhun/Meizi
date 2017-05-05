package com.nevil.meizi.bean;

import java.util.List;

/**
 * Created by Tangkun on 2017/5/5.
 */

public class TNGouBean {
    String status;
    String total;
    List<TNGouDataBean> tngou;

    public TNGouBean() {
    }

    public TNGouBean(String status, String total, List<TNGouDataBean> tngou) {
        this.status = status;
        this.total = total;
        this.tngou = tngou;
    }

    public String getStatus() {
        return status;
    }

    public TNGouBean setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public TNGouBean setTotal(String total) {
        this.total = total;
        return this;
    }

    public List<TNGouDataBean> getTngou() {
        return tngou;
    }

    public TNGouBean setTngou(List<TNGouDataBean> tngou) {
        this.tngou = tngou;
        return this;
    }

    @Override
    public String toString() {
        return "TNGouBean{" +
                "status='" + status + '\'' +
                ", total='" + total + '\'' +
                ", tngou=" + tngou +
                '}';

    }
}
