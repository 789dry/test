package com.hstong.AutomationTest.api;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 接口平台参数
 * @author renyou.deng
 *
 */
public class InterfaceParams {
	String sign;  	 //签名
	String sign_time;  //签名时间，有效期为2min
	String client_key; //第三方访问id
	String token;	 //授权登陆凭证
	String buzz; //业务参数
	
	public InterfaceParams() {
		super();
	}
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
