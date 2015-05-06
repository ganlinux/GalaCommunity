package net.eicp.ganmt.galacommunity.bll.net;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;


import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;

import com.google.gson.Gson;

import net.eicp.ganmt.galacommunity.app.GalaApplication;
import net.eicp.ganmt.galacommunity.tools.ConstantTool;
import net.eicp.ganmt.galacommunity.tools.Utils;

public class GetJsonRequest {
	public Context mcontext;
	public GetJsonRequest(Context context) {
		mcontext = context;
	}
	
	/**
	 * 
	 * 根据对象属�?�名获取属�?�的�?
	 * 
	 */
	 private Object getFieldValueByName(String fieldName, Object o) {  
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	   }
	/**
	 * 
	 * 通过反射将实体类获取字段，进行后续的排序加密，返回一个完整的json，用于请求服务器
	 * 
	 */
	public JSONObject getJsonObject(Object object) {
		Gson gson = new Gson();
		String j = gson.toJson(object);
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(j);
			HashMap<String, String> map = new HashMap<String, String>(); 
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String name = field.getName();
				Object value = getFieldValueByName(name, object);
				map.put(name, (String) value);
			}
			jsonObject.put("systemparameterinfo",
					getSystemParameterInfo(map));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 获取系统信息json
	 * 
	 */
	private JSONObject getSystemParameterInfo(HashMap<String, String> map) {
		JSONObject systemParameterInfo = new JSONObject();
		try {
			systemParameterInfo.put("appkey", ConstantTool.AppKey);
			systemParameterInfo.put("version", ConstantTool.net_version);
			systemParameterInfo.put("reqtime", System.currentTimeMillis());
			map.put("appkey", ConstantTool.AppKey);
			map.put("version", ConstantTool.net_version);
			map.put("reqtime", "" + System.currentTimeMillis());
			map.put("versiontype", android.os.Build.MODEL);
			map.put("versionnumber",GalaApplication.VERSIONCODE);
			map.put("deviceid", GalaApplication.DEVICEID);
			map.put("refid", "refid");
			map.put("extend", GalaApplication.EXTEND_NET);
			map.put("clientip", Utils.getHostIp(mcontext));
			// 通过Utils的算�? 获得sign
			systemParameterInfo.put("sign",
					Utils.getSign(map));
			JSONObject clientInfo = new JSONObject();
			clientInfo.put("versiontype", android.os.Build.MODEL);
			clientInfo.put("versionnumber",GalaApplication.VERSIONCODE);
			// 获取deviceId
			clientInfo.put("deviceid", GalaApplication.DEVICEID);
			clientInfo.put("refid", "test");
			clientInfo.put("extend", GalaApplication.EXTEND_NET);
			// 获取ip地址
			clientInfo.put("clientip",Utils.getHostIp(mcontext));
			systemParameterInfo.put("clientinfo", clientInfo);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return systemParameterInfo;
	}

}
