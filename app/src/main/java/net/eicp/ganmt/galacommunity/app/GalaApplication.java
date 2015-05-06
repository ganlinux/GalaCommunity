package net.eicp.ganmt.galacommunity.app;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class GalaApplication extends Application {
	// private HttpClient httpClient = null;
	public static String VERSIONCODE = null;

	public static String DEVICEID = null;

	public static String EXTEND_NET = null;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowMgr = (WindowManager) getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		windowMgr.getDefaultDisplay().getMetrics(dm);

		//JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
		//JPushInterface.init(this); // 初始化 JPush
		// if(httpClient == null){
		// httpClient = this.createHttpClient();
		// }
//		if (VERSIONCODE == null) {
//			VERSIONCODE = Utils.getVersionCode(this);
//		}
//		if (DEVICEID == null) {
//			DEVICEID = Utils.getDeviceId(this);
//		}
//		if (EXTEND_NET == null) {
//			EXTEND_NET = Utils.getExtend(this);
//		}
		// if (DataManager == null) {
		// DataManager = new OutDatabaseManager(this);
		// DataManager.createDatabase();
		// }
		super.onCreate();
	}

	// private HttpClient createHttpClient() {
	// HttpParams params = new BasicHttpParams();
	// HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	// HttpProtocolParams.setContentCharset(params,
	// HTTP.DEFAULT_CONTENT_CHARSET);
	// HttpProtocolParams.setUseExpectContinue(params, true);
	// HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
	// HttpConnectionParams.setSoTimeout(params, 20 * 1000);
	// HttpConnectionParams.setSocketBufferSize(params, 8192);
	// SchemeRegistry schReg = new SchemeRegistry();
	// schReg.register(new Scheme("http", PlainSocketFactory
	// .getSocketFactory(), 80));
	// schReg.register(new Scheme("https",
	// SSLSocketFactory.getSocketFactory(), 443));
	//
	// ClientConnectionManager connMgr = new ThreadSafeClientConnManager(
	// params, schReg);
	//
	// return new DefaultHttpClient(connMgr, params);
	// }
	// // 关闭连接管理器并释放资源
	// private void shutdownHttpClient() {
	// if (httpClient != null && httpClient.getConnectionManager() != null) {
	// httpClient.getConnectionManager().shutdown();
	// }
	// }
	//
	// // 对外提供HttpClient实例
	// public HttpClient getHttpClient() {
	// return httpClient;
	// }
	@Override
	public void onTerminate() {
		super.onTerminate();
		// this.shutdownHttpClient();
	}
}
