package com.qindadai.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by mymac on 2019/1/9.
 */

public class FontEcModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "syfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
