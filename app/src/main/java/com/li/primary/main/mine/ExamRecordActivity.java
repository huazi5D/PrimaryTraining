package com.li.primary.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.ExamRecordResult;
import com.li.primary.base.common.BaseSubscriber;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.home.CustomItemDecoration;
import com.li.primary.main.mine.adapter.ExamRecordAdapter;
import com.li.primary.util.UtilIntent;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/30.
 */

public class ExamRecordActivity extends BaseActivity implements OnLoadMoreListener, View.OnClickListener{
    private RecyclerView mRecyclerView;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ExamRecordAdapter mAdapter;
    private int page = 1;
    private ImageView mIvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_record);
        initView();
        getData("1");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.swipe_target);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipe_to_load_layout);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        CustomItemDecoration decoration = new CustomItemDecoration(1, 0xffd9e0f2);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(manager);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
    }


    private void getData(String page){
        RetrofitUtil.getInstance().create(HttpService.class).getExamrecordList(AppData.token, page).subscribeOn(io()).observeOn(mainThread()).subscribe(new BaseSubscriber<ExamRecordResult>(ExamRecordActivity.this) {

            @Override
            public void onStart() {
            }

            @Override
            public void onCompleted() {
                mSwipeToLoadLayout.setLoadingMore(false);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeToLoadLayout.setLoadingMore(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(ExamRecordResult result) {
                if(result.isStatus()){
                    if(mAdapter == null){
                        mAdapter = new ExamRecordAdapter(ExamRecordActivity.this);
                        mAdapter.setData(result.getData().getList());
                        mRecyclerView.setAdapter(mAdapter);
                    }else{
                        mAdapter.setData(result.getData().getList());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void success(ExamRecordResult result) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        page++;
        getData(String.valueOf(page));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                UtilIntent.finishDIY(this);
        }
    }
}
