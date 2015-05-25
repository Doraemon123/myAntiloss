package com.example.myanti_loss;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.example.myanti_loss.R;

public class AmapActivtiy extends Activity implements OnClickListener {
	private Button back;
	private MapView amapView;
	private AMap amap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amap);
		initViews();
		amapView.onCreate(savedInstanceState);
		init();
		
	}
	 /**
     * ��ʼ��AMap����
     */
    private void init() {
        if (amap == null) {
            amap = amapView.getMap();
        }
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onResume() {
        super.onResume();
        amapView.onResume();
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onPause() {
        super.onPause();
        amapView.onPause();
    }
     
    /**
     * ����������д
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        amapView.onSaveInstanceState(outState);
    }
 
    /**
     * ����������д
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        amapView.onDestroy();
    }
	private void initViews() {
		back=(Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		amapView=(MapView) findViewById(R.id.amap);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			this.finish();
			break;

		default:
			break;
		}
	}

}
