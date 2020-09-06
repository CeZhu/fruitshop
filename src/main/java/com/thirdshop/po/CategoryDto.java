package com.thirdshop.po;

import java.util.List;

public class CategoryDto {
    ItemCategory father;
    List<ItemCategory> children;

    public ItemCategory getFather() {
        return father;
    }

    public void setFather(ItemCategory father) {
        this.father = father;
    }

    public List<ItemCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ItemCategory> children) {
        this.children = children;
    }
}
