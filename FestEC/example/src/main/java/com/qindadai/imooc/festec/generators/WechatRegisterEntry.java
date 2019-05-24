package com.qindadai.imooc.festec.generators;

import com.qindadai.latte.annotations.AppRegisterGenerator;
import com.qindadai.latte.wechat.templates.WXResgisterEntryTemplate;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
@AppRegisterGenerator(
        packageName = "com.qindadai.imooc.festec",
        registerTemplete = WXResgisterEntryTemplate.class
)
public interface WechatRegisterEntry {

}
