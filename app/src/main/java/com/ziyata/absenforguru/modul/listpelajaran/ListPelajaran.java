package com.ziyata.absenforguru.modul.listpelajaran;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziyata.absenforguru.MainApp;
import com.ziyata.absenforguru.R;
import com.ziyata.absenforguru.adapter.AdapterDaftarAbsenPelajaran;
import com.ziyata.absenforguru.db.Flag;
import com.ziyata.absenforguru.db.Jurusan;
import com.ziyata.absenforguru.db.Kelas;
import com.ziyata.absenforguru.db.ListPelajarandb;
import com.ziyata.absenforguru.db.Siswa;
import com.ziyata.absenforguru.utils.Basic;
import com.ziyata.absenforguru.utils.DialogActivity;
import com.ziyata.absenforguru.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListPelajaran extends DialogActivity {


    @BindView(R.id.nav_back)
    ImageView navBack;
    @BindView(R.id.rvAbsenPelajaran)
    RecyclerView rvAbsenPelajaran;
    Siswa siswa;
    Realm realm;
    RealmResults<ListPelajarandb> daftarAbsenPelajaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_absen_pelajaran);
        initComponent();
    }

    private void initComponent() {
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        siswa = MainApp.getInstance(this).getSiswa();
        getAbsenToday();
        navBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getAbsenToday() {
        ArrayList<ListPelajarandb> absenPelajaranArrayList = new ArrayList<>();
        daftarAbsenPelajaran = realm.where(ListPelajarandb.class).equalTo("siswaid",siswa.getId()).findAll();
        for (int i =0;i<daftarAbsenPelajaran.size();i++) {
            absenPelajaranArrayList.add(daftarAbsenPelajaran.get(i));
        }
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAbsenPelajaran.setLayoutManager(horizontalLayoutManager);
        AdapterDaftarAbsenPelajaran adapter = new AdapterDaftarAbsenPelajaran(this, absenPelajaranArrayList);
        adapter.setClickListener(new AdapterDaftarAbsenPelajaran.ItemClickListener() {
            @Override
            public void onItemClick(View view, ListPelajarandb al, int position) {
                showDialog(al);
            }
        });
        rvAbsenPelajaran.setAdapter(adapter);
    }

    private void showDialog(ListPelajarandb al) {
        RealmResults<Kelas> kelas = realm.where(Kelas.class).equalTo("id",siswa.getId()).findAll();
        RealmResults<Jurusan> jurusans = realm.where(Jurusan.class).equalTo("id",kelas.get(0).getJurusanId()).findAll();
        RealmResults<Flag> flags = realm.where(Flag.class).equalTo("id",al.getFlag()).findAll();

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.detail_absen_pelajaran);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ImageView picProfile = dialog.findViewById(R.id.picProfile);
        TextView nama = dialog.findViewById(R.id.nama);
        TextView label = dialog.findViewById(R.id.labelPelajaran);
        TextView tvFlag = dialog.findViewById(R.id.tvFlagPelajaran);
        TextView tvLatitude = dialog.findViewById(R.id.tvLatitudePelajaran);
        TextView tvLongtitude = dialog.findViewById(R.id.tvLongtitudePelajaran);
        ImageView imgAbsen = dialog.findViewById(R.id.imgAbsenPelajaran);
        TextView tvJam = dialog.findViewById(R.id.tvJamPelajaran);
        TextView tvStatus = dialog.findViewById(R.id.tvStatusPelajaran);

        String pic = Basic.getUrlProfile() +siswa.getPhoto();
        Tools.displayImageOriginal(this,picProfile,pic);
        nama.setText(siswa.getNama());
        label.setText("Kelas "+kelas.get(0).getKelas()+" "+jurusans.get(0).getName());
        tvFlag.setText("Absen "+flags.get(0).getName());
        if(al.getStatus().equals("T")){
            tvStatus.setText("Terlambat");
            tvStatus.setTextColor(Color.RED);
        }else{
            tvStatus.setText("Tepat Waktu");
            tvStatus.setTextColor(Color.parseColor("#1B5E20"));
        }
        tvLatitude.setText(al.getLatitude());
        tvLongtitude.setText(al.getLongtitude());
        String picAbsen = Basic.getUrlAbsen()+al.getId()+"/"+al.getCapture();
        Tools.displayImageOriginal(this,imgAbsen,picAbsen);
        if(al.getFlag().equals("1")) {
            tvJam.setText(al.getIn());
        }else{
            tvJam.setText(al.getOut());
        }


        ((AppCompatButton) dialog.findViewById(R.id.bt_cancelPelajaran)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
