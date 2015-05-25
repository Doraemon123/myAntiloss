package com.example.myanti_loss;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.application.BleApplication;
import com.example.view.MyDialog;
import com.xtremeprog.sdk.ble.BleService;
import com.xtremeprog.sdk.ble.IBle;

public class MainActivity extends Activity implements OnClickListener {
	private ImageView locationIv;
	private LinearLayout locationLay;
	private ImageButton searchIb;
	private boolean mScanning;
	private IBle mBle;
	private Handler mHandler;
	private static final long SCAN_PERIOD = 10000;
	private MyDialog dialog;
	private ArrayList<BluetoothDevice> devices;
	private final BroadcastReceiver mBleReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			dialog.dismiss();// 搜索完毕
			String action = intent.getAction();
			if (BleService.BLE_NOT_SUPPORTED.equals(action)) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "该设备不支持BLE",
								Toast.LENGTH_SHORT).show();
					}
				});
			} else if (BleService.BLE_DEVICE_FOUND.equals(action)) {
				// device found
				Bundle extras = intent.getExtras();
				final BluetoothDevice device = extras
						.getParcelable(BleService.EXTRA_DEVICE);
				if (!devices.contains(device)) {
					devices.add(device);
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "发现设备",
								Toast.LENGTH_SHORT).show();
					}
				});
			} else if (BleService.BLE_NO_BT_ADAPTER.equals(action)) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "未发现设备",
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews() {
		locationIv = (ImageView) findViewById(R.id.locationIv);
		locationLay = (LinearLayout) findViewById(R.id.lnglatLay);
		searchIb = (ImageButton) findViewById(R.id.searchBtn);
		locationIv.setOnClickListener(this);
		locationLay.setOnClickListener(this);
		searchIb.setOnClickListener(this);
		mHandler = new Handler();
		dialog = new MyDialog(this, R.style.myDialogTheme, "搜索中...");
		devices = new ArrayList<BluetoothDevice>();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.locationIv:
		case R.id.lnglatLay:
			startActivity(new Intent(MainActivity.this, AmapActivtiy.class));
			break;
		case R.id.searchBtn:
			// 启动搜索
			scanLeDevice(true);
			dialog.show();
			break;
		default:
			break;
		}
	}

	private void scanLeDevice(final boolean enable) {
		BleApplication app = (BleApplication) getApplication();
		mBle = app.getIBle();
		if (mBle == null) {
			return;
		}
		if (enable) {
			// Stops scanning after a pre-defined scan period.
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mScanning = false;
					if (mBle != null) {
						mBle.stopScan();
					}
				}
			}, SCAN_PERIOD);

			mScanning = true;
			if (mBle != null) {
				mBle.startScan();
			}
		} else {
			mScanning = false;
			if (mBle != null) {
				mBle.stopScan();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mBleReceiver, BleService.getIntentFilter());
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(mBleReceiver);
	}
}
