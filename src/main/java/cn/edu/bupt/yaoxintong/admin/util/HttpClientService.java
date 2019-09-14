package cn.edu.bupt.yaoxintong.admin.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpClientService {

//	//设置连接超时时间
//	private static Integer CONNECTION_TIMEOUT = 2 * 1000; //设置请求超时2秒钟 根据业务调整
//	private static Integer SO_TIMEOUT = 2 * 1000; //设置等待数据超时时间2秒钟 根据业务调整
//	//定义了当从ClientConnectionManager中检索ManagedClientConnection实例时使用的毫秒级的超时时间
//	//这个参数期望得到一个java.lang.Long类型的值。如果这个参数没有被设置，默认等于CONNECTION_TIMEOUT，因此一定要设置
//	private static Long CONN_MANAGER_TIMEOUT = 500L; //该值就是连接不够用的时候等待超时时间，一定要设置，而且不能太大 ()
//
//	private static DefaultHttpClient httpClient = SpringContextHolder.getBean(DefaultHttpClient.class);
//	PoolingClientConnectionManager pm = new PoolingClientConnectionManager();
//	static {
//		HttpParams params = new BasicHttpParams();
//		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECTION_TIMEOUT);
//		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
//		params.setLongParameter(ClientPNames.CONN_MANAGER_TIMEOUT, CONN_MANAGER_TIMEOUT);
//		//在提交请求之前 测试连接是否可用
//		params.setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, true);
//		httpClient.setParams(params);
//		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
//	}

//	
//    private CloseableHttpClient httpClient;
// 
//    private RequestConfig requestConfig;

	public String connect(String url, String params, String key) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
//			httpPost.setConfig(requestConfig);
			if (null != key) {
//				httpPost.addHeader("Content-Signature", "HMAC-SHA1 " + Base64.encode(HMACSHA1.getHmacSHA1(params, key), false));
			}
			httpPost.setEntity(new StringEntity(params));
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 得到httpResponse的实体数据
//			HttpEntity httpEntity = httpResponse.getEntity();
//			if (httpEntity != null) {
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
//				StringBuilder entityStringBuilder = new StringBuilder();
//				String line = null;
//				while ((line = bufferedReader.readLine()) != null) {
//					entityStringBuilder.append(line);
//				}
//				httpPost.releaseConnection();
//				return entityStringBuilder.toString();
//			}
				return EntityUtils.toString(httpResponse.getEntity());
			}
		} finally {
			if(null != httpResponse) {
				httpResponse.close();
			}
		}
		return null;
	}

	public String postForm(String url, Map<String, String> headers, Map<String, Object> params) throws IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
//			httpPost.setConfig(requestConfig);
//			httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
			if(null != headers) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			List nvps = new LinkedList<BasicNameValuePair>();
			if(null != params){
				Set<String> keySet = params.keySet();
				for(String key : keySet) {
					nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//			httpPost.setHeader(HTTP.CONTENT_LEN, String.valueOf(httpPost.getEntity().getContentLength()));
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 得到httpResponse的实体数据
	//			HttpEntity httpEntity = httpResponse.getEntity();
	//			if (httpEntity != null) {
	//				BufferedReader bufferedReader = new BufferedReader(
	//						new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
	//				StringBuilder entityStringBuilder = new StringBuilder();
	//				String line = null;
	//				while ((line = bufferedReader.readLine()) != null) {
	//					entityStringBuilder.append(line);
	//				}
	//				httpPost.releaseConnection();
	//				return entityStringBuilder.toString();
	//			}
				return EntityUtils.toString(httpResponse.getEntity());
			}
		} finally {
			if(null != httpResponse) {
				httpResponse.close();
			}
		}
		return null;
	}

	public String postForm(String url, Map<String, String> headers, String params) throws IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
//			httpPost.setConfig(requestConfig);
//			httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
			if(null != headers) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			}
			httpPost.setEntity(new StringEntity(params, HTTP.UTF_8));
//			httpPost.setHeader(HTTP.CONTENT_LEN, String.valueOf(httpPost.getEntity().getContentLength()));
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 得到httpResponse的实体数据
	//			HttpEntity httpEntity = httpResponse.getEntity();
	//			if (httpEntity != null) {
	//				BufferedReader bufferedReader = new BufferedReader(
	//						new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
	//				StringBuilder entityStringBuilder = new StringBuilder();
	//				String line = null;
	//				while ((line = bufferedReader.readLine()) != null) {
	//					entityStringBuilder.append(line);
	//				}
	//				httpPost.releaseConnection();
	//				return entityStringBuilder.toString();
	//			}
				return EntityUtils.toString(httpResponse.getEntity());
			}
		} finally {
			if(null != httpResponse) {
				httpResponse.close();
			}
		}
		return null;
	}
	
	public boolean connect(String url) throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		boolean b=false;
		CloseableHttpResponse httpResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
//			httpPost.setConfig(requestConfig);
			httpResponse  = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200){
				b=true;
			}
		} finally {
			if(null != httpResponse) {
				httpResponse.close();
			}
		}
		return b;
	}
}
