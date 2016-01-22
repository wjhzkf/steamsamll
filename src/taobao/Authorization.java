package taobao;

public class Authorization {
	private String testUrl = "http://gw.api.tbsandbox.com/router/rest";// 沙箱
	private String url = "http://gw.api.taobao.com/router/rest";
	private String appkey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
	private String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥
	private String sessionKey = "6200725886354102840c4b1c9484287b3ZZfe0d1aeaacfa693314641";
	private String taobaoApiName = "taobao.trade.get"; // 交易类api
	private String startTime_str;	//2010-03-08 14:59:30.252
	private String endTime_str;
	private String redirect_uri = "www.d3buy.net";
	private String tbPostSessionUrl = "https://oauth.taobao.com/token";

public String getTestUrl() {
	return testUrl;
}
public void setTestUrl(String testUrl) {
	this.testUrl = testUrl;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getAppkey() {
	return appkey;
}
public void setAppkey(String appkey) {
	this.appkey = appkey;
}
public String getAppSecret() {
	return appSecret;
}
public void setAppSecret(String appSecret) {
	this.appSecret = appSecret;
}
public String getTaobaoApiName() {
	return taobaoApiName;
}
public void setTaobaoApiName(String taobaoApiName) {
	this.taobaoApiName = taobaoApiName;
}

public String getStartTime_str() {
	return startTime_str;
}

public void setStartTime_str(String startTime_str) {
	this.startTime_str = startTime_str;
}

public String getEndTime_str() {
	return endTime_str;
}
public void setEndTime_str(String endTime_str) {
	this.endTime_str = endTime_str;
}
public String getSessionKey() {
	return sessionKey;
}
public void setSessionKey(String sessionKey) {
	this.sessionKey = sessionKey;
}
public String getTbPostSessionUrl() {
	return tbPostSessionUrl;
}
public void setTbPostSessionUrl(String tbPostSessionUrl) {
	this.tbPostSessionUrl = tbPostSessionUrl;
}
public String getRedirect_uri() {
	return redirect_uri;
}
public void setRedirect_uri(String redirect_uri) {
	this.redirect_uri = redirect_uri;
}}
