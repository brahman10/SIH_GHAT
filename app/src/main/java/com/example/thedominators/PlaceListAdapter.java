package com.example.thedominators;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

    public static final String TAG = "PlaceListAdapter";
    Context mContext;
    private LayoutInflater mInflater;
    List<Upload> list;


    public PlaceListAdapter(Context ctx, List<Upload> list) {
        this.mContext = ctx;
        this.list = list;
        mInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public PlaceListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = list.get(holder.getAdapterPosition()).getCity();
                String name = list.get(holder.getAdapterPosition()).getName();
                String river = list.get(holder.getAdapterPosition()).getRiver();
                String about = list.get(holder.getAdapterPosition()).getAbout();
                String lati = list.get(holder.getAdapterPosition()).getLati();
                String longi = list.get(holder.getAdapterPosition()).getLongi();
                String imgurl = list.get(holder.getAdapterPosition()).getImgurl() ;

                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Url", imgurl);
                intent.putExtra("Longi", longi);
                intent.putExtra("Lati", lati);
                intent.putExtra("city", city);
                intent.putExtra("about", about);
                intent.putExtra("river",river);
                mContext.startActivity(intent);

                //mContext.startActivity(intent, options.toBundle());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Upload placeList = list.get(i);
        String url=placeList.getImgurl();


        viewHolder.place_name.setText(placeList.getName());

        if (url != null && !url.isEmpty()) {
            Picasso.with(mContext).load(url).fit().into(viewHolder.place_pic);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView place_name;
        ImageView place_pic;

        public ViewHolder(View itemView) {
            super(itemView);


            place_name = (TextView) itemView.findViewById(R.id.textviewName);
            place_pic = (ImageView) itemView.findViewById(R.id.item_image);

        }
    }


}