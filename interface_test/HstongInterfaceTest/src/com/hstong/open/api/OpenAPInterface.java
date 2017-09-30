package com.hstong.open.api;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.hstong.open.api.util.ThreeDesUtil;

public class OpenAPInterface {
	private final static String APPSECRET = "zf7HxnNBg2o8fnCf2";
	
	/**
	 * 获取签名
	 * @param client_key
	 * @param params
	 * @return
	 */
	public static String getSign(String client_key,Map<String, Object> params){
		StringBuffer sb = new StringBuffer();
		sb.append("client_key").append(client_key);
		
		params.entrySet().stream()
		.sorted(Map.Entry.comparingByKey())
		.forEach(e -> {
			String k = e.getKey();
			String v = StringUtils.defaultString(e.getValue()+"");
			if (StringUtils.equals("client_key", k) || StringUtils.equals("sign", k)) {
				return ;
			}
			sb.append(k).append(v);
		});
		sb.append("client_secret").append(APPSECRET);
		System.out.println("String is:"+sb.toString());
		String resign = DigestUtils.md5Hex(sb.toString());
		return resign;
	}
	/**
	 * 获取签名
	 * @param client_key
	 * @param params
	 * @return
	 */
	public static String getSign2(String client_key,String appSecret,Map<String, Object> params){
		StringBuffer sb = new StringBuffer();
		sb.append("client_key").append(client_key);
		
		params.entrySet().stream()
		.sorted(Map.Entry.comparingByKey())
		.forEach(e -> {
			String k = e.getKey();
			String v = StringUtils.defaultString(e.getValue()+"");
			if (StringUtils.equals("client_key", k) || StringUtils.equals("sign", k)) {
				return ;
			}
			sb.append(k).append(v);
		});
		sb.append("client_secret").append(appSecret);
		System.out.println("String is:"+sb.toString());
		String resign = DigestUtils.md5Hex(sb.toString());
		return resign;
	}
	public static String getEncryptedPwd(String password){
		return ThreeDesUtil.encode(APPSECRET, password);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
