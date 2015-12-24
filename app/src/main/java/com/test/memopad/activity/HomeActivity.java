package com.test.memopad.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.test.memopad.R;
import com.test.memopad.adapter.HomeListViewAdapter;
import com.test.memopad.swipe.SwipeMenu;
import com.test.memopad.swipe.SwipeMenuItem;

import com.test.memopad.swipe.SwipeMenuCreator;
import com.test.memopad.swipe.SwipeMenuListView;


public class HomeActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.pull_refresh_list)
    SwipeMenuListView pull_refresh_list;
    @ViewInject(R.id.search_linearly)
    LinearLayout search_linearly;
    @ViewInject(R.id.home_new_iv)
    ImageView home_new_iv;


    private Drawable mActionBarBackgroundDrawable;

    private HomeListViewAdapter homeListViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_actionbar);
        ViewUtils.inject(this);
        home_new_iv.setOnClickListener(this);
//
//        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ad_bg);
//        mActionBarBackgroundDrawable.setAlpha(0);

//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
//        }
        initHomeActionBar();
//        ((NotifyingScrollView) findViewById(R.id.scroll_view)).setOnScrollChangedListener(mOnScrollChangedListener);

        homeListViewAdapter = new HomeListViewAdapter(this);
        pull_refresh_list.setAdapter(homeListViewAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(60));
                // set item title
                openItem.setTitle("置顶");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(60));
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitle("删除");
                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        pull_refresh_list.setMenuCreator(creator);

        pull_refresh_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Log.d("TAG","onMenuItemClick--index:"+index);

                        break;
                    case 1:
                        // delete
                        Log.d("TAG","onMenuItemClick--index:"+index);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        // Right
        pull_refresh_list.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        // set SwipeListener
        pull_refresh_list.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // set MenuStateChangeListener
        pull_refresh_list.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });
    }

//    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
//        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
////            final int headerHeight = findViewById(R.id.srv1).getHeight() - getActionBar().getHeight();
////            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
////            final int newAlpha = (int) (ratio * 255);
//            mActionBarBackgroundDrawable.setAlpha(1);
//        }
//    };
//
//    private Drawable.Callback mDrawableCallback = new Drawable.Callback() {
//        @Override
//        public void invalidateDrawable(Drawable who) {
//            getActionBar().setBackgroundDrawable(who);
//        }
//
//        @Override
//        public void scheduleDrawable(Drawable who, Runnable what, long when) {
//        }
//
//        @Override
//        public void unscheduleDrawable(Drawable who, Runnable what) {
//        }
//    };



    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_new_iv:

                Intent intent = new Intent();
                intent.setClass(HomeActivity.this,NewPadActivity.class);
                startActivity(intent);
                break;
        }
    }
}
