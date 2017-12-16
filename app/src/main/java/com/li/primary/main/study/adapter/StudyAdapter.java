package com.li.primary.main.study.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.primary.MainActivity;
import com.li.primary.R;
import com.li.primary.base.bean.vo.StudyVO;
import com.li.primary.main.study.PlayListActivity;
import com.li.primary.util.UtilIntent;

import java.util.List;

/**
 * Created by liu on 2017/6/19.
 */

public class StudyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private MainActivity mActivity;
    private List<StudyVO> data;
    private LayoutInflater mInflater;
    private ItemOnClickListener mListener;
    private int sumEduTime;
    private int longtime;

    public StudyAdapter(MainActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mListener = new ItemOnClickListener();
    }

    public void setSumEduTime(int sumEduTime) {
        this.sumEduTime = sumEduTime;
    }

    public void setLongtime(int longtime) {
        this.longtime = longtime;
    }

    public void setData(List<StudyVO> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_study_item, parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudyVO vo = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.itemView.setTag(vo);
        itemViewHolder.itemView.setOnClickListener(mListener);
        itemViewHolder.mTvName.setText(vo.getTitle1());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private class ItemOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            StudyVO vo = (StudyVO) v.getTag();
            if(longtime >= sumEduTime){
                mActivity.showToast("学习完成");
                return;
            }
            if(vo.getEduable().equals("Y")) {
                Bundle bundle = new Bundle();
                bundle.putString("code1", vo.getCode1());
                UtilIntent.intentDIYLeftToRight(mActivity, PlayListActivity.class, bundle);
            }else{
                mActivity.showToast("该章节目前不能学习");
            }
        }
    }
}
