package com.test.memopad.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;


public class Utils {



    public static void hideSoftWindowPan(Activity context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // �õ�InputMethodManager��ʵ��

        if (imm != null && imm.isActive()) {

            // �����

            if (context.getCurrentFocus() != null) {
                // ���������
                imm.hideSoftInputFromWindow(context.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
