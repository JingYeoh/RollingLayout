package com.jkb.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * The Adapter for RollingLayout.
 * <p>
 * Created by yangjing on 17-7-21.
 */

public class RollingAdapter extends BaseAdapter {

    private Context context;

    public RollingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_rolling, parent, false);
    }
}
