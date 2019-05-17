package com.qindadai.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.qindadai.latte.app.Latte;

/**
 * Created by mymac on 2019/5/16.
 * func:
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
