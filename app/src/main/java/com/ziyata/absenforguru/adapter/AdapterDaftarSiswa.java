package com.ziyata.absenforguru.adapter;

import android.content.Context;
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
import com.ziyata.absenforguru.db.ListPelajarandb;
import com.ziyata.absenforguru.db.Siswa;

import java.util.ArrayList;
import java.util.List;

public class AdapterDaftarSiswa extends RecyclerView.Adapter<AdapterDaftarSiswa.ViewHolder> {

    private List<Siswa> listsiswa= new ArrayList<>();
    private int selected_position = -1;
    private LayoutInflater mInflater;
    private AdapterDaftarSiswa.ItemClickListener mClickListener;
    private Context ctx;


    

    public AdapterDaftarSiswa(Context context,List<Siswa> listsiswa) {
        this.mInflater = LayoutInflater.from(context);
        this.listsiswa = listsiswa;
        this.ctx = context;
        selected_position = listsiswa.size()-1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_daftar_siswa, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listsiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleSiswa , namaSiswa , alamatSiswa , emailSiswa , dateSiswa;
        ImageView imgeProfile;
        LinearLayout llMain;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaSiswa = itemView.findViewById(R.id.tvnamasiswa);
            alamatSiswa = itemView.findViewById(R.id.alamatsiswa);
            emailSiswa = itemView.findViewById(R.id.tvemailsiswa);
            dateSiswa = itemView.findViewById(R.id.tvdateAbsenSiswa);
            titleSiswa = itemView.findViewById(R.id.tvtitleDaftarsiswa);
            imgeProfile = itemView.findViewById(R.id.photosiswa);
            llMain = itemView.findViewById(R.id.llMainSiswa);
//            pelajaran = itemView.findViewById(R.id.t);
//            titleAbsen = itemView.findViewById(R.id.titleAbsenPelajaran);
//            statusAbsen = itemView.findViewById(R.id.statusAbsenPelajaran);
//            jamAbsen = itemView.findViewById(R.id.jamAbsenPelajaran);
//            dateAbsen = itemView.findViewById(R.id.dateAbsenPelajaran);
//            icon = itemView.findViewById(R.id.iconAbsenPelajaran);
//            llMain = itemView.findViewById(R.id.llMainPelajaran);
        }
    }
    public String getItem(int id) {
        return listsiswa.get(id).getId();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, ListAbsen la, int position);

    }
}
