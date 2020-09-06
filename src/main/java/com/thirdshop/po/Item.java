package com.thirdshop.po;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
    private Integer id;
    private String name;
    private String price;
    /**
     * 收藏数
     */
    private Integer scNum;
    /**
     * 购买数
     */
    private Integer gmNum;
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String ms;
    private String pam1;
    private String pam2;
    private String pam3;
    private String val1;
    private String val2;
    private String val3;
    private Integer type;
    /**
     * 折扣
     */
    private Integer zk;
    private Integer categoryIdOne;
    private ItemCategory yiji;
    private Integer categoryIdTwo;
    private ItemCategory erji;
    private Integer isDelete;
    private List<Comment> pls;

    public List<Comment> getPls() {
        return pls;
    }

    public void setPls(List<Comment> pls) {
        this.pls = pls;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getScNum() {
        return scNum;
    }

    public void setScNum(Integer scNum) {
        this.scNum = scNum;
    }

    public Integer getGmNum() {
        return gmNum;
    }

    public void setGmNum(Integer gmNum) {
        this.gmNum = gmNum;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getPam1() {
        return pam1;
    }

    public void setPam1(String pam1) {
        this.pam1 = pam1;
    }

    public String getPam2() {
        return pam2;
    }

    public void setPam2(String pam2) {
        this.pam2 = pam2;
    }

    public String getPam3() {
        return pam3;
    }

    public void setPam3(String pam3) {
        this.pam3 = pam3;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getZk() {
        return zk;
    }

    public void setZk(Integer zk) {
        this.zk = zk;
    }

    public Integer getCategoryIdOne() {
        return categoryIdOne;
    }

    public void setCategoryIdOne(Integer categoryIdOne) {
        this.categoryIdOne = categoryIdOne;
    }

    public Integer getCategoryIdTwo() {
        return categoryIdTwo;
    }

    public void setCategoryIdTwo(Integer categoryIdTwo) {
        this.categoryIdTwo = categoryIdTwo;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public ItemCategory getYiji() {
        return yiji;
    }

    public void setYiji(ItemCategory yiji) {
        this.yiji = yiji;
    }

    public ItemCategory getErji() {
        return erji;
    }

    public void setErji(ItemCategory erji) {
        this.erji = erji;
    }
}
