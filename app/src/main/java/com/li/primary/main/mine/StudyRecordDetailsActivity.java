package com.li.primary.main.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.vo.StudyRecordVO;
import com.li.primary.util.UtilDate;

/**
 * Created by liu on 2017/6/19.
 */

public class StudyRecordDetailsActivity extends BaseActivity{
    private ImageView mIvBack;
    private TextView mTvName;
    private TextView mTvTimeNum;
    private TextView mTvStart;
    private TextView mTvEnd;
    private StudyRecordVO mVO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_details);
        Bundle bundle = getIntent().getExtras();
        mVO = (StudyRecordVO) bundle.getSerializable("vo");
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvTimeNum = (TextView) findViewById(R.id.tv_time);
        mTvStart = (TextView) findViewById(R.id.tv_start);
        mTvEnd = (TextView) findViewById(R.id.tv_end);
        mTvName.setText(mVO.getTypetitle());
        mTvTimeNum.setText(mVO.getLongtime() + "分");
        mTvStart.setText(UtilDate.format(mVO.getStarttime(),"yyyy-MM-dd HH:mm:ss"));
        mTvEnd.setText(UtilDate.format(mVO.getEndtime(), "yyyy-MM-dd HH:mm:ss"));
    }

}
