package net.eicp.ganmt.galacommunity.bll.net;

import java.io.File;
import java.util.ArrayList;


import org.json.JSONObject;

import android.content.Context;

/**
 * 连接View层与网络交互的中间层
 * @author pengqm
 *
 */
public class RequestNet {
	public static RequestNet requestnet = null;
	public static RequestNet getRequestNet(){
		if (requestnet == null) {
			requestnet = new RequestNet();
		}
		return requestnet;
	}
	/**
	 * 请求网络数据
	 * */
	public RequestAsyncTask RequestData(Context context,String ip,Object jsonObject,RequestAsyncTask.ResponseCallBack callBack1){
		RequestAsyncTask asynctask = new RequestAsyncTask(context, ip, jsonObject, callBack1);
		asynctask.execute();
		return asynctask;
	}
	/**
	 * 上传图片和文字数�?
	 * */
	public RequestAsyncTask UploadPic_Data(Context context,String ip,Object jsonObject,RequestAsyncTask.ResponseCallBack callBack1,ArrayList<File> arrFile){
		RequestAsyncTask asynctask = new RequestAsyncTask(context, ip, jsonObject, callBack1);
		asynctask.setArrayFile(arrFile);
		asynctask.execute();
		return asynctask;
	}

}
