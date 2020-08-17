package com.example.thedominators;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Upload> {

    private Activity context;
    private List<Upload> UploadList;

    public DataAdapter(Activity context , List<Upload> UploadList){

        super(context , R.layout.list_layout,UploadList);
        this.context = context;
        this.UploadList = UploadList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //LayoutInflater layoutInflater = context.getLayoutInflater();
        //View listViewItem = layoutInflater.inflate(R.layout.list_layout,null,true);
        if (convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.textName);
        ImageView img = (ImageView) convertView.findViewById(R.id.img_item);

        final Upload places = (Upload)this.getItem(position);

        name.setText(places.getName());

        Glide.with(convertView).load(places.getImgurl()).into(img);

        return convertView;
    }

    @Override
    public int getCount() {
        return UploadList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public Upload getItem(int position) {
        return UploadList.get(position);
    }


}
