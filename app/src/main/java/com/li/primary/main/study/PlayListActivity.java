package com.li.primary.main.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.ChapterResult;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.home.CustomItemDecoration;
import com.li.primary.main.study.adapter.ChapterAdapter;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/19.
 */

public class PlayListActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mIvBack;
    private TextView mTvTime;
    private RecyclerView mRecyclerView;
    private ChapterAdapter mAdapter;
    private String code1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        initView();
        Bundle bundle = getIntent().getExtras();
        code1 = bundle.getString("code1");
        if(TextUtils.isEmpty(code1)){
            showToast("数据有误，请联系客服");
            finish();
        }
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mIvBack.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new CustomItemDecoration(1, 0xffd9e0f2));
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(code1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    private void getData(String code1){
        RetrofitUtil.getInstance().create(HttpService.class).getCode2Title2List(AppData.token, code1).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<ChapterResult>() {

            @Override
            public void onStart() {
                super.onStart();
                showProgressDialog();
            }

            @Override
            public void onCompleted() {
                removeProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                removeProgressDialog();
            }

            @Override
            public void onNext(ChapterResult result) {
                if(result.isStatus()){
                    if(result != null && result.getData() != null) {
                        if (mAdapter == null) {
                            mAdapter = new ChapterAdapter(PlayListActivity.this);
                            mAdapter.setData(result.getData().getSysEduTypeList());
                            mAdapter.setListVO(result.getData());
                            mRecyclerView.setAdapter(mAdapter);
                        }else{
                            mAdapter.setData(result.getData().getSysEduTypeList());
                            mAdapter.setListVO(result.getData());
                            mAdapter.notifyDataSetChanged();
                        }
                        mTvTime.setText("本次总学时为" + result.getData().getSumEduTime() + "，已完成学时" + result.getData().getLongtime() + "分钟");
                    }else{
                        if(result.getMessage().equals("99")){
                            showToast("请重新登录，否则学习无效");
                        }
                    }
                }
            }
        });
    }
}
