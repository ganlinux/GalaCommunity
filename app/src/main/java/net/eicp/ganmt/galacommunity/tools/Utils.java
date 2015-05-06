package net.eicp.ganmt.galacommunity.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import net.eicp.ganmt.galacommunity.dal.sp.SharedPreferencesUtil;

/**
 * @author pengqm
 * 
 */
public class Utils {
	private Context context;
	public static String privateKey = "77466E6C-C07D-4289-A104-40C135021E0A";
	public static String POST = "POST";
	public static String GET = "GET";
	public static String post = Utils.POST;
	public static String baseip_formal = "http://m.yichemall.com/MsappV2/";
	public static String baseip_test = "http://123.127.236.151:9001/msapp/";
	public static String ip = baseip_formal + "api";
	public static String fileIp = baseip_formal + "files/UserRegistV2";
	public static String rewardip = baseip_formal + "files/SubmitYongJinV2";
	public static String successResponseRspcode = "1000";
//	public static String img_Identity_Address = Environment
//			.getExternalStorageDirectory() + "/" + "YiCheMs";
	public static final String OUT_DATABASE_NAME = "out_data.db";
	public static final String PACKAGENAME = "com.yichemall.app.android";
	// 网络请求错误代码
	public static int OK_RESCODELOW = 1000;
	public static int OK_RESCODEHEIGHT = 1999;
	public static int NO_CLIENT_RESCODELOW = 2000;
	public static int NO_CLIENT_RESCODEHEIGHT = 2999;
	public static int NO_SERVER_RESCODELOW = 3000;
	public static int NO_SERVER_RESCODEHEIGHT = 3999;
	public static int SAFETYEXITNUM_I = 3201;
	public static int SAFETYEXITNUM_II = 2203;
	// 本地错误代码
	public static String LOCALERROR_UNUSUAL = "10000";
	public static String LOCALERRO_NONETWORK = "10001";
	public static String LOCALERRO_NETTIMEROUT = "10002";
	public static String RESULTNET_OK = "OK";
	public static String RESULTNET_NO = "NO";
	public static String RESULT_SAFETYEXIT = "Safetyexit";

