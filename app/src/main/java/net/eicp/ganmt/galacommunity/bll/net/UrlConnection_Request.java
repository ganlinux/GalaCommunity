package net.eicp.ganmt.galacommunity.bll.net;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;


import org.json.JSONArray;

import android.os.Build;

import net.eicp.ganmt.galacommunity.tools.Utils;

/**
 * 采用HttpUrlConnection请求服务�?
 * */
public class UrlConnection_Request {
	InputStream inputstream = null;
	JSONArray jObj = null;
	String result = null;
	public UrlConnection_Request  urlConnection_request = null;
	// constructor
	public UrlConnection_Request() {
		
	}
	// function get json from url
	// by making HTTP POST or GET mehtod List<NameValuePair> params
	public String makeHttpRequest(String url, String method,
			String params){
		HttpURLConnection urlConnection = null;
		// Making HTTP request
		try {
			// check for request method
//			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
//
//	            System.setProperty("http.keepAlive", "false");
//
//	        }
//
//			System.setProperty("http.keepAlive", "false");
			if(method == "POST"){
				// request method is POST
				 urlConnection = (HttpURLConnection) new URL(url).openConnection();
				 urlConnection.setConnectTimeout(40000);
				 urlConnection.setReadTimeout(40000);
				    urlConnection.setDoInput(true);  
				    urlConnection.setDoOutput(true);  
				    urlConnection.setRequestProperty("connection", "close"); 
				    urlConnection.setChunkedStreamingMode(0);  
				    urlConnection.setRequestMethod(method); 
				    urlConnection.setRequestProperty("Content-Type",
		                    "application/x-www-form-urlencoded");  
				    urlConnection.setRequestProperty("Charsert", "UTF-8");
//				    urlConnection.setRequestProperty("Accept-Encoding", "identity");
//				   urlConnection.setRequestProperty("Content-Length", String.valueOf(params.getBytes().length));  
				   OutputStream out = new  BufferedOutputStream(urlConnection.getOutputStream());  
				   out.write(params.getBytes("UTF-8"));
				   out.flush();
				   out.close();  
				    int responseCode = urlConnection.getResponseCode();    
				    if (responseCode == 200) {  
				    	inputstream = new BufferedInputStream(urlConnection.getInputStream());  
				    } else {  
				    	inputstream = new BufferedInputStream(urlConnection.getErrorStream());  
				    }  
			}else if(method == "GET"){
				// request method is GET
				
			}			
			result = Utils.convertStreamToString(inputstream).trim();
			inputstream.close();
			inputstream = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(SocketTimeoutException e){
			result = Utils.LOCALERRO_NETTIMEROUT;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {  
			   urlConnection.disconnect();  
			  
		} 
		// return JSON String
		return result;

	}
}
