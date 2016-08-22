package com.example.mirgao.shopscar;

import java.util.List;

/**
 * Author ： MirGao
 * mailbox : mirgaoguo@foxmail.com
 * Created Time : 2016/8/22.
 */
public class GoodsAll {
    private String name;
    private String id;
    private List<GoodsData> goodsDatas;

    public void setId(String id) {
        this.id = id;
    }

    public void setGoodsDatas(List<GoodsData> goodsDatas) {
        this.goodsDatas = goodsDatas;
    }

    public List<GoodsData> getGoodsDatas() {
        return goodsDatas;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
