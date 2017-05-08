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
public class MyAdapter extends BaseAdapter{
    private ArrayList<Mint> list;
    public MyAdapter(ArrayList<Mint> list) {
        this.list=list;
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
           String url=list.get(position).getResult().getList().get(position).getFirstImg();
        Log.e("TAG",""+url.toString());
    /*         Picasso.with(parent.getContext())
                 .load(url)
                .placeholder(R.drawable.only)
                .into(mholder.imageView);
*/
        Glide.with(parent.getContext())
                .load(url)
                .placeholder(R.drawable.only)
                .into(mholder.imageView);
        mholder.textView.setText(list.get(position).getResult().getList().get(position).getTitle());
        return convertView;
    }
    class mviewholder{
        ImageView imageView;
        TextView textView;
    }
}
