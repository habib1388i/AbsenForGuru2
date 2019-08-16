package com.ziyata.absenforguru;

import android.app.Application;
import android.content.Context;

import com.ziyata.absenforguru.db.Config;
import com.ziyata.absenforguru.db.Flag;
import com.ziyata.absenforguru.db.Siswa;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApp extends Application {
    private static final int SCHEMA_VERSION = 1;
    private Realm realmInstance;
    private Siswa siswa;
    private com.ziyata.absenforguru.db.Config config;
    private List<Siswa> siswas;
    private List<Flag> flags;
    public static MainApp getInstance(Context context) {
        return (MainApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        final RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        realmInstance = Realm.getDefaultInstance();

        start();
    }

    public Siswa getSiswa() {
        return siswa;
    }
    public void setSiswa(Siswa siswa){this.siswa = siswa;}

    public Config getConfig(){return config;}
    public void setConfig(com.ziyata.absenforguru.db.Config config){this.config=config;}

    public List<Flag> getFlags(){return flags;}
    public void setFlags(List<Flag> flags){this.flags=flags;}

    public List<Siswa> getSiswas(){return siswas;}
    public void setSiswas(List<Siswa> siswas1){this.siswas=siswas1;}

    public final Realm getRealmInstance() {
        return realmInstance;
    }
    private void start(){
        Realm realm = getRealmInstance();
        siswa = realm.where(Siswa.class).findFirst();
        if (siswa != null) {
            setSiswa(siswa);
        }
    }

}
