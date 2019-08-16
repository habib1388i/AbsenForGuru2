package com.ziyata.absenforguru.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziyata.absenforguru.R;
import com.ziyata.absenforguru.db.ListAbsen;

import java.util.ArrayList;

public class AdapterListAbsen extends RecyclerView.Adapter<AdapterListAbsen.ViewHolder> {

    private ItemClickListener mClickListener;
    private ArrayList<ListAbsen> listData = new ArrayList<>();
    private int selected_position = -1;
    private LayoutInflater mInflater;
    private Context ctx;

    public AdapterListAbsen(Context context, ArrayList<ListAbsen> listData) {
        this.mInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.ctx = context;
        selected_position = listData.size()-1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.absen_item, viewGroup, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(listData.get(i).getFlag().equals("1")){
            viewHolder.titleAbsen.setText("Absen Masuk");
            if(listData.get(i).getStatus().equals("T")){
                viewHolder.statusAbsen.setText("Telat Hadir");
                //imageView.setColorFilter(activity.getResources().getColor(R.color.your_color));
                viewHolder.icon.setColorFilter(Color.RED);
            }else{
                viewHolder.icon.setColorFilter(Color.BLACK);
                viewHolder.statusAbsen.setText("Hadir Tepat Waktu");
            }
            viewHolder.jamAbsen.setText(listData.get(i).getIn());
            viewHolder.icon.setImageResource(R.drawable.ic_masuk_absen);
        }else{
            viewHolder.titleAbsen.setText("Absen Pulang");
            if(listData.get(i).getStatus().equals("T")){
                viewHolder.statusAbsen.setText("Pulang lebih awal");
                viewHolder.icon.setColorFilter(Color.RED);
            }else{
                viewHolder.statusAbsen.setText("Pulang Tepat Waktu");
                viewHolder.icon.setColorFilter(Color.BLACK);
            }
            viewHolder.jamAbsen.setText(listData.get(i).getOut());
            viewHolder.icon.setImageResource(R.drawable.ic_keluar_absen);
        }

        viewHolder.dateAbsen.setText(listData.get(i).getDate());

        viewHolder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(selected_position);
                selected_position = i;
                notifyItemChanged(selected_position);
                if (mClickListener != null) {
                    mClickListener.onItemClick(view, listData.get(i), i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleAbsen,statusAbsen,jamAbsen,dateAbsen;
        ImageView icon;
        LinearLayout llMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleAbsen = itemView.findViewById(R.id.titleAbsen);
            statusAbsen = itemView.findViewById(R.id.statusAbsen);
            jamAbsen = itemView.findViewById(R.id.jamAbsen);
            dateAbsen = itemView.findViewById(R.id.dateAbsen);
            icon = itemView.findViewById(R.id.iconAbsen);
            llMain = itemView.findViewById(R.id.llMain);
        }
    }

    public String getItem(int id) {
        return listData.get(id).getId();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, ListAbsen la, int position);
    }

}
