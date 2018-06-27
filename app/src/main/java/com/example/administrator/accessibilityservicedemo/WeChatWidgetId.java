package com.example.administrator.accessibilityservicedemo;

/**
 * 微信各个控件Id
 * Created by Administrator on 2018/4/28.
 */

public class WeChatWidgetId {

    private static String WECHAT_PACKAGE = "com.tencent.mm:id/";

    /**
     * 二维码菜单按钮ID
     * 微信版本：6.6.3
     */
    public static String MORE = WECHAT_PACKAGE + "he";

    /**
     * 进度条ID
     */
    public static String PROGRESS = WECHAT_PACKAGE + "xd";

    /**
     * 主页底部4个按钮
     */
    public static String BOTTOM_TAB = WECHAT_PACKAGE + "c_z";

    /**
     * 返回按钮，大部分
     */
    public static String RETURN = WECHAT_PACKAGE + "hy";

    /**
     * 返回按钮，特例
     */
    public static String RETURN_SPECIAL = WECHAT_PACKAGE + "hi";

    /**
     * 弹窗，(右边按钮，确认)
     */
    public static String BUTTON_POSITIVE = WECHAT_PACKAGE + "all";

    /**
     * 弹窗，(左边按钮，取消)
     */
    public static String BUTTON_NEGATIVE = WECHAT_PACKAGE + "alk";

    /**
     * 分享链接网页，更多按钮
     */
    public static String MORE_FUNCTION = WECHAT_PACKAGE + "he";

    /**
     * “分享到朋友圈”按钮
     */
    public static String SHARE_FRIEND = WECHAT_PACKAGE + "ga";

    /**
     * 朋友圈“发送”按钮
     */
    public static String SEND_BTN = WECHAT_PACKAGE + "hd";

    /**
     * 朋友圈“编辑”按钮
     */
    public static String SHARE_TEXT = WECHAT_PACKAGE + "der";

    /**
     * 发现页面列表
     */
    public static String BUTTON_SNS = "com.tencent.mm:id/title";
}
