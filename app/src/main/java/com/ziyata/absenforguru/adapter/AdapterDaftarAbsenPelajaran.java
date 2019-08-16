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
import com.ziyata.absenforguru.db.ListPelajarandb;

import java.util.ArrayList;

public class AdapterDaftarAbsenPelajaran extends RecyclerView.Adapter<AdapterDaftarAbsenPelajaran.ViewHolder>{

    private ArrayList<ListPelajarandb> listDaftarPelajaran = new ArrayList<>();
    private int selected_position = -1;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context ctx;

    public AdapterDaftarAbsenPelajaran(Context context, ArrayList<ListPelajarandb> listDaftarPelajaran) {
        this.mInflater = LayoutInflater.from(context);
        this.listDaftarPelajaran = listDaftarPelajaran;
        this.ctx = context;
        selected_position = listDaftarPelajaran.size()-1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_absen_pelajaran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if(listDaftarPelajaran.get(position).getFlag().equals("1")){
            holder.titleAbsen.setText("Absen Masuk");
            if(listDaftarPelajaran.get(position).getStatus().equals("T")){
                holder.statusAbsen.setText("Telat Hadir");
                //imageView.setColorFilter(activity.getResources().getColor(R.color.your_color));
                holder.icon.setColorFilter(Color.RED);
            }else{
                holder.icon.setColorFilter(Color.BLACK);
                holder.statusAbsen.setText("Hadir Tepat Waktu");
            }
            holder.jamAbsen.setText(listDaftarPelajaran.get(position).getIn());
            holder.icon.setImageResource(R.drawable.ic_masuk_absen);
        }else{
            holder.titleAbsen.setText("Absen Pulang");
            if(listDaftarPelajaran.get(position).getStatus().equals("T")){
                holder.statusAbsen.setText("Pulang lebih awal");
                holder.icon.setColorFilter(Color.RED);
            }else{
                holder.statusAbsen.setText("Pulang Tepat Waktu");
                holder.icon.setColorFilter(Color.BLACK);
            }
            holder.jamAbsen.setText(listDaftarPelajaran.get(position).getOut());
            holder.icon.setImageResource(R.drawable.ic_keluar_absen);
        }

        holder.dateAbsen.setText(listDaftarPelajaran.get(position).getDate());

        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);
                if (mClickListener != null) {
                    mClickListener.onItemClick(view, listDaftarPelajaran.get(position), position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDaftarPelajaran.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleAbsen,statusAbsen,jamAbsen,dateAbsen;
        ImageView icon;
        LinearLayout llMain;

        ViewHolder(View itemView) {
            super(itemView);
            titleAbsen = itemView.findViewById(R.id.titleAbsenPelajaran);
            statusAbsen = itemView.findViewById(R.id.statusAbsenPelajaran);
            jamAbsen = itemView.findViewById(R.id.jamAbsenPelajaran);
            dateAbsen = itemView.findViewById(R.id.dateAbsenPelajaran);
            icon = itemView.findViewById(R.id.iconAbsenPelajaran);
            llMain = itemView.findViewById(R.id.llMainPelajaran);
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return listDaftarPelajaran.get(id).getId();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, ListPelajarandb al, int position);
    }
}
