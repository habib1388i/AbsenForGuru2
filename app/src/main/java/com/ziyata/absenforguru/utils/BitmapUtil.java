package com.ziyata.absenforguru.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class BitmapUtil {
    public static Bitmap mark(Bitmap src, Double latitude, Double longitude, String name) { // sebelumnya gambar harus dirubah ke format bitmap agar bisa di tambahkan watermark

        int w = src.getWidth(); // ini fungsinya ngambil lebar gambar
        int h = src.getHeight(); // ini fungsinya ngambil tinggi gambar
        //pw berfungsi untuk menentuka posisi titik X pada gambar sedangkan ph untuk menentuka posisi titik Y
        int pw = w - ((w / 100) * 95);
        int ph = h - ((h / 100) * 17);
        int pw2 = w - ((w / 100) * 95);
        int ph2 = h - ((h / 100 * 14));
        int pw3 = w - ((w / 100) * 95);
        int ph3 = h - ((h / 100 * 11));
        int pw4 = w - ((w / 100) * 45);
        int ph4 = h - ((h / 100 * 17));

        Bitmap result = Bitmap.createBitmap(w, h, src.getConfig()); //membuat salinan bitmap baru
        Canvas canvas = new Canvas(result); //object untuk membuat watermark

        // disini settingan untuk membuat watermark
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE); // warna watermark

        int texsize = (h < w) ? h : w; //menentukan size watermark
        paint.setTextSize((texsize / 100) * 3); //men-set watermark
        paint.setUnderlineText(false);
        canvas.drawText("latitude: " + latitude, pw, ph, paint); //disini untuk menentukan watermark yang mau dibuat, nilai methodnya yaitu ( text yang mau dibuat, posisi x, posisi y, paint
        canvas.drawText("longitude: " + longitude, pw2, ph2, paint);
        canvas.drawText("nama: " + name, pw3, ph3, paint);
        canvas.drawText("tanggal: " + DateUtil.getCurrentDateTime(), pw4, ph4, paint);

        return result; //kemudian setelah itu gambar sudah selesai ditambahkan watermark
    }

    public static Bitmap convert(String base64Str) throws IllegalArgumentException {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",") + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap revert(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public static byte[] convertToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static String getByteArrayFromImageURL(String url) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            InputStream is = ucon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        } catch (Exception e) {
            Log.d("Error", e.toString());
        }
        return null;
    }

    public static File storeImageTosdCard(Bitmap processedBitmap) {
        return storeImageTosdCard(processedBitmap, "pengawas-" + System.currentTimeMillis() + Math.random());
    }

    public static File storeImageTosdCard(Bitmap processedBitmap, String fileName) {
        try {
            // TODO Auto-generated method stub

            OutputStream output;
            // Find the SD Card path
            File filepath = Environment.getExternalStorageDirectory();
            // Create a new folder in SD Card
            File dir = new File(filepath.getAbsolutePath() + "/pengawas/");
            dir.mkdirs();

            String imge_name = fileName
                    + ".jpg";
            // Create a name for the saved image
            File file = new File(dir, imge_name);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            } else {
                file.createNewFile();

            }

            try {

                output = new FileOutputStream(file);

                // Compress into png format image from 0% - 100%
                processedBitmap
                        .compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();

                int file_size = Integer
                        .parseInt(String.valueOf(file.length() / 1024));
                System.out.println("size ===>>> " + file_size);
                System.out.println("file.length() ===>>> " + file.length());

                return file;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
