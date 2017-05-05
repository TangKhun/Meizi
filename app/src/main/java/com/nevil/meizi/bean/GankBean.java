package com.nevil.meizi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tangkun on 2017/5/4.
 */

public class GankBean implements Serializable {

    String error;
    List<GankDataBean> results;

    public GankBean(String error, List<GankDataBean> results) {
        this.error = error;
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public GankBean setError(String error) {
        this.error = error;
        return this;
    }

    public List<GankDataBean> getResults() {
        return results;
    }

    public GankBean setResults(List<GankDataBean> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return "GankBean{" +
                "error='" + error + '\'' +
                ", results=" + results +
                '}';
    }
}
