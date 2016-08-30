package com.example.mirgao.shopscar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mirgao.shopscar.GoodsAll;
import com.example.mirgao.shopscar.R;

import java.util.List;

/**
 * Author ： MirGao
 * mailbox : mirgaoguo@foxmail.com
 * Created Time : 2016/8/22.
 */
public class GoodsTypeAdapter extends BaseAdapter {

    private Context context;
    private List<GoodsAll> alls;
    int selection = 0;

    public GoodsTypeAdapter(Context context,List<GoodsAll> alls){
        this.context = context;
        this.alls = alls;
    }

    @Override
    public int getCount() {
        return alls.size();
    }

    @Override
    public Object getItem(int position) {
        return alls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Viewholder viewholder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.goods_type_list,null);
            viewholder = new Viewholder();

            viewholder.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            viewholder.viewRed = (View) convertView.findViewById(R.id.v_red);
            viewholder.viewV = (View) convertView.findViewById(R.id.view_v);
            viewholder.ll_goods_menu = (LinearLayout) convertView.findViewById(R.id.ll_goods);

            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

       viewholder.tv_type.setText(alls.get(position).getName());//商品名称

        return convertView;
    }

    class Viewholder {
        TextView tv_type;
        LinearLayout ll_goods_menu;
        View viewRed, viewV;
    }
}
