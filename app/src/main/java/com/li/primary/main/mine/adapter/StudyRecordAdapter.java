package com.li.primary.main.mine.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.bean.vo.StudyRecordVO;
import com.li.primary.main.mine.StudyRecordActivity;
import com.li.primary.main.mine.StudyRecordDetailsActivity;
import com.li.primary.util.UtilIntent;

import java.util.List;

/**
 * Created by liu on 2017/6/19.
 */

public class StudyRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private StudyRecordActivity mActivity;
    private LayoutInflater mInflater;
    private List<StudyRecordVO> data;

    public StudyRecordAdapter(StudyRecordActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(mActivity);
    }

    public void setData(List<StudyRecordVO> data) {
        this.data = data;
    }

    public void addData(List<StudyRecordVO> data){
        if(this.data != null && data != null){
            this.data.addAll(data);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = mInflater.inflate(R.layout.recycle_study_record_item, parent, false);
        holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final StudyRecordVO vo = data.get(position);
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.mTvName.setText((position + 1) + "、" + vo.getTypetitle());
        viewHolder.mTvTime.setText("时长：" + vo.getLongtime() + "分");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("vo", vo);
                UtilIntent.intentDIYLeftToRight(mActivity, StudyRecordDetailsActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvName;
        private TextView mTvTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
