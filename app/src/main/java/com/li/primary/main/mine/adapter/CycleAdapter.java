package com.li.primary.main.mine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.main.mine.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2017/6/15.
 */

public class CycleAdapter extends BaseAdapter{
    private RegisterActivity mActivity;
    private LayoutInflater mInflater;
    private List<AdapterVO> data = new ArrayList<>();

    public CycleAdapter(RegisterActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        /*AdapterVO vo = new AdapterVO();
        vo.setName("第一阶段");
        vo.setCode("1");
        data.add(vo);
        AdapterVO vo1 = new AdapterVO();
        vo1.setName("第二阶段");
        vo1.setCode("2");
        data.add(vo1);*/
        AdapterVO vo2 = new AdapterVO();
        vo2.setName("一周期");
        vo2.setCode("3");
        data.add(vo2);
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
        AdapterVO vo = data.get(position);
        holder.mTextView.setText(vo.getName());
        return convertView;
    }

    private class ViewHolder{
        private TextView mTextView;
    }
}
