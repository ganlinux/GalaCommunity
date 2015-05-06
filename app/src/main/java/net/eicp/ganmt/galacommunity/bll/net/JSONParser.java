package net.eicp.ganmt.galacommunity.bll.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.Environment;
import android.util.Log;

import net.eicp.ganmt.galacommunity.tools.MsLog;

public class JSONParser {

	/**
	 * HttpClient请求网络协议
	 * 弃用
	 * */
	static InputStream is = null;
	static JSONArray jObj = null;
	static String json = "";
	private MsLog mslog;
	// constructor
	public JSONParser() {
		mslog = new MsLog(this);
	}

	// function get json from url
	// by making HTTP POST or GET mehtod List<NameValuePair> params
	public String makeHttpRequest(String url, String method,
			String params) {

		// Making HTTP request
		try {
			
			// check for request method
			if(method == "POST"){
				// request method is POST
				// defaultHttpClient	 
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				
				httpPost.setEntity(new StringEntity(params, "UTF-8"));
//				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				
			}else if(method == "GET"){
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
//				String paramString = URLEncodedUtils.format(params, "utf-8");
//				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			if(sb==null){
				return null;
			}
			
			json=null;
			json = sb.toString().trim();
			
		} catch (Exception e) {
			mslog.e( e.toString());
		}

		// try parse the string to a JSON object
//		try {
//			
//			 jObj = new JSONArray(json); 
//			 
//		} catch (JSONException e) {
//			Log.e("JSON Parser", "Error parsing data " + e.toString());
//			return null;
//		}

		// return JSON String
		return json;

	}
}
