package com.example.administrator.accessibilityservicedemo;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
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

        LogUtils.d(accessibilityEvent.toString());

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
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

    /**
     * 打开最近列表并点击返回，使当前窗口获得焦点
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void getFocus() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        sleep(2000L);
        returnBackByAccessibly();
        sleep(2000L);
    }

    /**
     * 点击“返回”按键
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean returnBackByAccessibly() {
        return performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }
}
