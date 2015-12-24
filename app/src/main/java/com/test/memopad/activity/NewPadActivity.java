package com.test.memopad.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.test.memopad.MemoPadApplication;
import com.test.memopad.R;
import com.test.memopad.utils.Utils;
import com.test.memopad.view.SelectAaPopupWindow;


import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewPadActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    private static final int SELECT_PICTURE = 1;

//    @ViewInject(R.id.edit_memopad)
//    EditText editText;

    @ViewInject(R.id.show_tool_btn)
    ImageView show_tool_btn;
    @ViewInject(R.id.lineay_tool)
    LinearLayout lineay_tool;
    @ViewInject(R.id.aa)
    TextView fontText;
    @ViewInject(R.id.xm)
    ImageView xm_iv;
    @ViewInject(R.id.xj)
    ImageView phone_iv;
    @ViewInject(R.id.xq)
    ImageView thickness_iv;
    @ViewInject(R.id.iconfont_close)
    ImageView iconfont_close;

    @ViewInject(R.id.lineary_content)
    ScrollView lineary_content;

    @ViewInject(R.id.child_lineary)
    LinearLayout child_lineary;

    private EditText defaultEdit;
    private ImageView image;

    private int num = 0;
    private int pop_num = 0;


    private SelectAaPopupWindow menuWindow;

    private EditText tempEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pad);
        ViewUtils.inject(this);

        initNewMemoPadActionBar();
//        editText.setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//        InputMethodManager inputManager =
//                (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputManager.showSoftInput(editText, 0);
        show_tool_btn.setOnClickListener(this);
        xm_iv.setOnClickListener(this);
        fontText.setOnClickListener(this);
        phone_iv.setOnClickListener(this);
        thickness_iv.setOnClickListener(this);
        iconfont_close.setOnClickListener(this);


//        LinearLayout defaultLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//        defaultLayout.setLayoutParams(params);
//        child_lineary.addView(defaultLayout, 0);

        defaultEdit = new EditText(this);
//        defaultEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT, 1));
//        defaultEdit.setGravity(Gravity.TOP);
        defaultEdit.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        defaultEdit.setTextAppearance(this, R.style.edittext_style);
//        if(!TextUtils.isEmpty(defaultEdit.getText().toString()))
//        defaultEdit.setSelection(defaultEdit.getText().toString().length());
//        defaultEdit.setText("nihao a   cdsjdojpdsfgfhgj");
//        defaultEdit.setCursorVisible(true);
        defaultEdit.setHint("写点啥呢");
