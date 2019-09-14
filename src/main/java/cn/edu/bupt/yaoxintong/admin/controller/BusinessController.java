package cn.edu.bupt.yaoxintong.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationDianshang;
import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationYaoqi;
import cn.edu.bupt.yaoxintong.admin.repository.AuthenticationDianshangRepository;
import cn.edu.bupt.yaoxintong.admin.repository.AuthenticationYaoqiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bupt.yaoxintong.admin.componet.EnterpriseServiceTest;
import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper;
import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper.PasswordAndSalt;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongBusiness;
import cn.edu.bupt.yaoxintong.admin.repository.BusinessRepository;
import cn.edu.bupt.yaoxintong.admin.util.Constant;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import cn.edu.bupt.yaoxintong.admin.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.bupt.yaoxintong.admin.util.Logger;


@RestController
@RequestMapping(value = "/business")
public class BusinessController  {

	private static final Logger logger = Logger.getInstance();
	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	PasswordHelper passwordHelper;
	
	@Autowired
	EnterpriseServiceTest enterpriseServiceTest;

	@Autowired
	AuthenticationDianshangRepository authenticationDianshangRepository;

	@Autowired
	AuthenticationYaoqiRepository authenticationYaoqiRepository;
	
	
	@RequestMapping(value = "/certify", method = RequestMethod.POST)
	public ReturnModel certify(String id,String businessName,String businessLicense,HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		logger.info("======进入了BusinessController的/certify方法，参数：businessName=" + businessName + " businessLicense=" + businessLicense);
		ReturnModel result = new ReturnModel();
		if(businessName.isEmpty() || businessLicense.isEmpty()){
			result.setDatum("参数为空！");
			result.setResult(false);
		}
//		licenseAuth();
//		organizationAuth();
//		legalPersonAuth();
//		agentAuth();
		//   天威企业认证
		String ss=enterpriseServiceTest.licenseAuth(businessName,businessLicense);
		//JSONObject jsonObj =JSONObject.fromObject(ss);
		//JSONArray jsonArray = JSONArray.fromObject(jsonObj);
		System.out.println(ss);
		int s = ss.indexOf("\"status\":\"1\",\"orderNumber\"");
//		if(s == -1) {
		if(false){
			result.setDatum("企业基本信息比对失败");
			result.setResult(false);
		}	
		else {
			try {
				YaoxintongBusiness yaoxintongBusiness = businessRepository.findById(id);
				logger.info("======1");
				yaoxintongBusiness.setStatus("3");
				logger.info("======2");
				businessRepository.save(yaoxintongBusiness);
				logger.info("======3");
				result.setDatum("企业基本信息比对成功");
				result.setResult(true);

				List<AuthenticationDianshang> authenticationDianshangs = authenticationDianshangRepository.findAuthenticationDianshangByBusinessId(id);
				if(authenticationDianshangs.size()>0){
					for (AuthenticationDianshang authenticationDianshang:authenticationDianshangs){
						System.out.println(authenticationDianshang.toString());
						authenticationDianshang.setStatus("2");
						authenticationDianshangRepository.save(authenticationDianshang);
					}
				}

				List<AuthenticationYaoqi> authenticationYaoqis = authenticationYaoqiRepository.findAuthenticationYaoqiByBusinessId(id);
				if(authenticationYaoqis.size()>0){
					for (AuthenticationYaoqi authenticationYaoqi:authenticationYaoqis){
						System.out.println(authenticationYaoqi.toString());
						authenticationYaoqi.setStatus("2");
						authenticationYaoqiRepository.save(authenticationYaoqi);
					}
				}


			}catch (Exception e) {
				// TODO: handle exception
				result.setDatum("企业基本信息比对成功但数据库更新失败");
				result.setResult(false);
			}
		}
		return result;
		
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ReturnModel test(String businessName,String businessLicense,HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		logger.info("======进入了BusinessController的/test方法，参数：businessName=" + businessName + " businessLicense=" + businessLicense);
		ReturnModel result = new ReturnModel();
		if(businessName.isEmpty() || businessLicense.isEmpty()){
			result.setDatum("参数为空！");
			result.setResult(false);
		}
		String ss=enterpriseServiceTest.licenseAuth(businessName,businessLicense);
//		System.out.println(ss);
//		YaoxintongBusiness yaoxintongBusiness = businessRepository.findByBusinessLicense(businessLicense);
//		logger.info("======1");
//		yaoxintongBusiness.setStatus("2");
//		logger.info("======2");
//		businessRepository.save(yaoxintongBusiness);
//		logger.info("======3");
		result.setDatum(ss);
		result.setResult(true);;
	
		return result;
		
	}
	
	
	
	@RequestMapping(value = "/disabled", method = RequestMethod.POST)
	public ReturnModel disable(String username,String status,HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		logger.info("======进入了BusinessController的/disabled方法，参数：username=" + username + " status=" + status);
		ReturnModel result = new ReturnModel();
		try {
			if (StringUtil.isEmpty(username) || StringUtil.isEmpty(status)) {
				result.setResult(false);
			} else {
				YaoxintongBusiness yaoxintongBusiness = businessRepository.findByUsername(username);
				yaoxintongBusiness.setStatus(status);
				businessRepository.save(yaoxintongBusiness);
				result.setDatum(yaoxintongBusiness);
				result.setResult(true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
		}
		return result;
		
	}
	@RequestMapping(value = "/readAll", method = RequestMethod.POST)
	public ReturnModel readAll(HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(businessRepository.findAll());
		return result;
		
	}

	@RequestMapping(value = "/pagingAndSorting", method = RequestMethod.POST)
	public ReturnModel pagingAndSorting(int page,int size,HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		Pageable pageable = new PageRequest(page, size);
		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(businessRepository.findAll(pageable));
		return result;
		
	}

	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ReturnModel read(String username,HttpServletResponse response, HttpServletRequest request) {	
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		ReturnModel result = new ReturnModel();
		try {
			if (!StringUtil.isEmpty(username)) {
				YaoxintongBusiness yaoxintongBusiness = businessRepository.findByUsername(username);
				result.setResult(true);
				result.setDatum(yaoxintongBusiness);
			} else {
				result.setResult(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
		}
		
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ReturnModel delete(String username,HttpServletResponse response, HttpServletRequest request) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		logger.info("======进入了BusinessController的/delete方法，参数：username=" + username);
		ReturnModel result = new ReturnModel();
		try {
			if (!StringUtil.isEmpty(username)) {
				YaoxintongBusiness yaoxintongBusiness = businessRepository.findByUsername(username);
				businessRepository.delete(yaoxintongBusiness.getId());
				result.setResult(true);
			} else {
				result.setResult(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
		}
		return result;
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ReturnModel add(HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("*"));
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result=new ReturnModel();
		logger.info("Origin=" + request.getHeader("Origin"));
		logger.info("request=" + request);
		logger.info("response=" + response);
		return result;
		}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ReturnModel register(
			 String username,
			
			 String businessName,

			 String businessScope,

			 String businessType,

			 String contact,

			 String email,

			 String password,

			 String status,

//			 String sign,

//			 String businessLicense,

			 String address,

			 String details,
			HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result = new ReturnModel();
		if(
				StringUtil.isEmpty(username) || StringUtil.isEmpty(password)
				|| StringUtil.isEmpty(businessName) || StringUtil.isEmpty(businessScope)
				|| StringUtil.isEmpty(businessType) || StringUtil.isEmpty(email)
				|| StringUtil.isEmpty(contact) || StringUtil.isEmpty(address)
				|| StringUtil.isEmpty(status) || StringUtil.isEmpty(details)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		YaoxintongBusiness business = businessRepository.findByUsername(username);
		// case already registered
		if (business != null) {
			result.setResult(false);
			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
			return result;
		}

		PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
		// 设置用户信息
		business=new YaoxintongBusiness();
		String id = UUID.randomUUID().toString();
		business.setId(id);
		business.setUsername(username);
		business.setBusinessName(businessName);
		business.setBusinessScope(businessScope);
		business.setPassword(encryptPassword.getPassword());// 密码加密
		business.setSalt(encryptPassword.getSalt());
		business.setRegisterTime(new Date());
		business.setBusinessType(businessType);
		business.setEmail(email);
		business.setContact(contact);
		business.setAddress(address);
		business.setStatus(status);
		business.setDetails(details);
		business.setBusinessLicense("");
		
		try {
			businessRepository.save(business);
			result.setDatum(business);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ReturnModel modify(  
			 String originalusername,
			 
			 String username,
			
			 String businessName,

			 String businessScope,

			 String businessType,

			 String contact,

			 String email,

			 String password,

			// String sex,

			// String status,

			// String openid,

			// String sign,

			 String businessLicense,

			 String address,

			 String details,
			HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		logger.info("======进入了BusinessController的/delete方法，参数：originalusername=" + originalusername + " username=" + username+ " username=" + username
				+ " businessName=" + businessName+ " businessScope=" + businessScope+ " businessType=" + businessType+ " contact=" + contact+ " email=" + email
				+ " password=" + password+ " businessLicense=" + businessLicense+ " address=" + address+ " details=" + details);
		ReturnModel result = new ReturnModel();
		if(StringUtil.isEmpty(username)
				|| StringUtil.isEmpty(businessName) || StringUtil.isEmpty(businessScope)
				|| StringUtil.isEmpty(businessType) || StringUtil.isEmpty(email) || StringUtil.isEmpty(password)
				|| StringUtil.isEmpty(contact) || StringUtil.isEmpty(address)
				|| StringUtil.isEmpty(originalusername) || StringUtil.isEmpty(businessLicense)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		
		YaoxintongBusiness business = businessRepository.findByUsername(username);
		// case already registered
		if (business != null && (!username.equals(originalusername))) {
			result.setResult(false);
			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
			return result;
		}
		business = businessRepository.findByUsername(originalusername);
		// case  no registered
		if (business == null) {
			result.setResult(false);
			result.setReason("不存在");
			return result;
		}
		if(business.getPassword().equals(password) || password.equals("******")) {
			
		}else {
			PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
			business.setPassword(encryptPassword.getPassword());// 密码加密
			business.setSalt(encryptPassword.getSalt());
		}
		// 设置用户信息
		business.setUsername(username);
		business.setBusinessName(businessName);
		business.setBusinessScope(businessScope);
		business.setBusinessType(businessType);
		business.setEmail(email);
		business.setContact(contact);
		business.setAddress(address);
		//business.setStatus(status);
		business.setDetails(details);
		business.setBusinessLicense(businessLicense);
		try {
			businessRepository.save(business);
			result.setDatum(business);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
		}
		
		return result;
		
	}
}
