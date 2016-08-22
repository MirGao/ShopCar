package com.example.mirgao.shopscar;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ： MirGao
 * mailbox : mirgaoguo@foxmail.com
 * Created Time : 2016/8/22.
 */
public class shopCar extends Activity implements View.OnClickListener{

    private ListView lv_good_type, lv_good;
    private TextView tv_car, bv_unm, tv_totle_money, tv_go_pay;
    private RelativeLayout rl_bottom;
    private LinearLayout ll_shopcar;

    //底部购物车数据
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private SparseArray<GoodsData> shopcar_list;

    //分类
    private List<GoodsAll> alls = new ArrayList<>();
    //商品
    private List<GoodsData> goodsDatas = new ArrayList<>();

    //商品1
    private List<GoodsData> goodsDatas1 = new ArrayList<>();
    //商品2
    private List<GoodsData> goodsDatas2 = new ArrayList<>();
    //商品3
    private List<GoodsData> goodsDatas3 = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView(){
        lv_good_type = (ListView) findViewById(R.id.lv_goods_type);
        lv_good = (ListView) findViewById(R.id.lv_good);

        tv_car = (TextView) findViewById(R.id.tv_car);
        bv_unm = (TextView) findViewById(R.id.bv_unm);
        tv_totle_money = (TextView) findViewById(R.id.tv_totle_money);
        tv_go_pay = (TextView) findViewById(R.id.tv_go_pay);

        ll_shopcar = (LinearLayout) findViewById(R.id.ll_shopcar);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

        shopcar_list = new SparseArray<>();
    }

    private void initData(){
        for (int i = 0;i < 10;i++){
            GoodsData data = new GoodsData();
            data.setType_id(i);
            data.setProductId(i);
            data.setProductName("商品分类1  "+ i);
            data.setProductPrice(String.valueOf(i+1));
            data.setProduct_img(R.mipmap.iv_card);
            goodsDatas1.add(data);
        }

        for(int i = 0;i < 10;i++){
            GoodsData data = new GoodsData();
            data.setType_id(i);
            data.setProductId(i);
            data.setProductName("商品分类2  "+ i);
            data.setProductPrice(String.valueOf(i+1));
            data.setProduct_img(R.mipmap.iv_cash);
            goodsDatas2.add(data);
        }

        for(int i = 0;i < 10;i++){
            GoodsData data = new GoodsData();
            data.setType_id(i);
            data.setProductId(i);
            data.setProductName("商品分类3  "+ i);
            data.setProductPrice(String.valueOf(i+1));
            data.setProduct_img(R.mipmap.iv_yirisheng);
            goodsDatas3.add(data);
        }

        GoodsAll all1 = new GoodsAll();
        all1.setName("商品类别1");
        all1.setGoodsDatas(goodsDatas1);
        alls.add(all1);

        GoodsAll all2 = new GoodsAll();
        all1.setName("商品类别2");
        all1.setGoodsDatas(goodsDatas2);
        alls.add(all2);

        GoodsAll all3 = new GoodsAll();
        all1.setName("商品类别3");
        all1.setGoodsDatas(goodsDatas3);
        alls.add(all3);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

        //初始化
        goodsDatas.clear();
        goodsDatas.addAll(alls.get(0).getGoodsDatas());


    }

    @Override
    public void onClick(View v) {

    }
}
