package com.example.before_170503_test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 黑明阳 on 2017/5/3.
 */
public class MAdapter extends BaseAdapter{
    ArrayList<Model> list1;
    public MAdapter(ArrayList<Model> list1) {
        this.list1=list1;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       mviewholder mholder;
        if(convertView==null){
            mholder=new mviewholder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,null);
            mholder.imageView= (ImageView) convertView.findViewById(R.id.item_listview_img);
            mholder.textView= (TextView) convertView.findViewById(R.id.item_listview_tv);
            convertView.setTag(mholder);
        }else{
            mholder= (mviewholder) convertView.getTag();
        }
        String url=list1.get(position).getFirstImg();
        Log.e("TAG",""+url.toString());
        Glide.with(parent.getContext())
                .load(url)
                .placeholder(R.drawable.only)
                .into(mholder.imageView);

        mholder.textView.setText(list1.get(position).getTitle());
        return convertView;
    }
    class mviewholder{
        ImageView imageView;
        TextView textView;
    }
}
