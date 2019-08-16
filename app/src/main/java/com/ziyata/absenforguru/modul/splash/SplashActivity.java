package com.ziyata.absenforguru.modul.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.ziyata.absenforguru.MainActivity;
import com.ziyata.absenforguru.MainApp;
import com.ziyata.absenforguru.R;
import com.ziyata.absenforguru.api.parameters.UtilityResponseJson;
import com.ziyata.absenforguru.api.rest.ServiceGenerator;
import com.ziyata.absenforguru.api.rest.UserService;
import com.ziyata.absenforguru.db.Config;
import com.ziyata.absenforguru.db.Flag;
import com.ziyata.absenforguru.db.Gender;
import com.ziyata.absenforguru.db.Jurusan;
import com.ziyata.absenforguru.db.Kelas;
import com.ziyata.absenforguru.db.Pelajaran;
import com.ziyata.absenforguru.db.Siswa;
import com.ziyata.absenforguru.db.Status;
import com.ziyata.absenforguru.modul.Login.LoginGuruActivity;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chandra on 2019-07-12.
 * Bangunindo TBK
 * chandra@bangunindo.com
 */
public class SplashActivity extends AppCompatActivity {

    private ProgressBar progress_buffered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        initComponent();
    }

    private void initComponent() {
        progress_buffered = (ProgressBar) findViewById(R.id.progress_buffered);
        getData();
    }

    private void runProgressBuffered() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = progress_buffered.getProgress() + 5;
                progress_buffered.setProgress(progress);
                if (progress > 100) {
                    progress_buffered.setProgress(0);
                }
                //if (progress_buffered.getSecondaryProgress() > 0) {
                mHandler.postDelayed(this, 500);
                //}
            }
        };
        mHandler.postDelayed(runnable, 500);
    }
    private void getData() {
        progress_buffered.setProgress(20);
        UserService service = ServiceGenerator.createService(UserService.class, null, null);
        service.utility().enqueue(new Callback<UtilityResponseJson>() {
            @Override
            public void onResponse(Call<UtilityResponseJson> call, Response<UtilityResponseJson> response) {
                if (response.isSuccessful()) {
                    progress_buffered.setProgress(40);
                    Config config = response.body().getConfig();
                    List<Flag> flag = response.body().getFlag();
                    List<Gender> gender = response.body().getGender();
                    List<Jurusan> jurusan = response.body().getJurusan();
                    List<Kelas> kelas = response.body().getKelas();
                    List<Status> status = response.body().getStatus();
                    List<Pelajaran> pelajaran = response.body().getPelajaran();
                    saveData(config,flag,gender,jurusan,kelas,status,pelajaran);
                }else{
                    progress_buffered.setProgress(100);
                    Toast.makeText(SplashActivity.this, "System error", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UtilityResponseJson> call, Throwable t) {
                t.printStackTrace();
                progress_buffered.setProgress(100);
                Toast.makeText(SplashActivity.this, "System error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void saveData(Config config, List<Flag> flag, List<Gender> gender, List<Jurusan> jurusan, List<Kelas> kelas, List<Status> status, List<Pelajaran> pelajaran) {
        //Realm.init(getApplicationContext());
        //Realm realm = Realm.getDefaultInstance();
        Realm realm = MainApp.getInstance(this).getRealmInstance();

        realm.beginTransaction();
        realm.delete(Config.class);
        realm.copyToRealm(config);
        realm.commitTransaction();
        MainApp.getInstance(this).setConfig(config);
        progress_buffered.setProgress(50);

        realm.beginTransaction();
        realm.delete(Flag.class);
        realm.copyToRealm(flag);
        realm.commitTransaction();
        MainApp.getInstance(this).setFlags(flag);
        progress_buffered.setProgress(60);

        realm.beginTransaction();
        realm.delete(Gender.class);
        realm.copyToRealm(gender);
        realm.commitTransaction();
        progress_buffered.setProgress(70);

        realm.beginTransaction();
        realm.delete(Jurusan.class);
        realm.copyToRealm(jurusan);
        realm.commitTransaction();
        progress_buffered.setProgress(80);

        realm.beginTransaction();
        realm.delete(Kelas.class);
        realm.copyToRealm(kelas);
        realm.commitTransaction();
        progress_buffered.setProgress(90);

        realm.beginTransaction();
        realm.delete(Status.class);
        realm.copyToRealm(status);
        realm.commitTransaction();
        progress_buffered.setProgress(100);

        realm.beginTransaction();
        realm.delete(Pelajaran.class);
        realm.copyToRealm(pelajaran);
        realm.commitTransaction();
        progress_buffered.setProgress(110);

        if(progress_buffered.getProgress()==100){
            goModule();
        }
    }
    private void goModule(){
        Siswa siswa = MainApp.getInstance(this).getSiswa();
        Intent intent;
        if (siswa != null) {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, LoginGuruActivity.class);
        }
        startActivity(intent);
    }


}
