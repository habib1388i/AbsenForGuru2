package com.ziyata.absenforguru.modul.listabsen;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.ziyata.absenforguru.adapter.AdapterListAbsen;
import com.ziyata.absenforguru.db.Flag;
import com.ziyata.absenforguru.db.Jurusan;
import com.ziyata.absenforguru.db.Kelas;
import com.ziyata.absenforguru.db.ListAbsen;
import com.ziyata.absenforguru.db.Siswa;
import com.ziyata.absenforguru.utils.Basic;
import com.ziyata.absenforguru.utils.DateUtil;
import com.ziyata.absenforguru.utils.Tools;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class DaftarAbsen extends AppCompatActivity {

    @BindView(R.id.nav_toggle)
    ImageView navToggle;
    @BindView(R.id.rvAbsen)
    RecyclerView rvAbsen;
    @BindView(R.id.titleBar)
    TextView titleBar;

    private Siswa siswa;
    private Realm realm;
    RealmResults<ListAbsen> listAbsens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_absen);
        ButterKnife.bind(this);
        initialComponnets();
    }

    private void initialComponnets() {
        ButterKnife.bind(this);
        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();
        siswa = MainApp.getInstance(this).getSiswa();
        navToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getAbsen();
    }

    private void getAbsen() {
        ArrayList<ListAbsen> lstData = new ArrayList<>();
        listAbsens = realm.where(ListAbsen.class).equalTo("date", DateUtil.getDateServerNow()).equalTo("siswaId",siswa.getId()).findAll();
        for(int i =0;i<listAbsens.size();i++){
            lstData.add(listAbsens.get(i));
        }
        LinearLayoutManager verticalLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAbsen.setLayoutManager(verticalLayoutManager);
        AdapterListAbsen adapter = new AdapterListAbsen(this, lstData);
        adapter.setClickListener(new AdapterListAbsen.ItemClickListener() {
            @Override
            public void onItemClick(View view, ListAbsen la, int position) {
                showDialog(la);
            }
        });
        rvAbsen.setAdapter(adapter);
    }

    private void showDialog(ListAbsen la) {
        RealmResults<Kelas> kelas = realm.where(Kelas.class).equalTo("id", siswa.getKelasId()).findAll();
        RealmResults<Jurusan> jurusans = realm.where(Jurusan.class).equalTo("id", kelas.get(0).getJurusanId()).findAll();
        RealmResults<Flag> flags = realm.where(Flag.class).equalTo("id", la.getFlag()).findAll();

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.absen_detail_dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ImageView picProfile = dialog.findViewById(R.id.picProfile);
        TextView nama = dialog.findViewById(R.id.nama);
        TextView label = dialog.findViewById(R.id.label);
        TextView tvFlag = dialog.findViewById(R.id.tvFlag);
        TextView tvLatitude = dialog.findViewById(R.id.tvLatitude);
        TextView tvLongtitude = dialog.findViewById(R.id.tvLongtitude);
        ImageView imgAbsen = dialog.findViewById(R.id.imgAbsen);
        TextView tvJam = dialog.findViewById(R.id.tvJam);
        TextView tvStatus = dialog.findViewById(R.id.tvStatus);

        String pic = Basic.getUrlProfile() + siswa.getPhoto();
        Tools.displayImageOriginal(this, picProfile, pic);
        nama.setText(siswa.getNama());
        label.setText("Kelas " + kelas.get(0).getKelas() + " " + jurusans.get(0).getName());
        tvFlag.setText("Absen " + flags.get(0).getName());
        if (la.getStatus().equals("T")) {
            tvStatus.setText("Terlambat");
            tvStatus.setTextColor(Color.RED);
        } else {
            tvStatus.setText("Tepat Waktu");
            tvStatus.setTextColor(Color.parseColor("#1B5E20"));
        }
        tvLatitude.setText(la.getLatitude());
        tvLongtitude.setText(la.getLongtitude());
        String picAbsen = Basic.getUrlAbsen() + la.getId() + "/" + la.getCapture();
        Tools.displayImageOriginal(this, imgAbsen, picAbsen);
        if (la.getFlag().equals("1")) {
            tvJam.setText(la.getIn());
        } else {
            tvJam.setText(la.getOut());
        }

        ((AppCompatButton) dialog.findViewById(R.id.bt_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}