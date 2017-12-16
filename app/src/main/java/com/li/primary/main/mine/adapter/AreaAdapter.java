package com.li.primary.main.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.bean.vo.AreaVO;
import com.li.primary.main.mine.RegisterActivity;

import java.util.List;

/**
 * Created by liu on 2017/6/15.
 */

public class AreaAdapter extends BaseAdapter{
    private RegisterActivity mActivity;
    private List<AreaVO> data;
    private LayoutInflater mInflater;

    public AreaAdapter(RegisterActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    public void setData(List<AreaVO> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        AreaVO vo = data.get(position);
        holder.mTextView.setText(vo.getTITLE3());
        return convertView;
    }

    private class ViewHolder{
        private TextView mTextView;
    }
}
