package net.eicp.ganmt.galacommunity.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import net.eicp.ganmt.galacommunity.R;

/**
 * @author kingofglory
 *         email: kingofglory@yeah.net
 *         blog:  http:www.google.com
 * @date 2014-2-21
 * TODO 闪屏界面，根据指定时间进行跳转
 * 		在activity_splash.xml中加入background属性并传入图片资源ID即可
 */
public class SplashActivity extends BaseFragmentActivity {

	private static final long DELAY_TIME = 2000L;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		redirectByTime();
	}
	
	/**
	 * 根据时间进行页面跳转
	 */
	private void redirectByTime() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				redictToActivity(SplashActivity.this, MainActivity.class, null);
				finish();
			}
		}, DELAY_TIME);
	}

}
