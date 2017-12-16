package com.li.primary.main.study.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.li.primary.R;
import com.li.primary.base.bean.vo.ChapterListVO;
import com.li.primary.base.bean.vo.ChapterVO;
import com.li.primary.main.study.PlayActivity;
import com.li.primary.main.study.PlayListActivity;
import com.li.primary.util.UtilIntent;

import java.util.List;

/**
 * Created by liu on 2017/6/8.
 */

public class ChapterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ChapterVO> data;
    private PlayListActivity mActivity;
    private LayoutInflater mInflater;
    private ItemOnClickListener mListener;
    private ChapterListVO mListVO;

    public ChapterAdapter(PlayListActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mListener = new ItemOnClickListener();
    }

    public void setData(List<ChapterVO> data) {
        this.data = data;
    }


    public void setListVO(ChapterListVO listVO) {
        mListVO = listVO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = mInflater.inflate(R.layout.recycle_chapter_list, parent, false);
        holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChapterVO vo = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.mTvName.setText((position + 1) + "、" + vo.getTitle2());
        if(vo.getFinished().equals("Y")){
            itemViewHolder.mIvState.setImageResource(R.mipmap.play_complete);
            itemViewHolder.mTvName.setTextColor(0xffff3000);
        }else{
            itemViewHolder.mIvState.setImageResource(R.mipmap.play_uncomplete);
            itemViewHolder.mTvName.setTextColor(0xff000000);
        }
        itemViewHolder.itemView.setTag(position);
        itemViewHolder.itemView.setOnClickListener(mListener);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }



    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIvState;
        private TextView mTvName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mIvState = (ImageView) itemView.findViewById(R.id.iv_state);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private class ItemOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            if (data.get(position).getFinished().equals("Y")) {
                Toast.makeText(mActivity, "视频已学完", Toast.LENGTH_LONG).show();
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putSerializable("data", mListVO);
            UtilIntent.intentDIYLeftToRight(mActivity, PlayActivity.class, bundle);
        }
    }
}
