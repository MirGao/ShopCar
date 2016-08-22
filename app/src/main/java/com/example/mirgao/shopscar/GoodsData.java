package com.example.mirgao.shopscar;

/**
 * Author ： MirGao
 * mailbox : mirgaoguo@foxmail.com
 * Created Time : 2016/8/22.
 */
public class GoodsData {

    private int type_id;
    private int productId;
    private String productName;
    private String productPrice;
    private int product_img;

    public void setProduct_img(int product_img) {
        this.product_img = product_img;
    }

    public int getProduct_img() {
        return product_img;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    public int getType_id() {
        return type_id;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
