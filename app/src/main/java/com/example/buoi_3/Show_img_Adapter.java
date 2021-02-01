package com.example.buoi_3;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Show_img_Adapter extends BaseAdapter {
    Context context;
    List<String> ds = new ArrayList<>();

    public Show_img_Adapter(Context context, List<String> ds) {
        this.context = context;
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholer viewholer;
        if (convertView == null){
            viewholer = new Viewholer();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.show_img , null);

            viewholer.imv_them = convertView.findViewById(R.id.imv_them);

            convertView.setTag(viewholer);
        }else {
            viewholer = (Viewholer) convertView.getTag();
        }
        viewholer.imv_them.setImageBitmap(BitmapFactory.decodeFile(ds.get(position)));
        return convertView;
    }
    public static class Viewholer{
        ImageView imv_them;
    }
}
