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
			Toast.makeText(this, "����û���ҵ�����Ӳ����������", Toast.LENGTH_SHORT).show();
			finish();
		}
		// �����������û�п���������
		if (!mBluetoothAdapter.isEnabled()) {
			// ����ͨ��startActivityForResult()���������Intent������onActivityResult()�ص������л�ȡ�û���ѡ�񣬱����û�������Yes������
			// ��ô�����յ�RESULT_OK�Ľ����
			// ���RESULT_CANCELED������û���Ը�⿪������
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
				Toast.makeText(this, "�����Ѿ�����", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(WelActivtiy.this, MainActivity.class));
				finish();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "��������������", Toast.LENGTH_SHORT).show();
				finish();
			}
		}

	}

}
