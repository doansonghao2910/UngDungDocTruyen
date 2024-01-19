package com.example.appdoctruyen.adapter;

import static android.os.Build.VERSION_CODES.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {

    private Context context;
    private int layout;
    private List<chuyenmuc> chuyenmucList;

    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> chuyenmucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenmucList = chuyenmucList;
    }

    @Override
    public int getCount() {
        return chuyenmucList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        ImageView img = (ImageView) view.findViewById(com.example.appdoctruyen.R.id.imgchuyenmuc);
        TextView txt = (TextView) view.findViewById(com.example.appdoctruyen.R.id.textviewTenchuyemuc);
        chuyenmuc cm = chuyenmucList.get(i);
        txt.setText(cm.getTenchuyenmuc());
        img.setImageResource(cm.getHinhanhchuyenmuc());
      //  Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(com.example.appdoctruyen.R.drawable.ic_load).error(com.example.appdoctruyen.R.drawable.ic_image).into(img);
        return  view;
    }
}
