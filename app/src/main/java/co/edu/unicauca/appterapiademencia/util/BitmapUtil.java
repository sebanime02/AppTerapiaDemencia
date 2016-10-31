package co.edu.unicauca.appterapiademencia.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ASUS_01 on 02/07/2015.
 */
public class BitmapUtil {

    public static final int CAM=0;
    public static final int GALERY=1;

    public static String resizeImageFile(String path, int minDim, int origin) throws IOException {

        String p = path;

        if(origin==GALERY){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d_H-m-s", Locale.getDefault());
            p = AppUtil.DIR_IMG+format.format(Calendar.getInstance().getTime())+".jpg";
            File destino =  new File(p);
            File ori=new File(path);
            copy(ori,destino);
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d_H-m-s", Locale.getDefault());
            p = AppUtil.DIR_IMG+format.format(Calendar.getInstance().getTime())+".jpg";

        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds=true;

        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options,minDim);
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(path, options);


        float w,h;

        if(bitmap.getWidth()>bitmap.getHeight()){
            h = bitmap.getHeight()*minDim/bitmap.getWidth();
            w = minDim;
        }else{
            w = bitmap.getWidth()*minDim/bitmap.getHeight();
            h=minDim;

        }

        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, (int)w,(int)h,true);



        File f=null;
        //if(origin == CAM) {

               f = new File(p);

        /*}else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d_H-m-s", Locale.getDefault());

            p = AppUtil.DIR_IMG+format.format(Calendar.getInstance().getTime());
            f =  new File(p);
        }*/

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
          newBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        try {

                f.createNewFile();
                FileOutputStream fO = new FileOutputStream(f);
                fO.write(bytes.toByteArray());
                fO.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        bitmap.recycle();
        bitmap = null;
            newBitmap.recycle();
            newBitmap = null;
        if(origin==CAM){
            File anterior=new File(path);
            anterior.delete();
        }
        return p;
    }

    static public int calculateInSampleSize(BitmapFactory.Options options, int reqSize){

        final int w = options.outWidth;
        final int h = options.outHeight;
        int inSampleSize=1;

        if(h>reqSize || w>reqSize){
            final int halfH = h/2;
            final int halfW = w/2;

            while((halfH/inSampleSize)>reqSize && (halfW/inSampleSize)>reqSize){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

}
