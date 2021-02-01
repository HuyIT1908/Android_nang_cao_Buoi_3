package com.example.buoi_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LazyCursorAdapter extends BaseAdapter {
    Context context;
    List<DanhBa> ds = new ArrayList<>();

    public LazyCursorAdapter(Context context, List<DanhBa> ds) {
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
    public static class ViewHoler{
        TextView tv_b2;
        TextView tv_b3;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler viewHoler;
        if (convertView == null){
            viewHoler = new ViewHoler();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.show_image , null);

            viewHoler.tv_b2 = convertView.findViewById(R.id.tv_b2);
            viewHoler.tv_b3 = convertView.findViewById(R.id.tv_b3);

            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        viewHoler.tv_b2.setText(ds.get(position).getId());
        viewHoler.tv_b3.setText(ds.get(position).getName());
        return convertView;
    }
}
