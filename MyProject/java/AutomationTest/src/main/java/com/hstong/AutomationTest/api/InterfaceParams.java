package com.hstong.AutomationTest.api;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 接口平台参数
 * @author renyou.deng
 *
 */
public class InterfaceParams {
	JSONObject base; //平台参数
	JSONObject buzz; //业务参数
	String format;	 //发送格式
	String sign;  	 //签名
	
	public InterfaceParams() {
		super();
		this.base=new JSONObject();
		this.base.put(key, value);
		this.buzz = buzz;
		this.format = "json";
		this.sign = "FUvINnDALCpaxUeuU90pV3YgR/0=";
	}
	
	public JSONObject getBuzz() {
		return buzz;
	}


	public void setBuzz(JSONObject buzz) {
		this.buzz = buzz;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
