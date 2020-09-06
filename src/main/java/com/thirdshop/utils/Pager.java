package com.thirdshop.utils;

import com.github.pagehelper.Page;

import java.util.List;

public class Pager<T> {
    private int size;
    private int offset;
    private long total;
    private List<T> datas;

    public int getSize(){
        return size;
    }

    public void setSize(int size) {
        this.size=size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset=offset;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total=total;
    }

    public List<T> getdatas() {
        return datas;
    }

    public void setdatas(List<T> datas) {
        this.datas=datas;
    }

    public Pager() {

    }

    public Pager(List<T> datas) {
        if (datas instanceof Page) {
            Page<T> page=(Page<T>)datas;
            setOffset(page.getPageNum());
            setSize(page.getPageSize());
            setTotal(page.getTotal());
            setdatas(datas);
        }
    }
}
