package com.li.primary.main.mine;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.InfoResult;
import com.li.primary.base.bean.vo.InfoVO;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.util.UtilBitmap;
import com.li.primary.util.UtilDate;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/12.
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mIvBack;
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvId;
    private TextView mTvType;
    private TextView mTvTime;
    private CircleImageView mCvImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
        getData(AppData.token);
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvId = (TextView) findViewById(R.id.tv_id);
        mTvType = (TextView) findViewById(R.id.tv_type);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mCvImg = (CircleImageView) findViewById(R.id.cv_img);
        mIvBack.setOnClickListener(this);
    }

    private void getData(String token){
        RetrofitUtil.getInstance().create(HttpService.class).getUserInfo(token).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<InfoResult>() {

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
            public void onNext(InfoResult result) {
                if(result.isStatus()){
                    if(result.getData() != null) {
                        InfoVO vo = result.getData();
                        mTvName.setText(vo.getTitle());
                        mTvPhone.setText(vo.getTel());
                        mTvId.setText(vo.getPaperscode());
                        switch (vo.getPersontype()){
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
                                mTvType.setText("出租");
                                break;
                        }
                        mTvTime.setText(UtilDate.format(vo.getCyzg_clrq(), "yyyy-MM-dd"));
                        Bitmap bitmap = UtilBitmap.base64ToBitmap(result.getData().getFacefirsturl());
                        mCvImg.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }
}
