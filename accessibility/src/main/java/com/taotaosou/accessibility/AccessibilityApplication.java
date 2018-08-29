package com.taotaosou.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;

import com.taotaosou.accessibility.utils.AssistUtil;
import com.taotaosou.accessibility.viewid.WeChatWidgetId;


/**
 * Created by Administrator on 2018/8/9.
 */

public class AccessibilityApplication {

    public static void init(Context appContext, String wechatVersion) {
        AssistUtil.assistService = (AccessibilityService) appContext;
        WeChatWidgetId.WeChatIdConfig.initConfig(wechatVersion);
    }

}
