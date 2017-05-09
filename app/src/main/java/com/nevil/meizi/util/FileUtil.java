package com.nevil.meizi.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.nevil.meizi.R;
import com.nevil.meizi.network.BaseUrl;
import com.nevil.meizi.network.NetClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Tangkun on 2017/5/9.
 */

public class FileUtil {

    public static void downLoadImage(Context context, String url, boolean isWallpaper) {
        NetClient.getInterface(BaseUrl.TNGOU_IMAGE_ROOT_URL).downLoadImage(url).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(ResponseBody body) {
                try {
                    FileUtil.saveFileByByte(isWallpaper, body.bytes(), context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                T.showShortToast(context, "下载失败：" + throwable.getMessage());
                Log.e("MEIZI", "saveFile: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static void saveFileByByte(boolean sWallpaper, byte[] resource, Context context) {
        if (isExternalStorageWritable()) {
            String savePath = Environment.getExternalStorageDirectory() + "/" + context.getResources().getString(R.string.app_name);
            File file = new File(savePath, System.currentTimeMillis() + ".jpg");
            File dir = file.getParentFile();
            try {
                if (!dir.mkdirs() && (!dir.exists() || !dir.isDirectory())) {
                    throw new IOException("Cannot ensure parent directory for file " + file);
                }
                BufferedOutputStream s = new BufferedOutputStream(new FileOutputStream(file));
                s.write(resource);
                s.flush();
                s.close();
                if (sWallpaper) {
                    setWallPaper(file, context);
                } else {
                    T.showShortToast(context, "保存成功");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("MEIZI", "saveFile: " + e.getMessage());
            }
        } else {
            T.showShortToast(context, "存储不可用");
        }
    }

    private static void setWallPaper(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("mimeType", "image/*");
        try {
            Uri uri = Uri.parse(MediaStore.Images.Media
                    .insertImage(context.getContentResolver(), file.getPath(), null, null));
            intent.setData(uri);
            ((Activity) context).startActivityForResult(intent, 10086);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public File getAlbumStorageDir(String albumName) {
// 获得用户公共的图片目录
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("MEIZI", "Directory not created");
        }
        return file;
    }


    //判断外部存储是否可以读写
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //判断外部存储是否至少可以读
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
