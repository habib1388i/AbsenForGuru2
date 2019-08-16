package com.ziyata.absenforguru.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.ziyata.absenforguru.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static String getCurrentDateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
        return sdf.format(currentTime);
    }

    public static String getCurrentFrontDateTime() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE,MMMM dd,yyyy", Locale.US);
        return sdf.format(currentTime);
    }

    public static Date getCurrentDate() {
        return new Date();
    }


    public static Date parsingServerDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return sdf.parse(date);
    }

    public static String formatDate(Date createdDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return sdf.format(createdDate);
    }

    public static String formatShortDate(Date createdDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.US);
        return sdf.format(createdDate);
    }

    public static String formatTime(Date createdDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        return sdf.format(createdDate);
    }
    public static String getDateNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    public static String getTimeNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    public static String getTimeServerNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    public static String getDateServerNow(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c);
        return formattedDate;
    }
    public static String compareTime(String getTestTime){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String getCurrentTime = sdf.format(c.getTime());
        //String getTestTime="09:45";

        if (getCurrentTime .compareTo(getTestTime) <= 0) {
            // Do your staff
            Log.d("Return", "getTestTime less than getCurrentTime ");
            return "P";
        }else{
            Log.d("Return","getTestTime older than getCurrentTime ");
            return "T";
        }
    }
    public static String compareTimeOut(String getTestTime){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String getCurrentTime = sdf.format(c.getTime());
        //String getTestTime="09:45";

        if (getCurrentTime .compareTo(getTestTime) < 0) {
            // Do your staff
            Log.d("Return", "getTestTime older than getCurrentTime ");
            return "T";
        }else if (getCurrentTime .compareTo(getTestTime) == 0) {
            // Do your staff
            Log.d("Return", "getTestTime less than getCurrentTime ");
            return "P";
        }else{
            Log.d("Return","getTestTime less than getCurrentTime ");
            return "P";
        }
    }
    public static void dialogDatePickerLight(Context ctx, final EditText et) {
        Calendar cur_calender = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePicker = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        et.setText(Tools.getFormattedDate(date_ship_millis));
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(ctx.getResources().getColor (R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(((Activity) ctx).getFragmentManager(), "Datepickerdialog");
    }
    public static void dialogDatePickerCompare(Context ctx, final EditText et, final EditText etB, final TextView txtDays, final int i, final LinearLayout llEnd) {
        Calendar cur_calender = Calendar.getInstance();
        com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePicker = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        long date_ship_millis = calendar.getTimeInMillis();
                        et.setText(Tools.getFormattedDate(date_ship_millis));
                        if(i==1){
                            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                            Date tglAwal=null;
                            Date tglEnd = null;
                            try {
                                tglAwal = (Date) date.parse(etB.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                tglEnd = (Date) date.parse(et.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long bedaHari = Math.abs(tglEnd.getTime() - tglAwal.getTime());
                            txtDays.setText(String.valueOf(TimeUnit.MILLISECONDS.toDays(bedaHari)));
                        }else{
                            if(!et.getText().equals("")){
                                llEnd.setVisibility(View.VISIBLE);
                            }else{
                                llEnd.setVisibility(View.GONE);
                            }
                        }
                    }
                },
                cur_calender.get(Calendar.YEAR),
                cur_calender.get(Calendar.MONTH),
                cur_calender.get(Calendar.DAY_OF_MONTH)
        );
        if(i==1) {
            DateFormat dateB = new SimpleDateFormat("yyyy-MM-dd");
            Date tglFirst=null;
            try {
                tglFirst = (Date) dateB.parse(etB.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar tgl = dateToCalendar(tglFirst);
            Log.d("tglFirst", String.valueOf(tgl));
            datePicker.setMinDate(tgl);

        }
        //set dark light
        datePicker.setThemeDark(false);
        datePicker.setAccentColor(ctx.getResources().getColor(R.color.colorPrimary));
        datePicker.setMinDate(cur_calender);
        datePicker.show(((Activity) ctx).getFragmentManager(), "Datepickerdialog");
    }
    private static Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    public static String getFormat(String tgl){
        String[] separated = tgl.split("-");
        String dateConv = separated[2] +"-"+separated[1] +"-"+separated[0];
        return dateConv;
    }

}
