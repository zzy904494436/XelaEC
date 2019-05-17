package com.qindadai.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by mymac on 2019/1/9.
 */

public enum EcIcons implements Icon{
    icon_scan('\ue606')
    ,icon_ali('\ue606');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_' , '-');
    }

    @Override
    public char character() {
        return character;
    }
}
