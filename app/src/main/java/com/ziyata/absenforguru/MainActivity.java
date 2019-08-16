package com.ziyata.absenforguru;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ziyata.absenforguru.db.Siswa;
import com.ziyata.absenforguru.modul.listabsen.DaftarAbsen;
import com.ziyata.absenforguru.modul.listpelajaran.ListPelajaran;
import com.ziyata.absenforguru.modul.listnamasiswa.ListNamaSiswa;
import com.ziyata.absenforguru.utils.DialogActivity;
import com.ziyata.absenforguru.widget.DialogWidget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends DialogActivity {

    @BindView(R.id.daftarNama)
    CardView daftarNama;
    @BindView(R.id.daftarAbsen)
    CardView daftarAbsen;
    @BindView(R.id.daftarPelajaran)
    CardView daftarPelajaran;
    @BindView(R.id.titleBar)
    TextView titleBar;
    boolean doubleBackToExitPressedOnce = false;
    @BindView(R.id.imgLogout)
    ImageView imgLogout;
    @BindView(R.id.picProfile)
    RoundedImageView picProfile;

    private LocationManager locationManager;
    private Siswa siswa;
//    private Realm realm;
//    RealmResults<ListAbsen> listAbsens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        innitialComponnets();
    }

    private void innitialComponnets() {
        ButterKnife.bind(this);
//        realm = Realm.getDefaultInstance();
//        siswa = MainApp.getInstance(this).getSiswa();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        DialogWidget.isLocationStable(this, locationManager);

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeUser();
            }
        });

        picProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                //startActivity(i);
            }
        });

        daftarNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListNamaSiswa.class);
                startActivity(i);
            }
        });

        daftarAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DaftarAbsen.class);
                startActivity(i);
            }
        });

        daftarPelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListPelajaran.class);
                startActivity(i);
            }
        });

        //String pic = Basic.getUrlProfile() + siswa.getPhoto();
        //Tools.displayImageOriginal(this, picProfile, pic);
        //titleBar.setText(siswa.getNama());

    }

    private void removeUser() {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.delete(Siswa.class);
//        realm.commitTransaction();
//        Intent i = new Intent(DashboardActivity.this, LoginActifity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
}
