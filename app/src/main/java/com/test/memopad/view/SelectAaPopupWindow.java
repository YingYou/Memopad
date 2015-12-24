package com.test.memopad.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.test.memopad.R;



public class SelectAaPopupWindow extends PopupWindow {


    private Button btn_big_title, btn_small_title, btn_content,btn_project_list,btn_dhx_list,btn_num_list;
    private View mMenuView;

    public SelectAaPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_window_layout, null);
        btn_big_title = (Button) mMenuView.findViewById(R.id.big_title);
        btn_small_title = (Button) mMenuView.findViewById(R.id.small_title);
        btn_content = (Button) mMenuView.findViewById(R.id.content);
        btn_project_list = (Button)mMenuView.findViewById(R.id.project_list);
        btn_dhx_list = (Button)mMenuView.findViewById(R.id.xhx_list);
        btn_num_list = (Button)mMenuView.findViewById(R.id.num_list);
        btn_num_list.setVisibility(View.GONE);

        //设置按钮监听
        btn_big_title.setOnClickListener(itemsOnClick);
        btn_small_title.setOnClickListener(itemsOnClick);
        btn_content.setOnClickListener(itemsOnClick);
        btn_project_list.setOnClickListener(itemsOnClick);
        btn_dhx_list.setOnClickListener(itemsOnClick);
        btn_num_list.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

}