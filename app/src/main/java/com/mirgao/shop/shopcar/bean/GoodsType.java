package com.mirgao.shop.shopcar.bean;

import java.util.List;

/**
 * Created by fengyongge on 2016/7/6 0006.
 */
public class GoodsType {
    private String id;
    private String Typename;
    private List<GoodsBean> list;

    public void setTypename(String typename) {
        Typename = typename;
    }

    public String getTypename() {
        return Typename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GoodsBean> getList() {
        return list;
    }

    public void setList(List<GoodsBean> list) {
        this.list = list;
    }

}
