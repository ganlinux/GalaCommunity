package net.eicp.ganmt.galacommunity.bll.net;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;


import org.json.JSONException;
import org.json.JSONObject;




import android.content.Context;
import android.os.AsyncTask;

import net.eicp.ganmt.galacommunity.tools.MsLog;
import net.eicp.ganmt.galacommunity.tools.Utils;

/**
 * 主要负责网络的异步请�?
 * @author pengqm
 *
 */
public class RequestAsyncTask extends AsyncTask<String, Integer, String>{
	private String ip;
	private Object jsonObject;
	private ResponseCallBack callBack1;
	private GetJsonRequest getJsonRequest;
	private UrlConnection_Request urlConnection_Req;
	private ArrayList<File> arrFile = null;
	private Context mcontext;
	private MsLog mslog;
	public RequestAsyncTask(Context context, String ip, Object jsonObject,ResponseCallBack callBack1) {
		this.ip = ip;
		this.jsonObject = jsonObject;
		this.callBack1 = callBack1;
		mcontext = context;
		mslog = new MsLog(this);
		getJsonRequest = new GetJsonRequest(context);
		urlConnection_Req  = new UrlConnection_Request();
	}
	public void setArrayFile( ArrayList<File>Files){
		arrFile = Files;
		
	}
	@Override
	protected String doInBackground(String... arg0) {
		String jsonarr=null;
		try{
			JSONObject json = getJsonRequest.getJsonObject(jsonObject);
			mslog.e(json.toString());
			//如果arrFile等于null 表示普�?�请求数据，否则带图片请求�??
			if (arrFile != null) {
				Map map = Utils.parseJSON2Map(json.toString());
				jsonarr = UrlConnection_Request_PD.uploadSubmit(ip, map, arrFile);
			}else{
				 jsonarr = urlConnection_Req.makeHttpRequest(ip, Utils.post, json.toString());
			}	
		}catch(Exception e){
//			e.printStackTrace();
//			publishProgress(0);
			
		}
		return jsonarr;
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
			if (result!=null) {
				if (result.equals(Utils.LOCALERRO_NETTIMEROUT)) {
					//网络超时
					callBack1.onErrorResponse(Utils.LOCALERRO_NETTIMEROUT);
				}else{
					try {
						JSONObject jb;
						jb = new JSONObject(result);
						callBack1.onResponse(jb);
					} catch (JSONException e) {
						e.printStackTrace();
						//网络异常
						callBack1.onErrorResponse(Utils.LOCALERROR_UNUSUAL);
					} 
				}
				
			}else{
				//无网络连�?
				 callBack1.onErrorResponse(Utils.LOCALERRO_NONETWORK);
			}
	}
	public interface ResponseCallBack {
		public void onResponse(JSONObject response);
		public void onErrorResponse(String error);
	}
}

