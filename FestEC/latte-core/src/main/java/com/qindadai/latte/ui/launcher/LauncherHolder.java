package com.qindadai.latte.ui.launcher;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by mymac on 2019-05-22.
 * func:
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new AppCompatImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setBackgroundResource(data);
    }
}
