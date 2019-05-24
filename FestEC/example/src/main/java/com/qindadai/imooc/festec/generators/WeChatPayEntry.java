package com.qindadai.imooc.festec.generators;

import com.qindadai.latte.annotations.PayEntryGenerator;
import com.qindadai.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by mymac on 2019-05-24.
 * func:
 */
@PayEntryGenerator(
        packageName = "com.qindadai.imooc.festec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
