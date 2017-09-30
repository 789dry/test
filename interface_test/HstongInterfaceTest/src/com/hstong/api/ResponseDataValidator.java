package com.hstong.api;

import java.util.Arrays;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;


public class ResponseDataValidator {
	
//	 public static void main(String []args) throws JSONException{
//		 String []filterFileds = {"sid","token"};
//		 String expectedStr="{\"bound\":true,\"member\":{\"usStockStatus\":\"0\",\"unreadMsgCount\":31,\"nickName\":\"拉风的骆驼\",\"mobile\":\"18600000050\",\"avatar\":\"http://q.qlogo.cn/qqapp/1105611323/65DDE6CEB8C260C1322A1014EA0545B8/100\",\"tid\":1449,\"canReadHq\":\"0\",\"stockAccount\":\"\",\"countryCode\":\"0086\",\"nickname\":\"拉风的骆驼\",\"pwdNotSet\":true,\"hsNo\":\"366055\",\"email\":\"\",\"status\":0},\"ro\":{\"result\":{},\"respMsg\":\"处理成功\",\"ok\":true,\"respCode\":\"0000\"},\"token\":\"\",\"sid\":\"\"}";
//		 String actualStr="{\"bound\":true,\"ro\":{\"result\":{},\"respMsg\":\"处理成功\",\"ok\":true,\"respCode\":\"0000\"}}";
//		 //validateJsonResponseDataByRo(expectedStr,actualStr);
//		 String []filterFileds2 = Arrays.copyOfRange(filterFileds, 1, filterFileds.length);
//		 for(int i=0;i<filterFileds2.length;i++){
//			 System.out.println(filterFileds2[i]);
//		 }
//		 Integer.parseInt("1");
//	 }
	 public static void main(String []args) throws JSONException{
		 String []filterFileds = {"sid","data"};
		 String expectedStr="{\"data\":[],\"ro\":{\"respCode\":\"0000\",\"respMsg\":\"处理成功\",\"sid\":\"\",\"result\":{},\"ok\":true}}";
		 String actualStr="{\"data\":[\"03188.HK\",\"华夏沪深三百\",\"10002\",\"2017-09-08 14:32\",\"47.100\",\"47.300\",\"47.550\",\"47.050\",\"47.300\",\"200\",\"47.550\",\"36.000\",\"67.200\",\"25.650\",\"7030474.000\",\"332500575.40\",\"267750000.000\",\"\",\"3\",\"0\",\"267750000.000\",\"\",\"HKEX\",\"0.000\",\"-4.81%\",\"398.9万\",\"288.9万\",\"0.05\",\"1\",\"0\",\"0\",\"0\"],\"ro\":{\"respCode\":\"0000\",\"respMsg\":\"处理成功\",\"sid\":\"ccf1494907604579a0950de24ba396c0-3706\",\"result\":{},\"ok\":true}}";
		 //validateJsonResponseDataByRo(expectedStr,actualStr);
		 /*
		 String []filterFileds2 = Arrays.copyOfRange(filterFileds, 1, filterFileds.length);
		 for(int i=0;i<filterFileds2.length;i++){
			 System.out.println(filterFileds2[i]);
		 }
		 Integer.parseInt("1");
		 */
		 validateJsonResponseData(expectedStr,actualStr,filterFileds);
	 }
	 
