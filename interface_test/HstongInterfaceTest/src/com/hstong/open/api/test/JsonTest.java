package com.hstong.open.api.test;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String testStr="{\"result\":{\"account\":\"10000051\",\"nikeName\":\"505027\",\"tradePerm\":\"2\",\"hkMarginStatus\":\"0\",\"token\":\"1ee81387356f4e54b6f646e0d18d6742\",\"mid\":\"3611\"},\"code\":\"00000\"}";
		String testStr = "{\"code\":\"10104\",\"error\":\"Î´Öª¿Í»§\"}";
		try {
			JSONObject jsonObj = new JSONObject(testStr);
			if(testStr.indexOf("token") != -1){
				JSONObject resultObj = jsonObj.getJSONObject("result"); 
				System.out.println(resultObj.get("token"));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