//        defaultEdit.setFocusable(false);
//        defaultEdit.setFocusableInTouchMode(true);
        defaultEdit.addTextChangedListener(this);
        defaultEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    tempEditText = (EditText) view;
                Log.d("TAG", "onFocusChange----tempEditText:" + tempEditText.getText().toString() + "---b:" + b);
            }
        });
        child_lineary.addView(defaultEdit);

        image = new ImageView(this);
        image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//        image.setBackgroundDrawable(getResources().getDrawable(R.drawable.iconfont_pen));
        child_lineary.addView(image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_tool_btn:
                show_tool_btn.setVisibility(View.GONE);
                lineay_tool.setVisibility(View.VISIBLE);
                break;

            case R.id.aa:  //文本格式
                //Aa
                Log.d("TAG", "===aa===");
                Utils.hideSoftWindowPan(NewPadActivity.this);
                menuWindow = new SelectAaPopupWindow(NewPadActivity.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;

            case R.id.xq:        //画板
                Log.d("TAG", "===xq===");
                startActivity(new Intent(NewPadActivity.this,PaintViewActivity.class));
                break;

            case R.id.xj:        //选择图片
                Log.d("TAG", "===xj===");
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                break;

            case R.id.xm:                  //是否显现选择框
                Log.d("TAG", "===xm===");//1
                Drawable img_off = this.getResources().getDrawable(R.drawable.iconfont_selectfill);
                //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
                img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
                String textStr = tempEditText.getText().toString();

                String regExp = "^[0-9]*$";
                Pattern p = Pattern.compile(regExp);
                Matcher m = p.matcher(textStr);
                if (textStr.contains("●")) {
                    Log.d("TAG", "========●========");
                    tempEditText.setText(textStr.replace("●", ""));
                } else if (textStr.contains("-")) {
                    textStr.replace("-", "");
                    tempEditText.setText(textStr.replace("-", ""));
                    Log.d("TAG", "========-========");
                } else if (m.find()) {
                    Log.d("TAG", "========123456========");
                }
                tempEditText.setCompoundDrawables(img_off, null, null, null);
                tempEditText.setSelection(tempEditText.getText().length());
                break;

            case R.id.iconfont_close:
                show_tool_btn.setVisibility(View.VISIBLE);
                lineay_tool.setVisibility(View.GONE);
                break;

            default:
                break;
        }

    }

    /*public void setmBitmap(Uri uri){
        Log.e("图片路径", String.valueOf(uri));
        ContentResolver cr = context.getContentResolver();
        try {
            mBitmapInit = BitmapFactory.decodeStream(cr.openInputStream(uri));
            drawBitmapToCanvas(mBitmapInit);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        invalidate();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ImageLoader.getInstance().displayImage(String.valueOf(data.getData()), image, MemoPadApplication.options);
//            ContentResolver cr = getContentResolver();
//            Bitmap mBitmapInit = null;
//            try {
//                mBitmapInit = BitmapFactory.decodeStream(cr.openInputStream(data.getData()));
////                drawBitmapToCanvas(mBitmapInit);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            image.setImageBitmap(mBitmapInit);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d("TAG", "event===" + event.getKeyCode());
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            /*隐藏软键盘*/
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            if(inputMethodManager.isActive()){
//                inputMethodManager.hideSoftInputFromWindow(NewPadActivity.this.getCurrentFocus().getWindowToken(), 0);
//            }
//        Utils.hideSoftWindowPan(this);
            createEdittext();

            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    public void createEdittext() {
        num++;
        pop_num++;

        LinearLayout tempLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        tempLayout.setLayoutParams(params);
        child_lineary.addView(tempLayout, num);

        EditText tempEdit = new EditText(this);
//        defaultEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT, 1));
//        defaultEdit.setGravity(Gravity.TOP);
        tempEdit.setBackgroundColor(this.getResources().getColor(R.color.transparent));
        tempEdit.setTextAppearance(this, R.style.edittext_style);
        tempEdit.setCursorVisible(true);
        tempEdit.setHint("写点啥呢" + num);
        tempLayout.addView(tempEdit);
//        tempEdit.setFocusable(false);
//        tempEdit.setFocusableInTouchMode(true);
//        tempEdit.requestFocus();
        tempEdit.addTextChangedListener(this);

        tempEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b)
                    tempEditText = (EditText) view;
                Log.d("TAG", "onFocusChange----b:" + b + "----view:" + tempEditText.getText().toString());
            }
        });


        for (int i = 0; i <= 3; i++) {
            Log.d("TAG", "====drable---:" + tempEditText.getCompoundDrawables()[i]);
        }

        if (tempEditText.getCompoundDrawables() != null && tempEditText.getCompoundDrawables()[0] != null) {

            Drawable img_off = this.getResources().getDrawable(R.drawable.iconfont_selectfill);
            //调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
            img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
            tempEdit.setCompoundDrawables(img_off, null, null, null);
        } else if (tempEditText.getText().toString() != null &&
                tempEditText.getText().toString().contains("●")) {
            tempEdit.setText("●");
        } else if (tempEditText.getText().toString() != null &&
                tempEditText.getText().toString().contains("-")) {
            tempEdit.setText("-");

        }

        tempEdit.setSelection(tempEdit.getText().length());

    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.big_title:

                    tempEditText.setTextSize(25);

                    break;

                case R.id.small_title:

                    tempEditText.setTextSize(18);
                    break;

                case R.id.content:
                    tempEditText.setTextSize(16);

                    break;

                case R.id.xhx_list:
                    Log.d("TAG", "======xhx_list====");
                    tempEditText.setCompoundDrawables(null, null, null, null);
                    String textStr = tempEditText.getText().toString();
                    if (textStr.contains("●")) {
                        Log.d("TAG", "========●========");
                        tempEditText.setText(textStr.replace("●", ""));
                    } else if (textStr.contains("-")) {
                        textStr.replace("-", "");
                        tempEditText.setText(textStr.replace("-", ""));
                        Log.d("TAG", "========-========");
                    }
                    tempEditText.setText("-" + tempEditText.getText());
                    tempEditText.setSelection(tempEditText.getText().length());
                    break;

                case R.id.project_list:
                    tempEditText.setCompoundDrawables(null, null, null, null);
                    String textStr1 = tempEditText.getText().toString();
                    if (textStr1.contains("●")) {
                        Log.d("TAG", "========●========textStr1:" + textStr1);
                        tempEditText.setText(textStr1.replace("●", ""));
                    } else if (textStr1.contains("-")) {
                        textStr1.replace("-", "");
                        tempEditText.setText(textStr1.replace("-", ""));
                        Log.d("TAG", "========-========");
                    }
                    tempEditText.setText("●" + tempEditText.getText());
                    tempEditText.setSelection(tempEditText.getText().length());
                    break;

                case R.id.num_list:
                    pop_num = 0;
                    tempEditText.setCompoundDrawables(null, null, null, null);
                    tempEditText.setText(String.valueOf(num + 1) + "." + tempEditText.getText());
                    tempEditText.setSelection(tempEditText.getText().length());
                    break;
            }
        }

    };

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
