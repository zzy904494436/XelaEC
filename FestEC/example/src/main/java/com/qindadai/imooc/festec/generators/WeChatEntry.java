package com.qindadai.imooc.festec.generators;

import com.qindadai.latte.annotations.EntryGenerator;
import com.qindadai.latte.wechat.templates.WXEntryTemplate;

/***
  Created by mymac on 2019-05-24.
  func:
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.qindadai.imooc.festec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}

