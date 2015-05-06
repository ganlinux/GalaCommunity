package net.eicp.ganmt.galacommunity.bll.net.model.request;

public class ClientInfo {
	private String versiontype;
	private String versionnumber;
	private String deviceid;
	private String refid;
	private String extend;
	private String clientip;
	public String getVersiontype() {
		return versiontype;
	}
	public void setVersiontype(String versiontype) {
		this.versiontype = versiontype;
	}
	public String getVersionnumber() {
		return versionnumber;
	}
	public void setVersionnumber(String versionnumber) {
		this.versionnumber = versionnumber;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getRefid() {
		return refid;
	}
	public void setRefid(String refid) {
		this.refid = refid;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getClientip() {
		return clientip;
	}
	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
}
