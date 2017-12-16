package com.li.primary.main.study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.primary.MainActivity;
import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseFragment;
import com.li.primary.base.bean.StudyResult;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.home.CustomItemDecoration;
import com.li.primary.main.study.adapter.StudyAdapter;
import com.li.primary.view.CommonLayout;

import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/3.
 */

public class StudyFragment extends BaseFragment implements View.OnClickListener{
    private View mView;
    private RecyclerView mRecyclerView;
    private TextView mTvCycle;
    private TextView mTvType;
    private CommonLayout mCommonLayout;
    private MainActivity mActivity;
    private StudyAdapter mChapterAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_study, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mActivity = (MainActivity) getActivity();
        getData(AppData.token);
    }

    private void initView() {
        mCommonLayout = (CommonLayout) mView.findViewById(R.id.common_layout);
        mCommonLayout.setListener(this);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        CustomItemDecoration decoration = new CustomItemDecoration(1,0xffeaebec);
        decoration.setDividerSize(40);
        mRecyclerView.addItemDecoration(decoration);
        mTvCycle = (TextView) mView.findViewById(R.id.tv_cycle);
        mTvType = (TextView) mView.findViewById(R.id.tv_type);
    }

    private void getData(String token){
        RetrofitUtil.getInstance().create(HttpService.class).getCode1Title1List(token).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<StudyResult>() {

            @Override
            public void onStart() {
                super.onStart();
                mActivity.showProgressDialog();
            }

            @Override
            public void onCompleted() {
                mActivity.removeProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mCommonLayout.show(CommonLayout.SHOW_RELOAD);
                mActivity.removeProgressDialog();
                mActivity.showToast("服务器出错，请联系客服");
            }

            @Override
            public void onNext(StudyResult result) {
                if(result.isStatus()){
                    if(result != null && result.getData() != null) {
                        mCommonLayout.showData(true);
                        if (mChapterAdapter == null) {
                            mChapterAdapter = new StudyAdapter(mActivity);
                            mChapterAdapter.setData(result.getData().getSysEduTypeList());
                            mChapterAdapter.setLongtime(Integer.valueOf(result.getData().getLongtime()));
                            mChapterAdapter.setSumEduTime(Integer.valueOf(result.getData().getSumEduTime()));
                            mRecyclerView.setAdapter(mChapterAdapter);
                        }else{
                            mChapterAdapter.setData(result.getData().getSysEduTypeList());
                            mChapterAdapter.setLongtime(Integer.valueOf(result.getData().getLongtime()));
                            mChapterAdapter.setSumEduTime(Integer.valueOf(result.getData().getSumEduTime()));
                            mChapterAdapter.notifyDataSetChanged();
                        }
                        if(!TextUtils.isEmpty(result.getData().getCycle())) {
                            AppData.cycle_code = result.getData().getCycle();
                            switch (result.getData().getCycle()){
                                case "1":
                                    mTvCycle.setText("第一阶段");
                                    break;
                                case "2":
                                    mTvCycle.setText("第二阶段");
                                    break;
                                case "3":
                                    mTvCycle.setText("一周期");
                                    break;
                            }
                        }

                        if(!TextUtils.isEmpty(result.getData().getPersontype())) {
                            switch (result.getData().getPersontype()){
                                case "010600":
                                    mTvType.setText("货运");
                                    break;
                                case "010100":
                                    mTvType.setText("客运");
                                    break;
                                case "010700":
                                    mTvType.setText("危货");
                                    break;
                                case "010300":
                                    mTvType.setText("出租车");
                                    break;
                            }
                        }
                    }else{
                        mCommonLayout.show(CommonLayout.SHOW_RELOAD);
                    }
                }else{
                    if(result.getMessage().equals("99")){
                        mActivity.showToast("请重新登录，否则学习无效");
                    }else {
                        mCommonLayout.show(CommonLayout.SHOW_RELOAD);
                        mActivity.showToast(result.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reload:
                getData(AppData.token);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
