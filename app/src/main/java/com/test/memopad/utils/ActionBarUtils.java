package com.test.memopad.utils;

/**
 * Created by yw on 15/9/25.
 */
public class ActionBarUtils {

    private static ActionBarUtils mActionBarUtils = null;

    public static ActionBarUtils getInstance(){
        if (mActionBarUtils == null){
            mActionBarUtils = new ActionBarUtils();
        }
        return mActionBarUtils;
    }



}
