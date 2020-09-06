package com.thirdshop.po;

import java.io.Serializable;

public class CartDto implements Serializable {
    private Integer id;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
