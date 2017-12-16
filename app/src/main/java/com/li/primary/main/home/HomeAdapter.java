package com.li.primary.main.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.MainActivity;
import com.li.primary.R;
import com.li.primary.base.bean.vo.HomeNewVO;
import com.li.primary.util.UtilGlide;
import com.li.primary.util.UtilIntent;

import java.util.List;

/**
 * Created by liu on 2017/6/8.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<HomeNewVO> data;
    private MainActivity mActivity;
    private LayoutInflater mInflater;
    private ItemOnClickListener mListener;

    public HomeAdapter(MainActivity activity) {
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);
        mListener = new ItemOnClickListener();
    }

    public void setData(List<HomeNewVO> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View view = mInflater.inflate(R.layout.recycle_home_item, parent, false);
        holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeNewVO vo = data.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.mTvName.setText(vo.getNewsname());
        UtilGlide.loadImg(mActivity, itemViewHolder.mIvImg, vo.getNewsimg());
        itemViewHolder.itemView.setTag(vo);
        itemViewHolder.itemView.setOnClickListener(mListener);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }



    private class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIvImg;
        private TextView mTvName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    private class ItemOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            HomeNewVO vo = (HomeNewVO) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putString("url", vo.getNewsdetail());
            bundle.putString("title", vo.getNewsname());
            UtilIntent.intentDIYLeftToRight(mActivity, NewDetailsActivity.class, bundle);
        }
    }
}
