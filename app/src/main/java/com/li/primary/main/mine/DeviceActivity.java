package com.li.primary.main.mine;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.primary.R;
import com.li.primary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2017/8/26.
 */

public class DeviceActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private ImageView ivBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        DeviceAdapter adapter = new DeviceAdapter();
        mRecyclerView.setAdapter(adapter);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ItemViewHolder> {
        private String[] strName = {"BOARD", "BOOTLOADER", "BRAND", "DEVICE", "MANUFACTURER", "MODEL", "PRODUCT", "VERSION", "ROTATION"};
        private List<String> strDesc = new ArrayList<>();

        ;

        public DeviceAdapter() {

            strDesc.add(Build.BOARD);
            strDesc.add(Build.BOOTLOADER);
            strDesc.add(Build.BRAND);
            strDesc.add(Build.DEVICE);
            strDesc.add(Build.MANUFACTURER);
            strDesc.add(Build.MODEL);
            strDesc.add(Build.PRODUCT);
            strDesc.add(getAppVersionName(getApplicationContext()));
            strDesc.add(String.valueOf(getWindowManager().getDefaultDisplay().getRotation()));

        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DeviceActivity.this).inflate(R.layout.recycle_device_item, parent, false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.tvName.setText(strName[position]);
            holder.tvDesc.setText(strDesc.get(position));
        }

        @Override
        public int getItemCount() {
            return strName.length;
        }

        protected class ItemViewHolder extends RecyclerView.ViewHolder {
            private TextView tvName;
            private TextView tvDesc;


            public ItemViewHolder(View itemView) {
                super(itemView);
                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            }
        }
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
