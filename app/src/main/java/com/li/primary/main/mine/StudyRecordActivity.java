package com.li.primary.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.StudyRecord;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.home.CustomItemDecoration;
import com.li.primary.main.mine.adapter.StudyRecordAdapter;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/19.
 */

public class StudyRecordActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener{
    private ImageView mIvBack;
    private TextView tvTime;
    private RecyclerView mRecyclerView;
    private StudyRecordAdapter mAdapter;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private int page = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_record);
        initView();
        getData(0);
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        tvTime = (TextView) findViewById(R.id.tv_time);
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new CustomItemDecoration(1, 0xffe9edf8));
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load_layout);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }


    private void getData(final int type){
        RetrofitUtil.getInstance().create(HttpService.class).getLearnrecordList(AppData.token, String.valueOf(page)).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<StudyRecord>() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                mSwipeToLoadLayout.setRefreshing(false);
                mSwipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onError(Throwable e) {
                page--;
                mSwipeToLoadLayout.setRefreshing(false);
                mSwipeToLoadLayout.setLoadingMore(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(StudyRecord result) {
                if(result.isStatus()){
                    tvTime.setText("本次总学时为" + result.getData().getSumEduTime() + "，已完成学时" + result.getData().getLongtime() + "分钟");
                    if(mAdapter == null){
                        mAdapter = new StudyRecordAdapter(StudyRecordActivity.this);
                        mAdapter.setData(result.getData().getList());
                        mRecyclerView.setAdapter(mAdapter);
                    }else{
                        if(type == 0){
                            mAdapter.setData(result.getData().getList());
                        }else{
                            mAdapter.addData(result.getData().getList());
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }else{
                    page--;
                }
            }
        });
    }



    @Override
    public void onLoadMore() {
        page++;
        getData(1);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getData(0);
    }
}
