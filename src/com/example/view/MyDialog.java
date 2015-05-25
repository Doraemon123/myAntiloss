package com.example.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.example.myanti_loss.R;

public class MyDialog extends Dialog {
	private FlippingImageView iv;
	private TextView tv;
	private String str;

	public MyDialog(Context context, String str) {
		super(context);
		this.str = str;
		init();
	}

	public MyDialog(Context context, int theme, String str) {
		super(context, theme);
		this.str = str;
		init();
	}

	private void init() {
		setContentView(R.layout.login_dialog);
		iv = (FlippingImageView) findViewById(R.id.iv);
		tv = (TextView) findViewById(R.id.message_tv);
		iv.startAnimation();
		tv.setText(str);
	}

	@Override
	public void dismiss() {
		super.dismiss();
		if (isShowing())
			super.dismiss();
	}
}
