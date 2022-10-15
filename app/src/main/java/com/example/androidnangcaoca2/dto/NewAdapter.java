package com.example.androidnangcaoca2.dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnangcaoca2.R;

import java.util.List;

public class NewAdapter extends BaseAdapter {
    Context context;
    List<NewItem> news;

    public NewAdapter(Context context, List<NewItem> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
              view = LayoutInflater.from(context).inflate(R.layout.lv_item_new, null);
              TextView txtTitle = view.findViewById(R.id.txtTitle);
              TextView txtDes = view.findViewById(R.id.txtDes);
              NewItem item = news.get(position);
              txtTitle.setText(item.getTitle());
              txtDes.setText(item.getDescription());


           return  view;
    }

    public  void updateBaseAdapter(List<NewItem> list) {
        this.news = list;
        notifyDataSetChanged();
    }

}
