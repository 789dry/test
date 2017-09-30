package com.hstong.open.api;

import java.util.Arrays;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;


public class ResponseDataValidator {
	
	 public static void main(String []args) throws JSONException{
		 String []filterFileds = {"releasedDate","sid"};
		 String expectedStr="{\"data\":[{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/P5MJ-fykpysa3324560.jpg/w120h90l50t1829.jpg\",\"contentId\":17090510420784458,\"title\":\"擎天软件涨近2%获湖北省司法行政公共服务软件项目\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/FGQi-fykpyuf9136714.jpg/w120h90l50t18ec.jpg\",\"contentId\":17090510420727345,\"title\":\"万科企业升幅扩至6%冠国指获瑞信升目标\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/20170905/UZRN-fykpysa3326512.jpg/w120h90l50t17cc.jpg\",\"contentId\":17090510420670015,\"title\":\"兴业证券:毛利率有所下降积极布局电池新领域\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/20170905/ouqy-fykpyua5605040.jpg/w120h90l50t1ff4.jpg\",\"contentId\":17090510420753354,\"title\":\"兴业证券:华电福新各版块均预期向好上调至买入\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/nMRS-fykpysa3326592.jpg/w120h90l50t12d7.jpg\",\"contentId\":17090510420649926,\"title\":\"新高教早前指新生人数超预期现升6%\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170216/pbBG-fyarrcf4166260.png/w120h90l50t19a9.jpg\",\"contentId\":17090510420615135,\"title\":\"快讯：融创中国股价上涨超6%创上市以来新高\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/crawl/20170905/SO7a-fykqmrv9806395.jpg/w120h90l50t11f1.jpg\",\"contentId\":17090510420003199,\"title\":\"让人意外苹果证实今年将会停办苹果音乐节\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/crawl/20170905/c_g2-fykpysa3328542.jpg/w120h90l50t1133.jpg\",\"contentId\":17090510415973000,\"title\":\"限制旅游？西班牙巴利阿里群岛加倍征收游客过夜税\"},{\"releasedDate\":\"50分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20160818/McEN-fxuxwyx8907327.jpg/w120h90l50t1bb6.jpg\",\"contentId\":17090510415949073,\"title\":\"巴西走出史上最严重衰退\"},{\"releasedDate\":\"1小时前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170904/Zsig-fykpysa3040796.jpg/w120h90l50t1c66.jpg\",\"contentId\":17090510215871794,\"title\":\"融创中国昨日北水续录净流出仍升4%\"}],\"ro\":{\"result\":{},\"respMsg\":\"处理成功\",\"ok\":true,\"respCode\":\"0000\",\"sid\":\"\"}}";
		 String actualStr="{\"data\":[{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/P5MJ-fykpysa3324560.jpg/w120h90l50t1829.jpg\",\"contentId\":17090510420784458,\"title\":\"擎天软件涨近2%获湖北省司法行政公共服务软件项目\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/FGQi-fykpyuf9136714.jpg/w120h90l50t18ec.jpg\",\"contentId\":17090510420727345,\"title\":\"万科企业升幅扩至6%冠国指获瑞信升目标\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/20170905/UZRN-fykpysa3326512.jpg/w120h90l50t17cc.jpg\",\"contentId\":17090510420670015,\"title\":\"兴业证券:毛利率有所下降积极布局电池新领域\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/20170905/ouqy-fykpyua5605040.jpg/w120h90l50t1ff4.jpg\",\"contentId\":17090510420753354,\"title\":\"兴业证券:华电福新各版块均预期向好上调至买入\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170905/nMRS-fykpysa3326592.jpg/w120h90l50t12d7.jpg\",\"contentId\":17090510420649926,\"title\":\"新高教早前指新生人数超预期现升6%\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170216/pbBG-fyarrcf4166260.png/w120h90l50t19a9.jpg\",\"contentId\":17090510420615135,\"title\":\"快讯：融创中国股价上涨超6%创上市以来新高\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/crawl/20170905/SO7a-fykqmrv9806395.jpg/w120h90l50t11f1.jpg\",\"contentId\":17090510420003199,\"title\":\"让人意外苹果证实今年将会停办苹果音乐节\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/crawl/20170905/c_g2-fykpysa3328542.jpg/w120h90l50t1133.jpg\",\"contentId\":17090510415973000,\"title\":\"限制旅游？西班牙巴利阿里群岛加倍征收游客过夜税\"},{\"releasedDate\":\"54分钟前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20160818/McEN-fxuxwyx8907327.jpg/w120h90l50t1bb6.jpg\",\"contentId\":17090510415949073,\"title\":\"巴西走出史上最严重衰退\"},{\"releasedDate\":\"1小时前\",\"titleImg\":\"http://k.sinaimg.cn/n/finance/transform/20170904/Zsig-fykpysa3040796.jpg/w120h90l50t1c66.jpg\",\"contentId\":17090510215871794,\"title\":\"融创中国昨日北水续录净流出仍升4%\"}],\"ro\":{\"result\":{},\"respMsg\":\"处理成功\",\"ok\":true,\"respCode\":\"0000\",\"sid\":\"\"}}";
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