	public static int TOAST_DURATION = Toast.LENGTH_LONG;
	public static final int RegisterSuccess_Code = 66;
	public Utils(Context context) {
		this.context = context;
	}
	public void setConnectionType(int type){
		
		
	}

	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null) {
			if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI
					|| activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				return true;
			}
		}
		return false;
	}

	public static String intToIp(int i) {

		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
				+ "." + (i >> 24 & 0xFF);
	}

	/**
	 * 对list中的字符串进行排序，拼接，md5加密获得sign的值
	 * 
	 * 
	 */
	public static String getSign(HashMap<String, String> hMap) {
		// 排序
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
				hMap.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			public int compare(Map.Entry<String, String> o1,
					Map.Entry<String, String> o2) {
				return (o1.getValue()).toString().compareTo(o2.getValue());
			}
		});
		String target_string = "";
		for (int i = 0; i < infoIds.size(); i++) {
			target_string += infoIds.get(i) + "&";
		}
		String full_string = target_string + "privata_key=" + privateKey;
		String sign = stringToMD5(full_string);
		return sign;
	}

	/**
	 * 将字符串转成MD5值 32位
	 * 
	 * 
	 */
	public static String stringToMD5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

	/**
	 * 
	 * 处理图片url中的{0}，替换为1-6,数字越低图片越小
	 * 
	 */
	public static String getImgUrl(String url, String number) {
		String newUrl = "";
		if (!TextUtils.isEmpty(url)) {
			newUrl = url.replace("{0}", number);
		}
		return newUrl;
	}

	/**
	 * 
	 * 毫秒转换成天/小时/分/秒/
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
	}

	/**
	 * 
	 * 毫秒转换成天/小时/分/
	 */
	public static String formatDuringTime(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);

		String day = days + "";
		String hour = hours + "";
		String minute = minutes + "";

		if (days < 10)
			day = "0" + day;
		if (hours < 10)
			hour = "0" + hour;
		if (minutes < 10)
			minute = "0" + minute;

		return day + "天" + hour + "小时" + minute + "分";
	}

	/**
	 * 格式化数字
	 * 
	 * @param value
	 * @return
	 */
	public static String formatValue(float value) {

		float format_value = value / (float) 10000;

		String val = format_value + "";

		return val.format("%.2f", format_value);
	}

	/*
	 * 获取版本号
	 */
	public static String getVersionCode(Context context) {
		String version = "";
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = context.getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo;
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
			version = String.valueOf(packInfo.versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return version;
	}

	// 创建HttpClient实例
	/**
	 * 获取deviceId
	 * 
	 * @return deviceId
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * 获取网络头部信息extend
	 * 
	 * @throws
	 * */
	public static String getExtend(Context context) {
		String packageName = context.getPackageName();
		String currentapiVersion = android.os.Build.VERSION.RELEASE;
		String deviceName = android.os.Build.MODEL;
		String extendStr = "2^" + packageName + "," + "4^" + currentapiVersion
				+ "," + "5^" + deviceName + ",";
		return extendStr;
	}

	/**
	 * 获取手机的ip地址
	 * 
	 * @return ip地址
	 */
	public static String getHostIp(Context context) {
		if (Utils.isWifi(context)) {
			// 获取wifi服务
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			// 判断wifi是否开启
			if (!wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(true);
			}
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			return intToIp(ipAddress);
		} else {
			try {
				for (Enumeration<NetworkInterface> en = NetworkInterface
						.getNetworkInterfaces(); en.hasMoreElements();) {
					NetworkInterface intf = en.nextElement();
					for (Enumeration<InetAddress> ipAddr = intf
							.getInetAddresses(); ipAddr.hasMoreElements();) {
						InetAddress inetAddress = ipAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							return inetAddress.getHostAddress();
						}
					}
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * 根据json字符串转换成map
	 * */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		try {
			JSONObject json = new JSONObject(jsonStr);
			Iterator keys = json.keys();

			while (keys.hasNext()) {
				Object k = (String) keys.next();
				Object v = json.get(k.toString()).toString();

				// 如果内层还是数组的话，继续解析
				if (v instanceof JSONArray) {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

					Iterator<JSONObject> it = ((Iterator<JSONObject>) v);
					while (it.hasNext()) {
						JSONObject json2 = it.next();
						list.add(parseJSON2Map(json2.toString()));
					}
					map.put(k.toString(), list);
				} else {
					map.put(k.toString(), v);
				}
			}
			return map;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}

	/**
	 * 根据发送过来的数据判断是否请求成功！
	 * */
	public static String resultInfoByRsp(String rspCode, String rspDesc) {
		String result = null;
		int rspC = Integer.parseInt(rspCode);
		if (rspC >= OK_RESCODELOW && rspC <= OK_RESCODEHEIGHT) {
			// 请求成功！
			result = RESULTNET_OK;
		}
		if (rspC >= NO_CLIENT_RESCODELOW && rspC <= NO_CLIENT_RESCODEHEIGHT) {
			// 请求失败客户端原因
			if (rspDesc != null && !rspDesc.equals("")) {
				result = rspDesc;
			} else {
				result = RESULTNET_NO;
			}
			if (rspC == SAFETYEXITNUM_II) {
				result = RESULT_SAFETYEXIT;
			}
		}
		if (rspC >= NO_SERVER_RESCODELOW && rspC <= NO_SERVER_RESCODEHEIGHT) {
			// 请求失败服务端原因
			if (rspDesc != null && !rspDesc.equals("")) {
				result = rspDesc;
			} else {
				result = RESULTNET_NO;
			}
			if (rspC == SAFETYEXITNUM_I) {
				result = RESULT_SAFETYEXIT;
			}
		}

		return result;
	}

	/**
	 * 流转换字符串
	 * 
	 * @throws java.io.IOException
	 * */
	public static String convertStreamToString(InputStream is)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"utf-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 流转换自己数组
	 * 
	 * @param is
	 *            数据流
	 * @return 字节数组
	 * @throws java.io.IOException
	 */
	@SuppressWarnings("unused")
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.flush();
		bytestream.close();
		is.close();
		return imgdata;
	}

	/**
	 * unix时间戳转换为制定字符串
	 * 
	 * @author ganmt
	 * */
	public static String convertTimeStampToDate(String unixTimeStamp) {
		Long tempTimestamp = Long.parseLong(unixTimeStamp) * 1000;
		String date = new java.text.SimpleDateFormat("yyyy年MM月dd日")
				.format(new java.util.Date(tempTimestamp));
		return date;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

//	private static int compressImage(Bitmap image) {
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//		int options = 100;
//		while (baos.toByteArray().length / 1024 > 300) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
//			baos.reset();// 重置baos即清空baos
//			options -= 10;// 每次都减少10
//			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//		}
//		return options;
//	}
//
//	public static Bitmap getimage(File file, float ivw, float ivh)
//			throws Exception {
//		BitmapFactory.Options newOpts = new BitmapFactory.Options();
//		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
//		newOpts.inJustDecodeBounds = true;
//		Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), newOpts);// 此时返回bm为空
//
//		newOpts.inJustDecodeBounds = false;
//		int w = newOpts.outWidth;
//		int h = newOpts.outHeight;
//		float hh = ivh;
//		float ww = ivw;
//		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//		int be = 1;// be=1表示不缩放
//
//		// if (h > hh || w > ww) {
//		// // 计算出实际宽高和目标宽高的比率
//		// final int heightRatio = Math.round((float) h
//		// / (float) hh);
//		// final int widthRatio = Math.round((float) w / (float) ww);
//		// // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
//		// // 一定都会大于等于目标的宽和高。
//		// be = heightRatio < widthRatio ? heightRatio : widthRatio;
//		// }
//		// if (be <= 0)
//		// be = 1;
//		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
//			be = (int) (newOpts.outWidth / ww);
//		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
//			be = (int) (newOpts.outHeight / ww);
//		}
//		if (be <= 0)
//			be = 1;
//		newOpts.inSampleSize = be;// 设置缩放比例
//		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//		bitmap = BitmapFactory.decodeFile(file.getPath(), newOpts);
//		// 如果图片是竖向，进行反转
//		if (h > w) {
//			Matrix matrix = new Matrix();
//			matrix.postRotate(90);
//			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
//					bitmap.getHeight(), matrix, true);
//		}
//		return saveImg(bitmap, file);
//	}
//
//	private static Bitmap saveImg(Bitmap b, File file) throws Exception {
////		File f = new File(imgPath);
////		if (f != null && f.exists()) {
////			f.delete();
////		}
////		File newFile = new File(imgPath);
//		if (!new File(img_Identity_Address).exists()) {
//			new File(img_Identity_Address).mkdirs();
//		}
////		newFile.createNewFile();
//		FileOutputStream fos = new FileOutputStream(file);
//		int option = compressImage(b);
//		b.compress(Bitmap.CompressFormat.JPEG, option, fos);
//		fos.flush();
//		fos.close();
////		b.recycle();
////		b = null;
////		int ag = b.getRowBytes() * b.getHeight();
////		Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());// 此时返回bm为空
////		int bg = bitmap.getRowBytes() * bitmap.getHeight();
////		System.out.println(ag+","+bg);
//		return b;
//	}

	// 判断密码格式是否正确
	public static boolean isPassWordNO(String password) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9]{6,16}$");
		Matcher m = p.matcher(password);
		return m.matches();
	}

	// 判断手机格式是否正确
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((\\+86)?|\\(\\+86\\))0?1[34578]\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断姓名格式是否正确
	public static boolean isName(String mobiles) {
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{0,}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断身份证号格式是否正确
	public static boolean isIdentity(String mobiles) {
		Pattern p = Pattern.compile("^(\\d{14}|\\d{17})(\\d|[xX])$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断银行卡号格式是否正确
	public static boolean isBankCardNO(String mobiles) {
		Pattern p = Pattern.compile("^(\\d{15,20})");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断是否登录
	public static boolean isLogin(Context context) {
		String token = SharedPreferencesUtil.getValue(context, "token", "");
		if (!TextUtils.isEmpty(token)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 为用户名添加掩码
	 * 
	 * @param source
	 * @return
	 */
	public static String addMaskToTelephone(String source) {
		String destination = "";
		String elseString = source;
		int lenth = elseString.length();
		if (lenth > 7) {
			destination = elseString.substring(0, 3);
			int maskNum = lenth - 7;
			while ((maskNum--) > 0) {
				destination += "*";
			}
			destination += elseString.substring(lenth - 4, lenth);

		} else {
			destination = elseString;
		}
		return destination;
	}

	/**
	 * 为银行卡添加掩码
	 * 
	 * @param source
	 * @return
	 */
	public static String addMaskToBankcard(String source) {
		String destination = "";
		String elseString = source;
		int lenth = elseString.length();
		if (lenth > 8) {
			destination = elseString.substring(0, 4);
			int maskNum = lenth - 8;
			while ((maskNum--) > 0) {
				destination += "*";
			}
			destination += elseString.substring(lenth - 4, lenth);
		} else {
			destination = elseString;
		}
		return destination;
	}

	// 得到用户名
	public static String getName(Context context) {
		String peoplename = SharedPreferencesUtil.getValue(context,
				"peoplename", "");
		return peoplename;
	}

	private static long lastClickTime;

	public static boolean isDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 安全退出流程
	 * */
	public static void safetyExit(Context context) {
		SharedPreferencesUtil.putValue(context, "token", "");
		SharedPreferencesUtil.putValue(context, "userguide", false);// 关闭用户引导
		SharedPreferencesUtil.putValue(context, "isfirst1", true);
		SharedPreferencesUtil.putValue(context, "isfirst2", true);

	}
	public static String checkSDCardErrorReason(String state, Context context) {
		return state;
		/*
		if (state.equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
			return context.getString(R.string.text_sdcard_media_mounted);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return context
					.getString(R.string.text_sdcard_media_mounted_read_only);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_REMOVED)) {
			return context.getString(R.string.text_sdcard_media_removed);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_SHARED)) {
			return context.getString(R.string.text_sdcard_media_shared);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_BAD_REMOVAL)) {
			return context.getString(R.string.text_sdcard_media_bad_removal);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_NOFS)) {
			return context.getString(R.string.text_sdcard_media_nofs);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTABLE)) {
			return context.getString(R.string.text_sdcard_media_unmountable);
		} else if (state.equalsIgnoreCase(Environment.MEDIA_UNMOUNTED)) {
			return context.getString(R.string.text_sdcard_media_unmounted);
		} else {
			return context.getString(R.string.text_sdcard_media_other);
		}
		*/
		
	}
}
