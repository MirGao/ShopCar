package com.mirgao.shop.shopcar.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mirgao.shop.shopcar.MainActivity;
import com.mirgao.shop.shopcar.bean.GoodsBean;
import com.mirgao.shop.shopscar.R;

import java.util.List;


public class GoodsAdapter extends BaseAdapter {
    private List<GoodsBean> list;
    private Context context;
    private GoodsTypeAdapter goodsTypeAdapter;
    public GoodsAdapter(Context context, List<GoodsBean> list2, GoodsTypeAdapter goodsTypeAdapter) {
        this.context=context;
        this.list=list2;
        this.goodsTypeAdapter = goodsTypeAdapter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.shopcart_right_listview,null);
            viewholder=new Viewholder();
            viewholder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewholder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            viewholder.iv_add= (ImageView) convertView.findViewById(R.id.iv_add);
            viewholder.iv_remove= (ImageView) convertView.findViewById(R.id.iv_remove);
            viewholder.tv_acount= (TextView) convertView.findViewById(R.id.tv_acount);
            viewholder.iv_pic= (ImageView) convertView.findViewById(R.id.iv_pic);
            convertView.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertView.getTag();
        }

        viewholder.tv_name.setText(list.get(position).getName());
        viewholder.tv_price.setText("￥"+list.get(position).getPrice());


        if(list.get(position)!=null){
        //默认进来数量
            if (list.get(position).getNum()<1){
                viewholder.tv_acount.setVisibility(View.INVISIBLE);
                viewholder.iv_remove.setVisibility(View.INVISIBLE);
                goodsTypeAdapter.notifyDataSetChanged();
            }else{
                viewholder.tv_acount.setVisibility(View.VISIBLE);
                viewholder.iv_remove.setVisibility(View.VISIBLE);
                viewholder.tv_acount.setText(String.valueOf(list.get(position).getNum()));
                goodsTypeAdapter.notifyDataSetChanged();
            }
        }else{
            viewholder.tv_acount.setVisibility(View.INVISIBLE);
            viewholder.iv_remove.setVisibility(View.INVISIBLE);
        }

        viewholder.iv_pic.setBackgroundResource(R.drawable.iv);

        viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((MainActivity)context).getSelectedItemCountById(list.get(position).getProduct_id());
                if (count < 1) {
                    viewholder.iv_remove.setAnimation(getShowAnimation());
                    viewholder.iv_remove.setVisibility(View.VISIBLE);
                    viewholder.tv_acount.setVisibility(View.VISIBLE);
                }

                ((MainActivity)context).handlerCarNum(1,list.get(position),true);
                goodsTypeAdapter.notifyDataSetChanged();

                int[] loc = new int[2];
                viewholder.iv_add.getLocationInWindow(loc);

                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(context);
                ball.setImageResource(R.drawable.number);
                ((MainActivity)context).setAnim(ball, startLocation);// 开始执行动画

            }
        });

        viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((MainActivity)context).getSelectedItemCountById(list.get(position).getProduct_id());
                if (count < 2) {
                    viewholder.iv_remove.setAnimation(getHiddenAnimation());
                    viewholder.iv_remove.setVisibility(View.GONE);
                    viewholder.tv_acount.setVisibility(View.GONE);
                }
                ((MainActivity)context).handlerCarNum(0,list.get(position),true);
                goodsTypeAdapter.notifyDataSetChanged();

            }
        });

        return convertView;
    }
    class Viewholder{
        TextView tv_name,tv_original_price,tv_price;
        ImageView iv_add,iv_remove,iv_pic;
        TextView tv_acount;
    }



    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }



}
