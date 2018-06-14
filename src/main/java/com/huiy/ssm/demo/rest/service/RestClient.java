package com.huiy.ssm.demo.rest.service;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiy.ssm.demo.persist.po.UserTPO;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年7月28日
 * @version 1.0
 *
 *
 */
public class RestClient {
	
	public void fetchFacebookProfile(String id){
		HttpGet request = new HttpGet("http://192.168.199.31:8080/ssmdemo/rest/"+id);
		request.setHeader("Accept", "application/json");
		HttpResponse response = null;
		try {
			response = getHttpClient().execute(request);
			if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
//				HttpEntity entity = response.getEntity();
				ObjectMapper om = new ObjectMapper();//将响应映射为对象
//				UserTPO UPO = om.readValue(entity.getContent(),UserTPO.class);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void fetchFaceBookProfileByTemplateG(String id){
		 RestTemplate rest = new RestTemplate();
		 rest.getForObject("http://192.168.199.31:8080/ssmdemo/rest/{user}",UserTPO.class,id);
	}
	
	public void fetchFaceBookProfileByTemplateE(String id){
		 RestTemplate rest = new RestTemplate();
		 MultiValueMap<String,String> headers = new LinkedMultiValueMap<String,String>();
		 headers.set("Accept", "application/json");
		 HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		 rest.exchange("http://192.168.199.31:8080/ssmdemo/rest/{user}", HttpMethod.GET, requestEntity,UserTPO.class,id);
	}
	
	/**
	 * HttpClient
	 * @return
	 */
	private HttpClient getHttpClient(){
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams
				.setUserAgent(params,
						"Mozilla/5.0 (Windows NT 6.1; rv:20.0) Gecko/20100101 Firefox/20.0");
		return wrapClient(new DefaultHttpClient(params));
	}
	
	public static HttpClient wrapClient(HttpClient base) 
	{
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] chain,
	                    String authType) throws CertificateException
	            {

	            }

	            public void checkServerTrusted(X509Certificate[] chain,
	                    String authType) throws CertificateException
	            {

	            }

	            public X509Certificate[] getAcceptedIssuers()
	            {
	                return null;
	            }

	        };
	        ctx.init(null, new TrustManager[] { tm }, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        //设置要使用的端口，默认是443
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	 }
	
	public static void main(String[] args){
		RestClient rclient = new RestClient();	
		rclient.fetchFacebookProfile("1");
	}
}
