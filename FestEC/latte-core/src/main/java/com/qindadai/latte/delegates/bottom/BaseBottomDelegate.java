package com.qindadai.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.joanzapata.iconify.widget.IconTextView;
import com.qindadai.latte.R;
import com.qindadai.latte.R2;
import com.qindadai.latte.delegates.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;

    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar_base)
    LinearLayoutCompat bottomBar;
    @BindView(R2.id.container_base)
    FrameLayout container;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_base_bottom;

    }

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.item_bottom_layout, bottomBar);
            final RelativeLayout item  = (RelativeLayout) bottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itv = (IconTextView) item.getChildAt(0);
            final AppCompatTextView atv = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);

            //初始化
            itv.setText(bean.getICON());
            atv.setText(bean.getTITLE());

            if (i == mIndexDelegate){
                itv.setTextColor(mClickedColor);
                atv.setTextColor(mClickedColor);
            }
            final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
            loadMultipleRootFragment(R.id.container_base,mIndexDelegate,delegateArray);
        }

    }

    private void resetColor(){
        final int count = bottomBar.getChildCount();

        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            final IconTextView itv = (IconTextView) item.getChildAt(0);
            itv.setTextColor(Color.GRAY);
            final AppCompatTextView atv = (AppCompatTextView) item.getChildAt(1);
            atv.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itv = (IconTextView) item.getChildAt(0);
        final AppCompatTextView atv = (AppCompatTextView) item.getChildAt(1);
        itv.setTextColor(mClickedColor);
        atv.setTextColor(mClickedColor);
        showHideFragment(ITEM_DELEGATES.get(tag) , ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
