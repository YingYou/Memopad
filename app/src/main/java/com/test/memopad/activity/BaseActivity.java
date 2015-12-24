package com.test.memopad.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.test.memopad.R;

public class BaseActivity extends FragmentActivity {

    private ActionBar actionBar = null;
//    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
    }

    public void initHomeActionBar() {

//        getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);
        actionBar.setTitle("备忘录");

        actionBar.setCustomView(R.layout.custom_action_bar);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    public void initNewMemoPadActionBar() {

//        mContext = activity;
//        getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);
        actionBar.setTitle("");

        actionBar.setCustomView(R.layout.new_memopad_action_bar);

        actionBar.getCustomView().findViewById(R.id.new_memopad_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actionBar.setDisplayShowHomeEnabled(false);
    }

    public void initPaintViewActionBar() {
        actionBar.setTitle("");

        actionBar.setCustomView(R.layout.paint_view_action_bar);


        actionBar.getCustomView().findViewById(R.id.new_memopad_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actionBar.setDisplayShowHomeEnabled(false);
    }
}
