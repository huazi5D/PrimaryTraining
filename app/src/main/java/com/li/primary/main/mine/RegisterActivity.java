package com.li.primary.main.mine;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.AppData;
import com.li.primary.base.BaseActivity;
import com.li.primary.base.bean.BaseResult;
import com.li.primary.base.bean.CityResult;
import com.li.primary.base.bean.vo.AreaVO;
import com.li.primary.base.bean.vo.IdResult;
import com.li.primary.base.bean.vo.IdVO;
import com.li.primary.base.http.HttpService;
import com.li.primary.base.http.RetrofitUtil;
import com.li.primary.main.mine.adapter.AdapterVO;
import com.li.primary.main.mine.adapter.AreaAdapter;
import com.li.primary.main.mine.adapter.CityAdapter;
import com.li.primary.main.mine.adapter.CycleAdapter;
import com.li.primary.util.UtilBitmap;
import com.li.primary.util.UtilGson;
import com.li.primary.util.UtilMD5Encryption;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by liu on 2017/6/12.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mIvBack;
    private EditText mEtId;
    private EditText mEtPassword;
    private EditText mEtRePassword;
    private Button mBtnRegister;
    private TextView mTvName;
    private TextView mTvPhone;
    private TextView mTvQua;
    private TextView mTvTime;
    private TextView mDataSelect;
    private TextView mStudyType;
    private ImageView mIvImg;
    private Spinner mSpinnerCycle;
    private Spinner mSpinnerArea1;
    private Spinner mSpinnerArea2;
    private IdVO vo;
    private File file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mEtId = (EditText) findViewById(R.id.et_id);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtRePassword = (EditText) findViewById(R.id.et_repassword);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(this);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvQua = (TextView) findViewById(R.id.tv_qualification);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mDataSelect = (TextView) findViewById(R.id.data_select);
        mDataSelect.setOnClickListener(this);
        mIvImg = (ImageView) findViewById(R.id.iv_img);
        mIvImg.setOnClickListener(this);
        mSpinnerCycle = (Spinner) findViewById(R.id.spinner_cycle);
        CycleAdapter cycleAdapter = new CycleAdapter(this);
        mSpinnerCycle.setAdapter(cycleAdapter);

        mSpinnerArea1 = (Spinner) findViewById(R.id.spinner_area1);
        ArrayList<AreaVO> area1List = new ArrayList<AreaVO>();
        area1List.add(new AreaVO("阳泉市", "03"));
        AreaAdapter area1Adapter = new AreaAdapter(this);
        area1Adapter.setData(area1List);
        mSpinnerArea1.setAdapter(area1Adapter);

        mSpinnerArea2 = (Spinner) findViewById(R.id.spinner_area2);
        ArrayList<AreaVO> area2List = new ArrayList<AreaVO>();
        area2List.add(new AreaVO("市辖区", "01"));
        area2List.add(new AreaVO("平定县", "21"));
        area2List.add(new AreaVO("盂县", "22"));
        AreaAdapter area2Adapter = new AreaAdapter(this);
        area2Adapter.setData(area2List);
        mSpinnerArea2.setAdapter(area2Adapter);

        mStudyType = (TextView) findViewById(R.id.study_type);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        mTvTime.setText(date);
    }

    private void getIdData(String id){
        RetrofitUtil.getInstance().create(HttpService.class).getDataFrDB(id).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<IdResult>() {

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
                removeProgressDialog();
                e.printStackTrace();
            }

            @Override
            public void onNext(IdResult result) {
                if(result.isStatus()){
                    vo = (IdVO) UtilGson.fromJson(result.getData().getData(), IdVO.class);
                    CityAdapter adapter = new CityAdapter(RegisterActivity.this);
                    adapter.setData(result.getData().getSysareList());
                    updateUI();
                }
            }
        });
    }

    /**
     * 获取城市数据
     */
    private void getCityData(){
        RetrofitUtil.getInstance().create(HttpService.class).getCode2ByCode1().subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<CityResult>() {

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
                removeProgressDialog();
                e.printStackTrace();
            }

            @Override
            public void onNext(CityResult result) {
                if(result.isStatus()){

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

            /*case R.id.btn_get:
                String id = mEtId.getText().toString();
                if(TextUtils.isEmpty(id)){
                    showToast("请输入身份证号");
                    return;
                }
                getIdData(id);
                break;*/

            case R.id.data_select:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dataDialog = new DatePickerDialog(RegisterActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            mTvTime.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dataDialog.show();
                break;

            case R.id.iv_img:
                ChoosePicDialog dialog = new ChoosePicDialog(RegisterActivity.this);
                dialog.show();
                break;

            case R.id.btn_register:
                register();
                break;
        }
    }


    private void register(){
        if(file == null){
            showToast("请上传头像");
            return;
        }
        String id = mEtId.getText().toString().trim();
        if(TextUtils.isEmpty(id)){
            showToast("请输入身份证号");
            return;
        } else if (id.length() != 18) {
            showToast("请输入正确的身份证号");
            return;
        }
        if(TextUtils.isEmpty(mTvName.getText().toString().trim())){
            showToast("请输入姓名");
            return;
        }
        if(TextUtils.isEmpty(mTvPhone.getText().toString().trim())){
            showToast("请输入电话");
            return;
        }
        String password = mEtPassword.getText().toString().trim();
        String repassword = mEtRePassword.getText().toString().trim();
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)){
            showToast("请输入密码");
            return;
        }
        if(!password.equals(repassword)){
            showToast("两次输入的密码不一致");
            return;
        }
        if(password.length() < 6 || repassword.length() < 6){
            showToast("密码不能少于6位");
            return;
        }
        if(TextUtils.isEmpty(mTvQua.getText().toString().trim())){
            showToast("请输入资格证号");
            return;
        }
        if(TextUtils.isEmpty(mTvQua.getText().toString().trim())){
            showToast("请输入资格证号");
            return;
        }
        Map<String, RequestBody> map = new HashMap<>();
        //头像
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        map.put("facefirsturl\"; filename=\"touxiang.jpg", requestBody);

        //身份证号
        RequestBody bodyID = RequestBody.create(MediaType.parse("text/plain"), id);
        map.put("paperscode", bodyID);

        //姓名
        RequestBody bodyTitle = RequestBody.create(MediaType.parse("text/plain"), mTvName.getText().toString().trim());
        map.put("title", bodyTitle);

        //手机号码
        RequestBody bodyTel = RequestBody.create(MediaType.parse("text/plain"), mTvPhone.getText().toString().trim());
        map.put("tel", bodyTel);

        //密码
        RequestBody bodyPassword = RequestBody.create(MediaType.parse("text/plain"), UtilMD5Encryption.getMd5Value(password));
        map.put("password", bodyPassword);

        //学习类型
        RequestBody bodyType = RequestBody.create(MediaType.parse("text/plain"), "010300");
        map.put("persontype", bodyType);

        //资格证号
        RequestBody bodyZyzgzh = RequestBody.create(MediaType.parse("text/plain"), mTvQua.getText().toString().trim());
        map.put("cyzgzh", bodyZyzgzh);

        //区域-市
        AreaVO cityVO = (AreaVO) mSpinnerArea1.getSelectedItem();
        RequestBody body_city = RequestBody.create(MediaType.parse("text/plain"), cityVO.getCODE3());
        map.put("city", body_city);

        //区域-区
        AreaVO areaVO = (AreaVO) mSpinnerArea2.getSelectedItem();
        RequestBody body_area = RequestBody.create(MediaType.parse("text/plain"), areaVO.getCODE3());
        map.put("area", body_area);

        //证件初领日期
        RequestBody bodyCyzg_fzrq = RequestBody.create(MediaType.parse("text/plain"), mTvTime.getText().toString().trim());
        map.put("cyzg_fzrq", bodyCyzg_fzrq);

        //学习周期
        AdapterVO cycleVO = (AdapterVO) mSpinnerCycle.getSelectedItem();
        RequestBody bodyCycle = RequestBody.create(MediaType.parse("text/plain"), cycleVO.getCode());
        map.put("cycle", bodyCycle);

        RetrofitUtil.getInstance().create(HttpService.class).register(map).subscribeOn(io()).observeOn(mainThread()).subscribe(new Subscriber<BaseResult>() {

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
                removeProgressDialog();
                showToast("注册失败");
                e.printStackTrace();
            }

            @Override
            public void onNext(BaseResult result) {
                if(result.isStatus()){
                    finish();
                }else{
                    showToast(result.getMessage());
                }
            }
        });
    }

    private void updateUI(){
        mTvName.setText(vo.getXm());
        mTvPhone.setText(vo.getSjhm());
        mTvQua.setText(vo.getCyzgzh());
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        mTvTime.setText(date);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    File file = new File(AppData.PATH);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File fileImg = new File(AppData.PATH, "touxiang.jpg");
                    if (fileImg.exists()) {
//                    startPhotoZoom(Uri.fromFile(new File(AppData.PATH, "touxiang.jpg")));
                        setPicToView(Uri.fromFile(new File(AppData.PATH, "touxiang.jpg")));
                    }
                    break;
                case 1:
                    if (data != null) {
//                    startPhotoZoom(data.getData());
                        setPicToView(data.getData());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void setPicToViewCamera(Uri uri){

    }


    private void setPicToView(Uri uri) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, null, null, null, null);// 根据Uri从数据库中找
        if (cursor != null) {
            cursor.moveToFirst();// 把游标移动到首位，因为这里的Uri是包含ID的所以是唯一的不需要循环找指向第一个就是了
            String filePath = cursor.getString(cursor.getColumnIndex("_data"));// 获取图片路
            String orientation = cursor.getString(cursor
                    .getColumnIndex("orientation"));// 获取旋转的角度
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);//根据Path读取资源图片
            int angle = 0;
            if (orientation != null && !"".equals(orientation)) {
                angle = Integer.parseInt(orientation);
            }
            if (angle != 0) {
                // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                Matrix m = new Matrix();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                m.setRotate(angle); // 旋转angle度
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                        m, true);// 从新生成图片

            }
            mIvImg.setImageBitmap(bitmap);
            file = UtilBitmap.compressBmpToFile(bitmap, AppData.PATH + "touxiang.jpg", 600);
        }else {
            int rotate = UtilBitmap.getBitmapDegree(uri.getPath());
            Bitmap bitmap = BitmapFactory.decodeFile(uri.getPath());//根据Path读取资源图片
            Bitmap bitmap1 = UtilBitmap.rotateBitmapByDegree(bitmap, rotate);
            mIvImg.setImageBitmap(bitmap1);
            file = UtilBitmap.compressBmpToFile(bitmap1, AppData.PATH + "touxiang.jpg", 600);
        }


    }


    // 获取图片信息返回 byte数组
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    // 获取图片数组返回bitmap
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null) {
            if (opts != null) {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            } else {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        }
        return null;
    }

}
