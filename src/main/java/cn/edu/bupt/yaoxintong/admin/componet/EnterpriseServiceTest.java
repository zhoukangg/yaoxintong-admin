package cn.edu.bupt.yaoxintong.admin.componet;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Component;

import cn.edu.bupt.yaoxintong.admin.util.HttpClientService;
import cn.edu.bupt.yaoxintong.admin.util.HMACSHA1;

/**
 * 企业综合验证
 * @author
 *
 */
@Component
public class EnterpriseServiceTest {
	
	private static final String URL = "http://124.205.224.179:9018/portal/enterprise/authIden";
	private static final String APP_ID = "9143f99e63b847";
	private static final String SECRET_KEY = "a17b30ba976641bdb5c7d0678bba3b1a";
//	private static final String URL = "https://ixin.itrus.com.cn/portal/enterprise/authIden";
//	private static final String APP_ID = "2616d70bd1da45";
//	private static final String SECRET_KEY = "ace2500102a642acbde99ab41cee29d1";
	
	// 营业执照认证
	public String licenseAuth(String businessName,String businessLicense) {
		
		String appId = APP_ID;
		String serviceCode = "idb0003";
		String type = "ET_PE";
		String name = businessName;
		String idCode = businessLicense;
		String orgCode = null;
		String legalName = null;
		String legalId = null;
		String legalUrl = null;
		String legalImg = null;
		String agentName = null;
		String agentId = null;
		String agentUrl = null;
		String agentImg = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", appId);
		param.put("serviceCode", serviceCode);
		param.put("name", name);
		param.put("type", type);
		param.put("idCode", idCode);
		
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			headers.put("Content-Signature", "HMAC-SHA1 " + new Base64().encodeToString(HMACSHA1.getHmacSHA1(appId+serviceCode+type+name+
					idCode+orgCode+legalName+legalId+legalUrl+legalImg+agentName+agentId+agentUrl+agentImg, SECRET_KEY)));
			String resStr = new HttpClientService().postForm(URL, headers, param);
			System.out.println(resStr);
			return resStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 营业执照+组织机构代码认证
	public static void organizationAuth() {
		
		String appId = APP_ID;
		String serviceCode = "idb0007";
		String type = "ET_PE";
		String name = "杭州坤博岩土工程科技有限公司";
		String idCode = "913301066680067018";
		String orgCode = "123456";
		String legalName = null;
		String legalId = null;
		String legalUrl = null;
		String legalImg = null;
		String agentName = null;
		String agentId = null;
		String agentUrl = null;
		String agentImg = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", appId);
		param.put("serviceCode", serviceCode);
		param.put("name", name);
		param.put("type", type);
		param.put("idCode", idCode);
		param.put("orgCode", orgCode);
		
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			headers.put("Content-Signature", "HMAC-SHA1 " + new Base64().encodeToString(HMACSHA1.getHmacSHA1(appId+serviceCode+type+name+
					idCode+orgCode+legalName+legalId+legalUrl+legalImg+agentName+agentId+agentUrl+agentImg, SECRET_KEY)));
			String resStr = new HttpClientService().postForm(URL, headers, param);
			System.out.println(resStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 法定代表人身份证简项认证
	public static void legalPersonAuth() {
		
		String appId = APP_ID;
		String serviceCode = "idb0033";
		String type = "ET_PE";
		String name = "杭州坤博岩土工程科技有限公司";
		String idCode = "913301066680067018";
		String orgCode = null;
		String legalName = "杨晓军";
		String legalId = "330721197105156916";
		String legalUrl = null;
		String legalImg = null;
		String agentName = null;
		String agentId = null;
		String agentUrl = null;
		String agentImg = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", appId);
		param.put("serviceCode", serviceCode);
		param.put("name", name);
		param.put("type", type);
		param.put("idCode", idCode);
		param.put("legalName", legalName);
		param.put("legalId", legalId);
		
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			headers.put("Content-Signature", "HMAC-SHA1 " + new Base64().encodeToString(HMACSHA1.getHmacSHA1(appId+serviceCode+type+name+
					idCode+orgCode+legalName+legalId+legalUrl+legalImg+agentName+agentId+agentUrl+agentImg, SECRET_KEY)));
			String resStr = new HttpClientService().postForm(URL, headers, param);
			System.out.println(resStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 代理人身份证简项认证
	public static void agentAuth() {
		
		String appId = APP_ID;
		String serviceCode = "idb0035";
		String type = "ET_PE";
		String name = "杭州坤博岩土工程科技有限公司";
		String idCode = "913301066680067018";
		String orgCode = null;
		String legalName = null;
		String legalId = null;
		String legalUrl = null;
		String legalImg = null;
		String agentName = "杨晓军";
		String agentId = "330721197105156916";
		String agentUrl = null;
		String agentImg = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appId", appId);
		param.put("serviceCode", serviceCode);
		param.put("name", name);
		param.put("type", type);
		param.put("idCode", idCode);
		param.put("agentName", agentName);
		param.put("agentId", agentId);
		
		Map<String, String> headers = new HashMap<String, String>();
		
		try {
			headers.put("Content-Signature", "HMAC-SHA1 " + new Base64().encodeToString(HMACSHA1.getHmacSHA1(appId+serviceCode+type+name+
					idCode+orgCode+legalName+legalId+legalUrl+legalImg+agentName+agentId+agentUrl+agentImg, SECRET_KEY)));
			String resStr = new HttpClientService().postForm(URL, headers, param);
			System.out.println(resStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
