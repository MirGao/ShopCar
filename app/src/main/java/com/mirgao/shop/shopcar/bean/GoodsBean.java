package com.mirgao.shop.shopcar.bean;

/**
 * Created by fengyongge on 2016/7/6 0006.
 */
public class GoodsBean {

    public int product_id;
    public int type_id;
    public String name;
    public String image;
    public String price;
    public int num;

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getType_id() {
        return type_id;
    }

    public int getNum() {
        return num;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
