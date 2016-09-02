package com.mirgao.shop.shopcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mirgao.shop.shopcar.bean.GoodsType;
import com.mirgao.shop.shopscar.R;

import java.util.List;

public class GoodsTypeAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsType> list;
    int selection = 0;

    public GoodsTypeAdapter(Context context, List<GoodsType> list) {
        this.context = context;
        this.list = list;
    }

    public void setSelection(int selection) {
        this.selection = selection;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        if (convertView == null) {
            viewholder = new Viewholder();
            convertView = LayoutInflater.from(context).inflate(R.layout.shopcart_left_listview, null);
            viewholder.tv_type_name = (TextView) convertView.findViewById(R.id.tv_type_name);
            viewholder.viewRed = (View) convertView.findViewById(R.id.item_menu_red);
            viewholder.viewV = (View) convertView.findViewById(R.id.item_menu_view_v);
            viewholder.ll_goods_menu = (LinearLayout) convertView.findViewById(R.id.ll_goods_menu);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        viewholder.tv_type_name.setText(list.get(position).getTypename());

        //设置所有的item显示为默认状态
        viewholder.ll_goods_menu.setBackgroundResource(R.color.color_menu_back);
        viewholder.viewRed.setVisibility(View.GONE);
        viewholder.viewV.setVisibility(View.VISIBLE);

        if (position == selection) {
            viewholder.ll_goods_menu.setBackgroundResource(R.color.white);
            viewholder.tv_type_name.setTextColor(context.getResources().getColor(R.color.black));
            viewholder.viewRed.setVisibility(View.VISIBLE);
            viewholder.viewV.setVisibility(View.GONE);
        }
        return convertView;
    }

    class Viewholder {
        TextView tv_type_name;
        LinearLayout ll_goods_menu;
        View viewRed, viewV;
    }

}
