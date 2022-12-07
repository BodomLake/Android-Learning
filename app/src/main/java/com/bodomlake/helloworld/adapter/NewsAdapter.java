package com.bodomlake.helloworld.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bodomlake.helloworld.R;
import com.bodomlake.helloworld.entity.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<News> mData;
    private Context mContext;

    public NewsAdapter(List<News> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout, parent, false);
//        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView content = (TextView) convertView.findViewById(R.id.tv_content);
//        img_icon.setBackgroundResource(mData.get(position).getaIcon());
//        title.setText(mData.get(position).getTitle());
//        content.setText(mData.get(position).getContent());
//        return convertView;
//    }


    //BaseAdapter 优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        // 第一次执行 getView()，那么convertView就没进入缓存
        if (convertView == null) {
            holder = new ViewHolder();
            // 布局填充器（指定Activity）填充了R（资源）的一部分；
            // parent属于是容器；
            // attachToRoot : false 不要附着到根View控件上面
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_layout
                    , parent
                    , false);
            // 依次设置对应的组件
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 让每个组件分别设置属性
        holder.img_icon.setBackgroundResource(mData.get(position).getaIcon());
        holder.title.setText(mData.get(position).getTitle());
        holder.content.setText(mData.get(position).getContent());
        return convertView;
    }

    // 保有多个空间的Holder
    static class ViewHolder {
        ImageView img_icon;
        TextView title;
        TextView content;
    }
}