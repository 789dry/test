package com.hstong.open.api.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClient {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String utf8 = "utf-8";
	
	private static String userAgentName = "User-Agent";
	private static String userAgentValue = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0";
	private static String refererName = "Referer";
	private static String refererValue = "";
	
	private static final int CONNECTION_TIME_OUT=15000;
	
	private static final int CONNECTION_REQUEST_TIME_OUT=15000;
	
	private static final int SOCKET_TIME_OUT=15000;

	private String userAgent = null;
	private String referer = null;
	
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	private static TrustManager manager = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub

		}
	};

	private static PoolingHttpClientConnectionManager connectionManager = null;

	static {

		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[] { manager }, null);
			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(context,
					NoopHostnameVerifier.INSTANCE);
			
/*			  SSLContext context=null;
			try {
				context = SSLContexts.custom().loadTrustMaterial(null,  
				           new TrustSelfSignedStrategy())  
				   .build();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	   HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();  
	   SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(  
			   context,hostnameVerifier);*/
			
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
			connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

	}
	CloseableHttpClient httpsClient = null;

	private void create() {
		RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
				.setExpectContinueEnabled(true).setConnectTimeout(CONNECTION_TIME_OUT).setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
				.setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
				.setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
		httpsClient = HttpClients.custom().setConnectionManager(connectionManager).evictIdleConnections(30l, TimeUnit.SECONDS).evictExpiredConnections().setMaxConnTotal(100)
				.setDefaultRequestConfig(defaultRequestConfig).build();

	}
	
	public void close() {
		try {
			if(httpsClient != null) {
				httpsClient.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HttpClient() {
		create();
	}

	public static HttpClient createHttpsClient() {
		return new HttpClient();
	}



	/**
	 * post 涓嶅甫鍙傛暟璇锋眰锛� CloseableHttpResponse 闇�瑕佽皟鐢ㄦ柟鍏抽棴
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public CloseableHttpResponse doHttpsPost(String url) throws IOException {
		return doHttpsPost(url, (List<NameValuePair>) null);
	}







	public String doHttpsPost(String url, Map<String, Object> params) throws IOException {
		List<NameValuePair> values = new ArrayList<>();
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair p = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				values.add(p);
			}
		}
		CloseableHttpResponse response=doHttpsPost(url, values);
		HttpResult result=toResult(response);
		return ""+result.getStatus()+" "+result.getBody(); 

//		if(result.getStatus()==HttpStatus.SC_OK){
//			return result.getBody();
//		}else{
//			logger.info("httpClient post 璇锋眰澶辫触{}--{}",url,params);
//		}
//		return null;
	}


	public CloseableHttpResponse doHttpsPost(String url, List<NameValuePair> values) throws IOException {
		HttpPost post = new HttpPost(url);
		if (values != null) {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(values, Consts.UTF_8);
			post.setEntity(entity);
		}
		setCommonHeader(post);
		CloseableHttpResponse response = httpsClient.execute(post);
		return response;
	}




	public CloseableHttpResponse doHttpsPost(String url, String values) throws IOException {
		HttpPost post = new HttpPost(url);
		if (values != null) {
			StringEntity entity = new StringEntity(values, Consts.UTF_8);
			post.setEntity(entity);
		}
		setCommonHeader(post);
		CloseableHttpResponse response = httpsClient.execute(post);
		return response;
	}


	public CloseableHttpResponse doHttpsGet(String url) throws IOException {
		HttpGet get = new HttpGet(url);
		setCommonHeader(get);
		CloseableHttpResponse response = httpsClient.execute(get);
		return response;
	}


	private HttpResult toResult(CloseableHttpResponse response) throws IOException {
		HttpResult result = null;
		try {
			String body = EntityUtils.toString(response.getEntity(), HttpClient.utf8);
			result = new HttpResult();
			result.setBody(body);
			result.setStatus(response.getStatusLine().getStatusCode());
			if (logger.isDebugEnabled()) {
				logger.debug("https result: {}", result);
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return result;
	}


	private void setCommonHeader(HttpRequestBase request) {
		if(userAgent == null) {
			request.setHeader(userAgentName, userAgentValue);
		} else {
			request.setHeader(userAgentName, userAgent);
		}
		
	}
	public static class HttpResult {
		private String body;
		private Integer status;

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}


	}

	
}
