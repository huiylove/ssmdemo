package com.huiy.ssm.demo.front.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.huiy.ssm.demo.front.service.ConvertorService;
import com.huiy.ssm.demo.front.service.EncryptService;
import com.huiy.ssm.demo.front.service.FrontService;
import com.huiy.ssm.demo.front.xmlModelMappding.request.UserReqMapping;
import com.huiy.ssm.demo.front.xmlModelMappding.response.UserResMapping;
import com.huiy.ssm.frame.core.exception.CommonException;

/** 
 * 类功能描述
 * @author : yuanhui 
 * @date   : 2017年2月24日
 * @version 1.0
 *
 *
 */
@Component("frontService")
public class FrontServiceImpl implements FrontService{
	
	private static Logger logger = LoggerFactory.getLogger(FrontServiceImpl.class);

	//@Value注解从资源文件中，取出它的key并赋值给当前类的成员变量
	@Value("${front.ip}")
	private String frontIp;
	@Value("${front.key}")
	private String frontKey;
	@Value("${front.url}")
	private String frontUrl;
	
	@Resource(name="encryptService")
	private EncryptService encryptService;
	
	@Resource(name="convertorService")
	private ConvertorService convertorService;
	
	@Value("${front.downImgUrl}")
	private String frontDownImgUrl;
	
	
	@Override
	public UserResMapping findUserBy(UserReqMapping req) {
		req.setAction("user_detail");
		String requData = convertorService.convertToXml(req);
		String resStr = null;
		try {
			resStr = post(requData);
		} catch (CommonException e) {
			throw e;
		}
		UserResMapping res = convertorService.converyToJavaBean(resStr, UserResMapping.class);
		return res;
	}
	
	
	/**
	 * post提交
	 * @return
	 */
	private String post(String reqData){
//		StringBuilder sb = new StringBuilder();
//		sb.append(reqData).append("|").append(frontIp).append("|").append(frontKey);
//		String cryptStr = sb.toString();
//		//MD5加密
//		String encodedStr = encryptService.frontMd5(cryptStr);
		HttpPost post = null;
		post = new HttpPost(frontUrl);
		//向URL发送post请求，并用list来存放参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("reqData",reqData));
//		nvps.add(new BasicNameValuePair("md5",encodedStr));
		try {
//			post.setHeader("accept","application/xml");
			post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));//设置参数		       
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpResponse response = null;
		try {
			response = getHttpClient().execute(post);
			
			String resStr = null;
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				HttpEntity entity = response.getEntity();
				try {
					resStr = EntityUtils.toString(entity,"UTF-8");
				} catch (ParseException e) {
					throw new CommonException("HTTP响应数据转换发生错误", e);
				} catch (IOException e) {
					throw new CommonException("读取数据发生错误", e);
				}
			}
			return new String(resStr.getBytes("ISO-8859-1"),"utf-8");
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocol : ", e);
			throw new CommonException("不支持该协议", e);
		} catch (IOException e) {
			logger.error("IOE : ", e);
			throw new CommonException("读取数据发生错误", e);
		}
	}
	
	
	/**
	 * get提交
	 * @param dir
	 * @param imgName
	 * @param userId
	 */
	public void downLoadImg(String dir, String imgName, String userId){
		String filePath = dir + File.separator + imgName;
		String imgUrl = frontDownImgUrl + "?fileType=2&path=" + imgName + "&fileLastDic=" + userId;
		
		HttpGet get = new HttpGet(imgUrl);
		 
		HttpResponse res;
		try {
			res = getHttpClient().execute(get);
			if(HttpStatus.SC_OK == res.getStatusLine().getStatusCode()){
				HttpEntity entity = res.getEntity();
				if(entity!=null){
					InputStream stream = entity.getContent();
					FileOutputStream fos = new FileOutputStream(new File(filePath));
					byte[] b = new byte[1024];
					int read ;
					while((read=stream.read(b,0,10247))!=-1){
						fos.write(b,0,read);
					}
					fos.flush();//将缓冲区的数据刷到目的地中后，流可以使用
					fos.close();//将缓冲区的数据刷到目的地中后，流就关闭了，该方法主要用于结束调用的底层资源。这个动作一定做。
				}
			}
		} catch (ClientProtocolException e) {
			throw new CommonException("不支持该协议", e);		
		} catch (IOException e) {
			throw new CommonException("读取数据发生错误", e);		
		}
		
		
		
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
	                // TODO Auto-generated method stub

	            }

	            public X509Certificate[] getAcceptedIssuers()
	            {
	                // TODO Auto-generated method stub
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

}
