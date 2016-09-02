package com.mirgao.shop.shopcar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.mirgao.shop.shopcar.adapter.GoodsTypeAdapter;
import com.mirgao.shop.shopcar.adapter.GoodsAdapter;
import com.mirgao.shop.shopcar.adapter.GoodsDetailAdapter;
import com.mirgao.shop.shopcar.adapter.BottomAdapter;
import com.mirgao.shop.shopcar.bean.GoodsType;
import com.mirgao.shop.shopcar.bean.GoodsBean;
import com.mirgao.shop.shopcar.bean.ItemBean;
import com.mirgao.shop.shopcar.view.MyListView;
import com.mirgao.shop.shopscar.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private ListView lv_goods_type, lv_goods;
    private TextView tv_car,tv_count, tv_totle_money,tv_bv_unm;
    private RelativeLayout rl_bottom;
    private LinearLayout ll_shopcar;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private View bottomDetailSheet;

    private Double totleMoney = 0.00;
    private static DecimalFormat df;

    //分类和商品
    private List<GoodsType> goodsTypes = new ArrayList<GoodsType>();
    private List<GoodsBean> goodsBeen = new ArrayList<GoodsBean>();

    //分类下商品adapter
    private GoodsTypeAdapter goodsTypeAdapter;
    private GoodsAdapter goodsAdapter;
    //底部购物车
    private BottomAdapter bottomAdapter;
    //商品详情
    private GoodsDetailAdapter goodsDetailAdapter;

    private SparseArray<GoodsBean> selectedList;
    //套餐
    private List<GoodsBean> list1 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list2 = new ArrayList<GoodsBean>();
    private List<GoodsBean> list3 = new ArrayList<GoodsBean>();


    private Handler mHanlder;
    private ViewGroup anim_mask_layout;//动画层


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHanlder = new Handler(getMainLooper());
        initView();
        initData();
        initListener();
    }

    public void initView() {
        lv_goods_type = (ListView) findViewById(R.id.lv_goods_type);
        lv_goods = (ListView) findViewById(R.id.lv_goods);

        tv_car = (TextView) findViewById(R.id.tv_car);
        //底部控件
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tv_count = (TextView) findViewById(R.id.tv_count);

        tv_bv_unm = (TextView) findViewById(R.id.tv_bv_unm);
        tv_totle_money = (TextView) findViewById(R.id.tv_totle_money);
        ll_shopcar = (LinearLayout) findViewById(R.id.ll_shopcar);
        selectedList = new SparseArray<>();
        df = new DecimalFormat("0.00");
    }

    //填充数据
    private void initData() {
//        //商品
        for (int i= 0; i < 10; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setName("商品" + i);
            goodsBean.setProduct_id(i);
            goodsBean.setType_id(i);
            goodsBean.setPrice("100");
            list1.add(goodsBean);
        }

        //商品
        for (int j = 10; j < 20; j++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setName("商品" + j);
            goodsBean.setProduct_id(j);
            goodsBean.setType_id(j);
            goodsBean.setPrice("60");
            list2.add(goodsBean);
        }

        //商品
        for (int j = 20; j < 30; j++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.setName("商品" + j);
            goodsBean.setProduct_id(j);
            goodsBean.setType_id(j);
            goodsBean.setPrice("20");
            list3.add(goodsBean);
        }


        GoodsType goodsType1= new GoodsType();
        goodsType1.setTypename("分类1");
        goodsType1.setList(list1);
        goodsTypes.add(goodsType1);

        GoodsType goodsType2 = new GoodsType();
        goodsType2.setTypename("分类2");
        goodsType2.setList(list2);
        goodsTypes.add(goodsType2);

        GoodsType goodsType3 = new GoodsType();
        goodsType3.setTypename("分类3");
        goodsType3.setList(list3);
        goodsTypes.add(goodsType3);

        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);

        //默认值
        goodsBeen.clear();
        goodsBeen.addAll(goodsTypes.get(0).getList());

        //分类
        goodsTypeAdapter = new GoodsTypeAdapter(this, goodsTypes);
        lv_goods_type.setAdapter(goodsTypeAdapter);
        goodsTypeAdapter.notifyDataSetChanged();
        //商品
        goodsAdapter = new GoodsAdapter(this, goodsBeen, goodsTypeAdapter);
        lv_goods.setAdapter(goodsAdapter);
        goodsAdapter.notifyDataSetChanged();

    }


    //添加监听
    private void initListener() {
        lv_goods_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goodsBeen.clear();
                goodsBeen.addAll(goodsTypes.get(position).getList());
                goodsTypeAdapter.setSelection(position);
                goodsTypeAdapter.notifyDataSetChanged();
                goodsAdapter.notifyDataSetChanged();

            }
        });

        ll_shopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });
    }


    //创建套餐详情view
    public void showDetailSheet(List<ItemBean> listItem, String mealName) {
        bottomDetailSheet = createMealDetailView(listItem, mealName);
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
            if (listItem.size() != 0) {
                bottomSheetLayout.showWithSheetView(bottomDetailSheet);
            }
        }
    }

    //查看套餐详情
    private View createMealDetailView(List<ItemBean> listItem, String mealName) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_goods_detail, (ViewGroup) getWindow().getDecorView(), false);
        ListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView tv_meal = (TextView) view.findViewById(R.id.tv_meal);
        TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
        int count = 0;
        for (int i = 0; i < listItem.size(); i++) {
            count = count + Integer.parseInt(listItem.get(i).getNote2());
        }
        tv_meal.setText(mealName);
        tv_num.setText("(共" + count + "件)");
        goodsDetailAdapter = new GoodsDetailAdapter(MainActivity.this, listItem);
        lv_product.setAdapter(goodsDetailAdapter);
        goodsDetailAdapter.notifyDataSetChanged();
        return view;
    }


    //创建购物车view
    private void showBottomSheet() {
        bottomSheet = createBottomSheetView();
        if (bottomSheetLayout.isSheetShowing()) {
        } else {
            bottomSheetLayout.dismissSheet();
            if (selectedList.size() != 0) {
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }


    //查看购物车布局
    private View createBottomSheetView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet, (ViewGroup) getWindow().getDecorView(), false);
        MyListView lv_product = (MyListView) view.findViewById(R.id.lv_product);
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });
        bottomAdapter = new BottomAdapter(MainActivity.this, goodsAdapter, selectedList);
        lv_product.setAdapter(bottomAdapter);
        return view;
    }

    //清空购物车
    public void clearCart() {
        selectedList.clear();
        goodsBeen.clear();
        if (goodsTypes.size() > 0) {
            for (int j = 0; j < goodsTypes.size(); j++) {
                for (int i = 0; i < goodsTypes.get(j).getList().size(); i++) {
                    goodsTypes.get(j).getList().get(i).setNum(0);
                }
            }
            goodsBeen.addAll(goodsTypes.get(0).getList());
            goodsTypeAdapter.setSelection(0);
            //刷新不能删
            goodsTypeAdapter.notifyDataSetChanged();
            goodsAdapter.notifyDataSetChanged();
        }
        update(true);
    }


    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id) {
        GoodsBean temp = selectedList.get(id);
        if (temp == null) {
            return 0;
        }
        return temp.getNum();
    }


    public void handlerCarNum(int type, GoodsBean goodsBean, boolean refreshGoodList) {
        if (type == 0) {
            GoodsBean temp = selectedList.get(goodsBean.getProduct_id());
            if (temp != null) {
                if (temp.getNum() < 2) {
                    goodsBean.setNum(0);
                    selectedList.remove(goodsBean.getProduct_id());

                } else {
                    int i = goodsBean.getNum();
                    goodsBean.setNum(--i);
                }
            }


        } else if (type == 1) {
            GoodsBean temp = selectedList.get(goodsBean.getProduct_id());
            if (temp == null) {
                goodsBean.setNum(1);
                selectedList.append(goodsBean.getProduct_id(), goodsBean);
            } else {
                int i = goodsBean.getNum();
                goodsBean.setNum(++i);
            }
        }

        update(refreshGoodList);

    }


    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList) {
        int size = selectedList.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            GoodsBean item = selectedList.valueAt(i);
            count += item.getNum();
            totleMoney += item.getNum() * Double.parseDouble(item.getPrice());
        }
        tv_totle_money.setText("￥" + String.valueOf(df.format(totleMoney)));
        totleMoney = 0.00;
        if (count < 1) {
            tv_bv_unm.setVisibility(View.GONE);
        } else {
            tv_bv_unm.setVisibility(View.VISIBLE);
        }

        tv_bv_unm.setText(String.valueOf(count));

        if (bottomAdapter != null) {
            bottomAdapter.notifyDataSetChanged();
        }

        if (goodsAdapter != null) {
            goodsAdapter.notifyDataSetChanged();
        }

        if (goodsTypeAdapter != null) {
            goodsTypeAdapter.notifyDataSetChanged();
        }

        if (bottomSheetLayout.isSheetShowing() && selectedList.size() < 1) {
            bottomSheetLayout.dismissSheet();
        }
    }


    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        tv_car.getLocationInWindow(endLocation);
        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }
        });

    }


}