	 public static boolean validateJsonResponseDataByFileds(String expectJson, String actualJson,String[] fileds){
		 	JSONObject expectJsonObj,actualJsonObj,expectRoJsonObj,actualRoJsonObj;
			boolean ret = true;
			try {
				expectJsonObj = new JSONObject(expectJson.replaceAll(" ", ""));
				actualJsonObj = new JSONObject(actualJson.replaceAll(" ", ""));
				expectRoJsonObj = expectJsonObj.getJSONObject("ro");
				actualRoJsonObj = actualJsonObj.getJSONObject("ro");
				
				updateJsonObjectValue(expectJsonObj,"sid","");
				updateJsonObjectValue(actualJsonObj,"sid","");
				System.out.println("expectRoJsonObj:"+expectRoJsonObj.toString());
				System.out.println("actualRoJsonObj:"+actualRoJsonObj.toString());
				try{
					JSONAssert.assertEquals(expectRoJsonObj, actualRoJsonObj, false);
				}
				catch(AssertionError assertionError){ 
					//assertionError.printStackTrace();
					ret = false;
				}
				catch(JSONException jsonException){
					ret = false;
				}
			} catch (JSONException e) {
//				// TODO Auto-generated catch block
				e.printStackTrace();
				ret = false;
			}finally{
				System.out.println("Test result is:"+ret);
				return ret;
			}
	 }
	 
	 
	 /**
		 * 根据返回的ro来判断是否正确
		 * @param expectJson
		 * @param actualJson
		 * @param filterFileds
		 * @return
		 * @throws JSONException
		 */
		public static boolean validateJsonResponseDataByRo(String expectJson,String actualJson){
			JSONObject expectJsonObj,actualJsonObj,expectRoJsonObj,actualRoJsonObj;
			boolean ret = true;
			expectJson = expectJson.replaceAll(" ", "");
			actualJson = actualJson.replaceAll(" ", "");
			if(expectJson == null || actualJson == null)
				return false;
			if(expectJson.equals(actualJson)){
				System.out.println("expectJson:"+expectJson);
				System.out.println("actualJson:"+actualJson);
				return true;
			}else{
				try {
					expectJsonObj = new JSONObject(expectJson);
					actualJsonObj = new JSONObject(actualJson);
					expectRoJsonObj = expectJsonObj.getJSONObject("ro");
					actualRoJsonObj = actualJsonObj.getJSONObject("ro");
					
					updateJsonObjectValue(expectJsonObj,"sid","");
					updateJsonObjectValue(actualJsonObj,"sid","");
					System.out.println("expectRoJsonObj:"+expectRoJsonObj.toString());
					System.out.println("actualRoJsonObj:"+actualRoJsonObj.toString());
					try{
						JSONAssert.assertEquals(expectRoJsonObj, actualRoJsonObj, false);
					}
					catch(AssertionError assertionError){ 
						//assertionError.printStackTrace();
						ret = false;
					}
					catch(JSONException jsonException){
						ret = false;
					}
				} catch (JSONException e) {
//					// TODO Auto-generated catch block
					e.printStackTrace();
					ret = false;
				}finally{
					System.out.println("Test result is:"+ret);
					return ret;
				}
			}
		}
	/**
	 * 
	 * @param expectJson
	 * @param actualJson
	 * @param filterFileds
	 * @return
	 * @throws JSONException
	 */
	public static boolean validateJsonResponseData(String expectJson,String actualJson,String []filterFileds){
		JSONObject expectJsonObj,actualJsonObj;
		boolean ret = true;
		try {
			expectJsonObj = new JSONObject(expectJson.replaceAll(" ", ""));
			actualJsonObj = new JSONObject(actualJson.replaceAll(" ", ""));
			for(int i=0;i<filterFileds.length;i++){
				updateJsonObjectValue(expectJsonObj,filterFileds[i],"");
				updateJsonObjectValue(actualJsonObj,filterFileds[i],"");
			}
			System.out.println("expectJsonObj:"+expectJsonObj.toString());
			System.out.println("actualJsonObj:"+actualJsonObj.toString());
			try{
				JSONAssert.assertEquals(expectJsonObj, actualJsonObj, true);
			}
			catch(AssertionError assertionError){ 
				//assertionError.printStackTrace();
				ret = false;
			}
			catch(JSONException jsonException){
				ret = false;
			}
		} catch (JSONException e) {
//			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = false;
		}finally{
			System.out.println("Test result is:"+ret);
			return ret;
		}
	}
	/**
	 * 
	 * @param args ��һ������ΪexpectValue��������ַ���Ϊ��Ҫ���˵��ֶ�
	 * @param actualJson
	 * @return
	 * @throws JSONException
	 */
	public static boolean validateJsonResponseData(String []args,String actualJson) throws JSONException{
		JSONObject expectJsonObj,actualJsonObj;
		
		if(args.length < 1)
			return false;
		expectJsonObj = new JSONObject(args[0].replaceAll(" ", ""));
		actualJsonObj = new JSONObject(actualJson.replaceAll(" ", ""));
		for(int i=1;i<args.length;i++){
			updateJsonObjectValue(expectJsonObj,args[i],"");
			updateJsonObjectValue(actualJsonObj,args[i],"");
		}
		System.out.println("expectJsonObj:"+expectJsonObj.toString());
		System.out.println("actualJsonObj:"+actualJsonObj.toString());
		try{
			JSONAssert.assertEquals(expectJsonObj, actualJsonObj, false);
		}
		catch(AssertionError assertionError){
			//assertionError.printStackTrace();
			return false;
		}
		return true;
	}
	public static void updateJsonObjectValue(JSONObject obj,String key,Object newValue) throws JSONException{
		JSONObject json = new JSONObject();
	    Iterator iterator = obj.keys();

	    while (iterator.hasNext()) {
	    	String itrKey = (String) iterator.next();
	    	if(key.equals(itrKey)){
	    		obj.put(key,newValue);
	    	}else{
	    		if(obj.optJSONArray(itrKey) != null){
	    			JSONArray array = obj.getJSONArray(itrKey);
		    		int len = array.length();
		    		for(int i=0;i<len;i++){
		    			if(array.optJSONObject(i) != null){
		    				updateJsonObjectValue(array.getJSONObject(i),key,newValue);
		    			}
		    		}
	    		}
	    		if(obj.optJSONObject(itrKey) != null)
	    			updateJsonObjectValue(obj.getJSONObject(itrKey),key,newValue);
    		}
	    	
	    }
	}
}
