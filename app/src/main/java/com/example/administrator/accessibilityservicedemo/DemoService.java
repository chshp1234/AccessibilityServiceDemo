package com.example.administrator.accessibilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * Created by Admini
 * strator on 2018/3/27.
 */

public class DemoService extends AccessibilityService {
    private static final int FIND_BY_ID = 1;
    private static final int FIND_BY_TEXT = 2;

    /**
     * 二维码菜单按钮ID
     * 微信版本：6.6.3
     */
    public static String MORE = "com.tencent.mm:id/he";

    /**
     * 进度条ID
     */
    public static String PROGRESS = "com.tencent.mm:id/xd";

    /**
     * 主页按钮“我”
     */
    public static String BUTTON_ME = "com.tencent.mm:id/c_z";

    /**
     * 二维码按钮
     */
    public static String BUTTON_QRCODE = "com.tencent.mm:id/cdi";

    /**
     * 朋友圈按钮
     */
    public static String BUTTON_SNS = "com.tencent.mm:id/title";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

//        LogUtils.d(accessibilityEvent.toString());
//
//
//        LogUtils.d(accessibilityEvent.getPackageName());
//        LogUtils.d(accessibilityEvent.getClassName());

        if ("com.tencent.mm".equals(accessibilityEvent.getPackageName())) {
            LogUtils.d("找到微信");
//            if (accessibilityEvent.getContentChangeTypes() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {

            List<AccessibilityNodeInfo> find = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(WeChatWidgetId.BOTTOM_TAB);

            if (find != null && find.size() > 0) {

                LogUtils.d("准备跳转朋友圈");

                nodePerformInfoClick(find.get(2));

                sleep(2000L);

                AccessibilityNodeInfo snsTimeLine = getFirstNodeInfoByText("朋友圈");
                nodePerformInfoClick(snsTimeLine);

                sleep(2000L);

            }

            int time = 5;
            while (time > 0) {
                LogUtils.d("time" + time);
                time--;
                AccessibilityNodeInfo snsTimeLineList = getFirstNodeInfoByViewId(WeChatWidgetId.SNS_LIST);

                if (snsTimeLineList != null) {
                    List<AccessibilityNodeInfo> snsComments = getNodeInfosByViewId(WeChatWidgetId.SNS_COMMENT);
                    if (snsComments != null && snsComments.size() > 0) {
                        for (AccessibilityNodeInfo snsComment : snsComments) {
                            nodePerformInfoClick(snsComment);
                            AccessibilityNodeInfo snsFavour = getFirstNodeInfoByViewId(WeChatWidgetId.SNS_FAVOUR);
                            if (snsFavour != null) {
                                nodePerformInfoClick(snsFavour);
                            }
                        }
                    }
                    snsTimeLineList.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                }
            }
        }

//        }
       /* LogUtils.d("getPackageName:" + accessibilityEvent.getPackageName()
                + "\n" + accessibilityEvent.getClassName()
                + "\n" + "getAction:" + accessibilityEvent.getAction()
                + "\n" + "getContentChangeTypes:" + matchContentType(accessibilityEvent.getContentChangeTypes())
                + "\n" + "getEventType:" + matchEventType(accessibilityEvent.getEventType())
                + "\n" + "getEventTime:" + accessibilityEvent.getEventTime()
                + "\n" + "getMovementGranularity:" + accessibilityEvent.getMovementGranularity()
                + "\n" + "getRecordCount:" + accessibilityEvent.getRecordCount());*/


    }

    private String matchEventType(int type) {
        String event = "";
        switch (type) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                event = "TYPE_VIEW_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                event = "TYPE_VIEW_LONG_CLICKED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                event = "TYPE_VIEW_SELECTED";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                event = "TYPE_VIEW_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                event = "TYPE_VIEW_TEXT_CHANGED";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                event = "TYPE_WINDOW_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                event = "TYPE_NOTIFICATION_STATE_CHANGED";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                event = "TYPE_VIEW_HOVER_ENTER";
                break;
            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                event = "TYPE_VIEW_HOVER_EXIT";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                event = "TYPE_TOUCH_EXPLORATION_GESTURE_START";
                break;
            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                event = "TYPE_TOUCH_EXPLORATION_GESTURE_END";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                event = "TYPE_WINDOW_CONTENT_CHANGED";
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                event = "TYPE_VIEW_SCROLLED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                event = "TYPE_VIEW_TEXT_SELECTION_CHANGED";
                break;
            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                event = "TYPE_ANNOUNCEMENT";
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                event = "TYPE_VIEW_ACCESSIBILITY_FOCUSED";
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                event = "TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                event = "TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY";
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                event = "TYPE_GESTURE_DETECTION_START";
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                event = "TYPE_GESTURE_DETECTION_END";
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                event = "TYPE_TOUCH_INTERACTION_START";
                break;
            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                event = "TYPE_TOUCH_INTERACTION_END";
                break;
            case AccessibilityEvent.TYPE_WINDOWS_CHANGED:
                event = "TYPE_WINDOWS_CHANGED";
                break;
            case AccessibilityEvent.TYPE_VIEW_CONTEXT_CLICKED:
                event = "TYPE_VIEW_CONTEXT_CLICKED";
                break;
            case AccessibilityEvent.TYPE_ASSIST_READING_CONTEXT:
                event = "TYPE_ASSIST_READING_CONTEXT";
                break;
            default:
                event = String.valueOf(type);
                break;
        }
        return event;
    }

    private String matchContentType(int type) {
        String content = "";
        switch (type) {
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED:
                content = "CONTENT_CHANGE_TYPE_UNDEFINED";
                break;
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_SUBTREE:
                content = "CONTENT_CHANGE_TYPE_SUBTREE";
                break;
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_TEXT:
                content = "CONTENT_CHANGE_TYPE_TEXT";
                break;
            case AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION:
                content = "CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION";
                break;
            default:
                content = String.valueOf(type);
                break;

        }
        return content;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void openDelayByText(int delay, final int state, final String name) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openNextByText(state, name);
            }
        }, delay);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void openNextByText(int state, String name) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            Log.e("e", "rootWindow为空");
            Toast.makeText(this, "rootWindow为空", Toast.LENGTH_SHORT).show();
            return;
        }
        List<AccessibilityNodeInfo> nodeInfoList = null;
        switch (state) {
            case FIND_BY_ID:
                nodeInfoList = nodeInfo.findAccessibilityNodeInfosByViewId(name);
                break;
            case FIND_BY_TEXT:
                nodeInfoList = nodeInfo.findAccessibilityNodeInfosByText(name);
                break;
            default:
                break;
        }

        if (nodeInfoList != null && nodeInfoList.size() > 0) {
//            nodeInfoList.get(nodeInfoList.size() - 1).setClickable(true);
            Log.d("name", "匹配个数：" + nodeInfoList.size());
            for (int i = 0, len = nodeInfoList.size(); i < len; i++) {
                Log.d(name, "节点为：" + nodeInfoList.get(i).getClassName());
            }

            nodeInfoList.get(nodeInfoList.size() - 1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
            nodeInfoList.get(nodeInfoList.size() - 1).getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            Toast.makeText(this, "找不到有效的节点", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onInterrupt() {

    }

    protected void sleep(long times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
            LogUtils.i("sleep: error - " + e.getMessage());
        }
    }

    protected AccessibilityNodeInfo getFirstNodeInfoByViewId(String viewId) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) {
            sleep(1000L);
            getFocus();
            sleep(1000L);
            rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                LogUtils.e("getFirstNodeInfoByText :  getRootNode is null ,viewId :" + viewId);
                return null;
            }
        }
        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByViewId(viewId);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    protected AccessibilityNodeInfo getFirstNodeInfoByText(String name) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) {
            sleep(1000L);
            getFocus();
            sleep(1000L);
            rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                LogUtils.e("getFirstNodeInfoByText :  getRootNode is null ,name :" + name);
                return null;
            }
        }
        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByText(name);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    protected List<AccessibilityNodeInfo> getNodeInfosByViewId(String viewId) {
        if (getRootInActiveWindow() == null) {
            LogUtils.e("getFirstNodeInfoByViewId: getRootNode is null ,viewId:" + viewId);
            return null;
        }
        return getRootInActiveWindow().findAccessibilityNodeInfosByViewId(viewId);
    }

    protected List<AccessibilityNodeInfo> getNodeInfosByText(String text) {
        if (getRootInActiveWindow() == null) {
            LogUtils.e("getFirstNodeInfoByText :  getRootNode is null ,name :" + text);
            return null;
        }
        return getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
    }

    /**
     * 打开最近列表并点击返回，使当前窗口获得焦点
     */
    public void getFocus() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        sleep(2000L);
        returnBackByAccessibly();
        sleep(2000L);
    }

    /**
     * 点击“返回”按键
     */
    public boolean returnBackByAccessibly() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    protected boolean nodePerformInfoClick(AccessibilityNodeInfo nodeInfo) {
        while (nodeInfo != null && !nodeInfo.isClickable()) {
            nodeInfo = nodeInfo.getParent();
        }
        if (nodeInfo != null) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            return true;
        }
        return false;
    }

    protected void setEditText(AccessibilityNodeInfo editNodeInfo, String text) {
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        editNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
    }

    /**
     * <b>能否返回微信主页</b>
     * <p>1.判断是否有弹窗（比如朋友圈点返回时）</p>
     * <p>2.判断左上角是否有“回退”按钮（2种ID）</p>
     * <p>3.判断当前节点是否有主页的4个导航按钮（主页有）</p>
     * <p>4.直接点“回退”键（查看图片界面）</p>
     */
    public boolean returnMain() {
        int time = 0;
        while (time < 15) {
            time++;
            if (getRootInActiveWindow() == null) {
                getFocus();
            }
//            AccessibilityNodeInfo quit = getFirstNodeInfoByViewId(WeChatWidgetId.BUTTON_POSITIVE);
            AccessibilityNodeInfo returnNormal = getFirstNodeInfoByViewId(WeChatWidgetId.RETURN);
            AccessibilityNodeInfo returnSpecial = getFirstNodeInfoByViewId(WeChatWidgetId.RETURN_SPECIAL);
            List<AccessibilityNodeInfo> bottomTab = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(WeChatWidgetId.BOTTOM_TAB);
            /*if (quit != null) {
                nodePerformInfoClick(quit);
                sleep(2000L);
            } else */
            if (returnNormal != null) {
                nodePerformInfoClick(returnNormal);
                sleep(2000L);
            } else if (returnSpecial != null) {
                nodePerformInfoClick(returnSpecial);
                sleep(2000L);
            } else if (bottomTab != null && bottomTab.size() > 0) {
                LogUtils.d("find wechat main,time=" + time);
                sleep(2000L);
                return true;
            } else {
                returnBackByAccessibly();
                sleep(2000L);
            }
        }
        LogUtils.e("could not find wechat main,time=" + time);
        return false;
    }
}
