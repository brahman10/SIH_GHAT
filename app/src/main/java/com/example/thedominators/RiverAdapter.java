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

public class RiverAdapter extends ArrayAdapter<River> {

    private Activity context;
    private List<River> Place;

    public RiverAdapter(Activity context , List<River> Place){

        super(context , R.layout.river_layout,Place);
        this.context = context;
        this.Place = Place;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //LayoutInflater layoutInflater = context.getLayoutInflater();
        //View listViewItem = layoutInflater.inflate(R.layout.list_layout,null,true);
        if (convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.river_layout,parent,false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.river_name);
        TextView source = (TextView)convertView.findViewById(R.id.river_start);
        TextView desti = (TextView)convertView.findViewById(R.id.river_end);
        ImageView img = convertView.findViewById(R.id.river_img);

        final River river = (River) this.getItem(position);

        Glide.with(convertView).load(river.getImgid()).into(img);
        name.setText(river.getName());
        source.setText(river.getStart());
        desti.setText(river.getEnd());

        return convertView;
    }

    @Override
    public int getCount() {
        return Place.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public River getItem(int position) {
        return Place.get(position);
    }


}
