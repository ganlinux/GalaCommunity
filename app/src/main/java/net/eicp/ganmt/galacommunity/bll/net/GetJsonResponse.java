package net.eicp.ganmt.galacommunity.bll.net;



import com.google.gson.Gson;

import org.json.JSONObject;


import net.eicp.ganmt.galacommunity.bll.net.model.response.ResponseSimple;

public class GetJsonResponse {
	private Gson gson;

	public GetJsonResponse() {
		gson = new Gson();
	}


	/**
	 * 
	 * 查看已获得佣金明细信息接口返回�??
	 */
	public ResponseSimple jsonToGetDoYongJin(JSONObject jsonObject) {
		ResponseSimple responseSimple = gson.fromJson(jsonObject.toString(),
				ResponseSimple.class);
		return responseSimple;
	}
}
