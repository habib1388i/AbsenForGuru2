package com.ziyata.absenforguru.widget;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ziyata.absenforguru.utils.GPSTracker;

public class DialogWidget {

    public static void noConnectionResultUploadData(Context ctx) {
        new MaterialDialog.Builder(ctx)
                .title("Data seluler mati")
                .content("Nyalakan data seluler atau nyalakan wifi")
                .positiveText("Data Seluler")
                .neutralText("Simpan data")
                .negativeText("WIFI")
                .cancelable(false)
                .onPositive((dialog, which) -> {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$DataUsageSummaryActivity"));
                    ctx.startActivity(intent);
                })
                //.onNeutral((dialog, which) -> taskPresenter.saveLocalUpload(daoSession, null))
                .onNegative((dialog, which) -> ctx.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .show();
    }
    public static void isLocationStable(Context ctx, LocationManager locationManager) {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            turnOnGPS(ctx);
        } else {
            gpsIsTurnOn(ctx);
        }
    }
    public static void turnOnGPS(Context ctx) {
        new MaterialDialog.Builder(ctx)
                .title("GPS Tidak Aktif")
                .content("Mohon aktifkan GPS untuk bisa menambahkan Task")
                .positiveText("Nyalakan")
                .onPositive((dialog, which) -> {
                    ((Activity) ctx).startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 1);
                    dialog.dismiss();
                })
                .cancelable(false)
                .show();
    }
    public static void gpsIsTurnOn(Context ctx) {
        if (isLocationGranted(ctx)) {
            GPSTracker gpsTracker = new GPSTracker(ctx);
            if (gpsTracker.canGetLocation()) {
            }
        } else {
            requestPermission(ctx);
        }
    }
    private static boolean isLocationGranted(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int resultSecond = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED && resultSecond == PackageManager.PERMISSION_GRANTED;
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static void requestPermission(Context ctx) {
        if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) ctx,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(ctx)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions((Activity) ctx,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions((Activity) ctx,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }
}