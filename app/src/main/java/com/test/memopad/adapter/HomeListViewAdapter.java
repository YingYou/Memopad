package com.test.memopad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.memopad.R;


public class HomeListViewAdapter extends BaseAdapter {

    private Context context;
    private ViewHolder mViewHolder;


    public HomeListViewAdapter(Context context) {
        super();
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public void setData() {

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return 15;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        mViewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.adapter_list_home, null);
            mViewHolder.adapter_home_title = (TextView) convertView
                    .findViewById(R.id.adapter_home_title);
            mViewHolder.adapter_home_date = (TextView) convertView
                    .findViewById(R.id.adapter_home_date);

            mViewHolder.adapter_home_conent = (TextView) convertView.findViewById(R.id.adapter_home_details);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public class ViewHolder {
        TextView adapter_home_title;
        TextView adapter_home_date;
        TextView adapter_home_conent;
    }
}
