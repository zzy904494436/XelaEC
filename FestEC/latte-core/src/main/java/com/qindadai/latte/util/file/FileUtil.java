package com.qindadai.latte.util.file;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import com.qindadai.latte.app.Latte;
import com.qindadai.latte.net.callback.IFailure;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.internal.schedulers.IoScheduler;

/**
 * Created by mymac on 2019/5/16.
 * func:
 */
public class FileUtil {

    public static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();


    public static File writeToDisk(InputStream is, String downloadDir, String prefix, String extension) {
        File file = FileUtil.createFileByTime(downloadDir, prefix, extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }
            bos.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private static File createFileByTime(String sdcardDirName, String timeformatHeader, String extension) {
        String fileName = getFileNameByTime(timeformatHeader, extension);
        return createFile(sdcardDirName, fileName);
    }

    private static String getFileNameByTime(String timeformatHeader, String extension) {
        return getTimeFormatName(timeformatHeader) + "." + extension;
    }

    private static File createFile(String sdcardDirName, String fileName) {
        return new File(createDir(sdcardDirName), fileName);
    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File createDir(String sdcardDirName) {
        String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        File fileDir = new File(dir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    private static String getTimeFormatName(String timeformatHeader) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeformatHeader + "'" + TIME_FORMAT);
        return dateFormat.format(date);
    }

    private static void refreshDCIM() {
        if (Build.VERSION.SDK_INT >= 19) {
            MediaScannerConnection.scanFile(Latte.getApplication(),
                    new String[]{Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()}
                    , null, null);
        } else {
            Latte.getApplication().sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(
                    Environment.getExternalStorageDirectory())));
        }
    }

    public static File writeToDisk(InputStream is, String downloadDir, String name) {
        File file = FileUtil.createFile(downloadDir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String getExtension(String filePath) {
        String suffix = "";
        File file = new File(filePath);
        String name = file.getName();
        final int idx = name.lastIndexOf(".");
        if (idx > 0) {
            suffix = name.substring(idx + 1);
        }
        return suffix;
    }

    public static String getRawFile(int id) {
        InputStream is = Latte.getApplication().getResources().openRawResource(id);
        BufferedInputStream bis = new BufferedInputStream(is);
        InputStreamReader isr = new InputStreamReader(bis);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder sb = new StringBuilder();
        String str;

        try {
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
