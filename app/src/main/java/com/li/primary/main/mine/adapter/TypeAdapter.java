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

public class TypeAdapter extends BaseAdapter{
    private RegisterActivity mActivity;
    private LayoutInflater mInflater;
    private List<AdapterVO> data = new ArrayList<>();

    public TypeAdapter(RegisterActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        AdapterVO vo = new AdapterVO();
        vo.setName("货运");
        vo.setCode("010600");
        data.add(vo);
        AdapterVO vo1 = new AdapterVO();
        vo1.setName("客运");
        vo1.setCode("010100");
        data.add(vo1);
        AdapterVO vo2 = new AdapterVO();
        vo2.setName("危货");
        vo2.setCode("010700");
        data.add(vo2);
        AdapterVO vo3 = new AdapterVO();
        vo3.setName("出租");
        vo3.setCode("010300");
        data.add(vo3);
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
