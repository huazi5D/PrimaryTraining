package com.li.primary.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.li.primary.R;


public class LoadDialog extends Dialog {
	public LoadDialog(Context context) {
		super(context, R.style.CustomDialog);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_load);
		setCanceledOnTouchOutside(false);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		super.cancel();
	}
}
