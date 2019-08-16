package com.ziyata.absenforguru.modul.listnamasiswa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ziyata.absenforguru.MainApp;
import com.ziyata.absenforguru.R;
import com.ziyata.absenforguru.adapter.AdapterDaftarAbsenPelajaran;
import com.ziyata.absenforguru.adapter.AdapterDaftarSiswa;
import com.ziyata.absenforguru.db.ListAbsen;
import com.ziyata.absenforguru.db.ListPelajarandb;
import com.ziyata.absenforguru.db.Siswa;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ListNamaSiswa extends AppCompatActivity {

    @BindView(R.id.nav_back)
    ImageView navBack;
    @BindView(R.id.rvDaftarSiswa)
    RecyclerView rvDaftarSiswa;
    Realm realm;
    List<Siswa> listsiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);
        initialComponents();
    }

    private void initialComponents() {
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        listsiswa = MainApp.getInstance(this).getSiswas();
        getAbsenToDay();
        navBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getAbsenToDay() {
        List<Siswa> listsiswa2 = new ArrayList<>();
        listsiswa = realm.where(Siswa.class).equalTo("siswaid",listsiswa.getId ()).findAll();
        for (int i =0;i<listsiswa2.size();i++) {
            listsiswa2.add(listsiswa2.get(i));
        }
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDaftarSiswa.setLayoutManager(horizontalLayoutManager);
        AdapterDaftarSiswa adapter = new AdapterDaftarSiswa(this,listsiswa);
        adapter.setClickListener(new AdapterDaftarSiswa.ItemClickListener() {
            @Override
            public void onItemClick(View view, ListAbsen la, int position) {
                showDialog(la);
            }
        });
        rvDaftarSiswa.setAdapter(adapter);
    }

    private void showDialog(ListAbsen al) {
    }
}
