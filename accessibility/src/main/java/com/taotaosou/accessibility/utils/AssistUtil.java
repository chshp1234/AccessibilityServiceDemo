package com.taotaosou.accessibility.utils;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.elvishew.xlog.XLog;
import com.taotaosou.accessibility.viewid.WeChatWidgetId;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

public class AssistUtil {
    public static AccessibilityService assistService;
    public static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage: Thread is " + Thread.currentThread().getName());
            super.handleMessage(msg);
        }
    };
    private static final String TAG = "AssistUtil";
    private static long ONE_SECOND = 1000L;

    public static AccessibilityNodeInfo getRootNode() {
        if (assistService != null) {
            return assistService.getRootInActiveWindow();
        }
        return null;
    }


    public static boolean nodePerformInfoClick(AccessibilityNodeInfo nodeInfo) {
        while (nodeInfo != null && !nodeInfo.isClickable()) {
            nodeInfo = nodeInfo.getParent();
        }
        if (nodeInfo != null) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            return true;
        }
        return false;
    }

    public static void setEditText(AccessibilityNodeInfo editNodeInfo, String text) {
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        editNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
    }

    public static AccessibilityNodeInfo getFirstNodeInfoByViewId(String viewId) {
        AccessibilityNodeInfo rootNode = getRootNode();
        if (rootNode == null) {
            sleep(ONE_SECOND);
            getFocus();
            sleep(ONE_SECOND);
            rootNode = getRootNode();
            if (rootNode == null) {
                XLog.tag(TAG).e("getFirstNodeInfoByText :  getRootNode is null ,viewId :" + viewId);
                return null;
            }
        }
        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByViewId(viewId);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    public static AccessibilityNodeInfo getFirstNodeInfoByText(String name) {
        AccessibilityNodeInfo rootNode = getRootNode();
        if (rootNode == null) {
            sleep(ONE_SECOND);
            getFocus();
            sleep(ONE_SECOND);
            rootNode = getRootNode();
            if (rootNode == null) {
                XLog.tag(TAG).e("getFirstNodeInfoByText :  getRootNode is null ,name :" + name);
                return null;
            }
        }
        List<AccessibilityNodeInfo> nodeInfos = rootNode.findAccessibilityNodeInfosByText(name);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    public static AccessibilityNodeInfo getFirstNodeInfoByViewId(String viewId, AccessibilityNodeInfo parent) {
        List<AccessibilityNodeInfo> nodeInfos = parent.findAccessibilityNodeInfosByViewId(viewId);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    public static AccessibilityNodeInfo getFirstNodeInfoByText(String name, AccessibilityNodeInfo parent) {
        List<AccessibilityNodeInfo> nodeInfos = parent.findAccessibilityNodeInfosByText(name);
        return nodeInfos.size() > 0 ? nodeInfos.get(0) : null;
    }

    public static List<AccessibilityNodeInfo> getNodeInfosByViewId(String viewId) {
        if (getRootNode() == null) {
            XLog.tag(TAG).i("getFirstNodeInfoByViewId: getRootNode is null ,viewId:" + viewId);
            return null;
        }
        return getRootNode().findAccessibilityNodeInfosByViewId(viewId);
    }

    public static List<AccessibilityNodeInfo> getNodeInfosByText(String text) {
        if (getRootNode() == null) {
            XLog.tag(TAG).e("getFirstNodeInfoByText :  getRootNode is null ,name :" + text);
            return null;
        }
        return getRootNode().findAccessibilityNodeInfosByText(text);
    }

    public static boolean returnBackByAccessibly() {
        return assistService != null && assistService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    /**
     * 跳转到目标Activity
     *
     * @param pkg The name of the package that the component exists in.  Can
     *            not be null
     * @param cls The name of the class inside of <var>pkg</var> that
     *            implements the component.  Can not be null
     */
    public static void gotoTargetActivity(String pkg, String cls) {
        gotoTargetActivity(null, null, pkg, cls);
    }

    /**
     * 跳转到目标Activity
     *
     * @param action   An action name, such as ACTION_VIEW.  Application-specific
     *                 actions should be prefixed with the vendor's package name
     * @param category The desired category.  This can be either one of the
     *                 predefined Intent categories, or a custom category in your own
     *                 namespace
     * @param pkg      The name of the package that the component exists in.  Can
     *                 not be null
     * @param cls      The name of the class inside of <var>pkg</var> that
     *                 implements the component.  Can not be null
     */
    public static void gotoTargetActivity(String action, String category, String pkg, String cls) {
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName(pkg, cls);
        if (action != null) {
            intent.setAction(action);
        }
        if (category != null) {
            intent.addCategory(category);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        assistService.startActivity(intent);
    }

    /**
     * 获取焦点操作
     */
    public static void getFocus() {
        assistService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
        sleep(ONE_SECOND << 1);
        returnBackByAccessibly();
        sleep(ONE_SECOND << 1);
    }

    public static void sleep(long milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
