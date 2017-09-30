package com.hstong.AutomationTest.test;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.huasheng.open.api.util.HttpClient;
import com.huasheng.open.api.util.ThreeDesUtil;

/**
 * open api 交易接口测试用例
 *
 */
public class StockTradeCleintTest {
	
	public HttpClient httpClient = HttpClient.createHttpsClient();
	
//	private final static String httpDmain = "http://localhost:18080";
	private final static String httpDmain = "https://opendaily.hstong.com";
	private final static String APPID = "10002";
	private final static String APPSECRET = "zf7HxnNBg2o8fnCf2";
	
	// 测试交易账户  15612345678/a111111   10000015/123456   15611112222/a111111
	private final static String stock_account = "10000015";
	private final static String stock_password = "123456";
	
	
	private static String TOKEN = "7c5a2bcd7b964b58a0c7b0bf95182e92";
	
	/**
	 * 登录交易
	 */
	@Test
	public void login(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", stock_account);
		params.put("password", ThreeDesUtil.encode(APPSECRET, stock_password)); 
//		params.put("token", TOKEN);
		params.put("remoteAddr", "192.168.66.74");
		
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/login", params);
		
		System.out.println("response： "+responseBody); 
		
	}
	
	/**
	 * 资金信息
	 */
	@Test
	public void queryMarginFundInfo(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
//		params.put("stockCode", "00700");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryMarginFundInfo", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	/**
	 * 最大可买卖数量
	 */
	@Test
	public void queryMarginBuyAmount(){  
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("stockCode", "08096");//00543
		params.put("entrustPrice", "0.11");//1.99
		params.put("clientType", "1");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryMarginBuyAmount", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	/**
	 * {"entrustBs":"1","entrustPrice":"278.4","exchangeType":"K","entrustType":"2","entrustAmount":"100","stockCode":"00700"}
	 * 委托买卖
	 */
	@Test
	public void entrust(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("entrustBs", "1");
		params.put("entrustPrice", "355");
		params.put("exchangeType", "K");
		params.put("entrustType", "2");
		params.put("entrustAmount", "100");
		params.put("stockCode", "00700");
		params.put("clientType", "1");
		
		params.put("remoteAddr", "192.168.66.74");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/entrust", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 撤单
	 */
	@Test
	public void cancelEntrust(){  
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("stockCode", "00700");
		params.put("entrustPrice", "355");
		params.put("entrustAmount", "100");
		params.put("entrustId", "59");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/cancelEntrust", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 持仓
	 */
	@Test
	public void queryHoldsList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryHoldsList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	
	/**
	 * 持仓资金信息
	 */
	@Test
	public void queryHoldsFundInfo(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "P");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryHoldsFundInfo", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 可撤单列表
	 */
	@Test
	public void queryCancelList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryCancelList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 当日委托
	 */
	@Test
	public void queryRealEntrustList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("queryParamStr", "0");
		params.put("queryCount", "20");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryRealEntrustList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 历史委托
	 */
	@Test
	public void queryHistoryEntrustList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("queryParamStr", "0");
		  params.put("queryCount", "3");
		params.put("startDate", "");
		params.put("endDate", "");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryHistoryEntrustList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 当日成交
	 */
	@Test
	public void queryRealDeliverList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("queryParamStr", "0");
		params.put("queryCount", "20");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryRealDeliverList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	/**
	 * 历史成交
	 */
	@Test
	public void queryHistoryDeliverList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("queryParamStr", "0");
		params.put("queryCount", "4");
		params.put("startDate", ""); //20170401
		params.put("endDate", "");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryHistoryDeliverList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	/**
	 * 当日资金流水
	 */
	@Test
	public void queryRealFundJourList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("queryParamStr", "0");
		params.put("queryCount", "20");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryRealFundJourList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	/**
	 * 历史资金流水
	 */
	@Test
	public void queryHistoryFundJourList(){ 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("token", TOKEN);
		
		// 业务参数
		params.put("exchangeType", "K");
		params.put("fundJourParentTypes", "1,2"); // 参考 FundJourParentType.java
		params.put("queryParamStr", "0");
		params.put("queryCount", "20");
		params.put("startDate", "20170401");
		params.put("endDate", "20170713");
		
		paramBuild(params);
		
		String responseBody =  doHttpsPost("/stock/queryHistoryFundJourList", params);
		
		System.out.println("response： "+responseBody); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String doHttpsPost(String uri, Map<String, Object> params) {
		try {
			return httpClient.doHttpsPost(httpDmain+uri, params);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 生成基础参数 时间戳签名等
	 * 
	 * @param param
	 * @return
	 */
	private static void paramBuild(Map<String, Object> param) {
		
		// 固定参数
		param.put("client_key", APPID);
		param.put("sign_time", Instant.now().getEpochSecond()+"");
		
		// 签名
		String sign =  sign(param);
		
		param.put("sign", sign);
		
		
	}
	
	
	private static String sign(Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		sb.append("client_key").append(APPID);
		
		param.entrySet().stream()
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
		
		
		String resign = DigestUtils.md5Hex(sb.toString());
		return resign;
	}
	
	public static void main(String[] args) {
		String resign = DigestUtils.md5Hex("client_key10002account15612345678passwordrbk/KDujhV8=sign_time1501136391client_secretzf7HxnNBg2o8fnCf2");
		System.out.println(resign);

	}
	
	

}
