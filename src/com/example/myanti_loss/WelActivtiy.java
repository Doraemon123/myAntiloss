package com.example.myanti_loss;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WelActivtiy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wel_activtiy);
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "本机没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show();
			finish();
		}
		// 如果本地蓝牙没有开启，则开启
		if (!mBluetoothAdapter.isEnabled()) {
			// 我们通过startActivityForResult()方法发起的Intent将会在onActivityResult()回调方法中获取用户的选择，比如用户单击了Yes开启，
			// 那么将会收到RESULT_OK的结果，
			// 如果RESULT_CANCELED则代表用户不愿意开启蓝牙
			Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(mIntent, 1);
		} else {
			startActivity(new Intent(WelActivtiy.this, MainActivity.class));
			finish();
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Toast.makeText(this, "蓝牙已经开启", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(WelActivtiy.this, MainActivity.class));
				finish();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "不允许蓝牙开启", Toast.LENGTH_SHORT).show();
				finish();
			}
		}

	}

}
