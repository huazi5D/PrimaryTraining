package com.li.primary.main.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.vo.ExamRecordVO;
import com.li.primary.util.UtilDate;

import java.util.List;

/**
 * Created by liu on 2017/6/30.
 */

public class ExamRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private BaseActivity mActivity;
    private LayoutInflater mInflater;
    private List<ExamRecordVO> data;

    public ExamRecordAdapter(BaseActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    public void setData(List<ExamRecordVO> data) {
        this.data = data;
    }

    public void addData(List<ExamRecordVO> data){
        if(this.data != null && data != null){
            this.data.addAll(data);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_exam_record, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExamRecordVO vo = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tvTime.setText((position + 1) + "  "  + UtilDate.format(vo.getAdddate(), "yyyy-MM-dd HH:mm:ss"));
        itemViewHolder.tvScore.setText("考试成绩：" + vo.getScore());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTime;
        private TextView tvScore;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
        }
    }
}
