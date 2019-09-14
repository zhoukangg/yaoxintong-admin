package cn.edu.bupt.yaoxintong.admin.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper;
import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper.PasswordAndSalt;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongAdmin;
import cn.edu.bupt.yaoxintong.admin.repository.AdminRepository;
import cn.edu.bupt.yaoxintong.admin.repository.LoginTokenRepository;
import cn.edu.bupt.yaoxintong.admin.util.Constant;
import cn.edu.bupt.yaoxintong.admin.util.Logger;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import cn.edu.bupt.yaoxintong.admin.util.StringUtil;

@RestController
@RequestMapping(value = "/admin")
public class  AdminController {
	
	private static final Logger logger = Logger.getInstance();
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	PasswordHelper passwordHelper;
	
	@Autowired
	LoginTokenRepository loginTokenRepository;
	/**
	 * @author 作者wydewy 接口名称：用户注册
	 * @param
	 * @param
	 * @param
	 * @param password
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ReturnModel register(String operatorName, String adminname,String password,String type,
			HttpServletResponse response, HttpServletRequest request) {

		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		logger.info("======进入了AdminController的/register方法，参数：username = " + adminname + " password = " + password 
				+ "type = " + type );

		ReturnModel result = new ReturnModel();

		if(StringUtil.isEmpty(operatorName)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		YaoxintongAdmin i = adminRepository.findByAdminname(operatorName);
		if (i == null) {
			result.setResult(false);
			result.setReason("未查找到操作人员的注册信息");
			return result;
		}
		if (StringUtil.isEmpty(type)){
			result.setResult(false);
			return result;
		}
		if (i.getType().equals("0") && type.equals("1")) {
			result.setResult(false);
			result.setReason("操作超出权限");
			return result;
		}
		
		if (StringUtil.isEmpty(adminname)) {
			result.setResult(false);
			if (Constant.DEBUG) {
				result.setReason(Constant.REASON_USERNAME_IS_NULL);
			} else {
				result.setReason(Constant.REASON_UNKNOW);
			}

			return result;
		}
		if (StringUtil.isEmpty(password)) {
			result.setResult(false);
			if (Constant.DEBUG) {
				result.setReason(Constant.REASON_PASSWORD_IS_NULL);
			} else {
				result.setReason(Constant.REASON_UNKNOW);
			}

			return result;
		}

		// 验证是否该用户已经注册
		YaoxintongAdmin admin = adminRepository.findByAdminname(adminname);
		// case already registered
		if (admin != null) {
			result.setResult(false);
			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
			return result;
		}

		PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);

		// 设置用户信息
		admin = new YaoxintongAdmin();
		String userId = UUID.randomUUID().toString();
		admin.setId(userId);
		admin.setAdminname(adminname);
		admin.setPassword(encryptPassword.getPassword());// 密码加密
		admin.setSalt(encryptPassword.getSalt());
		admin.setRegistertime(new Date());
		admin.setType(type);
		YaoxintongAdmin j=null;
		try {
			j = adminRepository.save(admin);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.toString());
		}
		
		if (j != null) {
			result.setResult(true);
		} else {
			result.setResult(false);
		}
		result.setDatum(j);
		return result;
	}


	/**
	 * 用户登录
	 * 
	 * @param
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ReturnModel login(String adminname, String password, HttpServletResponse response,
			HttpServletRequest request) {
		ReturnModel result = new ReturnModel();

		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		YaoxintongAdmin admin = adminRepository.findByAdminname(adminname);
		logger.info("adminname="+adminname +" password="+password);
		if (admin == null) {
			result.setResult(false);
			result.setReason(Constant.REASON_USER_NOT_EXIST);
			return result;
		}
		if (StringUtil.isEmpty(password)) {
			result.setResult(false);
			result.setReason(Constant.REASON_PASSWORD_IS_NULL);
			return result;
		}

		String decryptPassword = passwordHelper.encryptPassword(password, admin.getSalt());

		String originPassword = admin.getPassword();
		if (StringUtil.isEmpty(originPassword)) {
			result.setResult(false);
			result.setReason(Constant.REASON_PASSWORD_NOT_EXIST);
			return result;
		} else {
			logger.info("originPassword="+originPassword +" decryptPassword="+decryptPassword);
			if (originPassword.equals(decryptPassword)) {
				
				result.setResult(true);
				result.setDatum(admin);

			} else {
				result.setResult(false);
				result.setReason(Constant.LOGIN_FAILED_PASSWORD_ERROR);
			}
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
		result.setDatum(adminRepository.findAll());
		return result;
	}
	
	@RequestMapping(value = "/pagingAndSorting", method = RequestMethod.POST)
	public ReturnModel pagingAndSorting(int page,int size,HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
        Pageable pageable =new PageRequest(page,size);
		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(adminRepository.findAll(pageable));
		logger.info("result="+result );
		return result;
	}


	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ReturnModel read(String adminname,HttpServletResponse response, HttpServletRequest request) {	
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		ReturnModel result = new ReturnModel();
		try {
			YaoxintongAdmin yaoxintongAdmin = adminRepository.findByAdminname(adminname);
			result.setResult(true);
			result.setDatum(yaoxintongAdmin);
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
		}
		
		return result;
	}

	@RequestMapping(value = "/getLoginUser", method = RequestMethod.POST)
	public ReturnModel getLoginUser(HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		String adminname = "";
		Cookie[] cookies =  request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")){
					adminname = cookie.getValue();
				}
				System.out.println(cookie.getName());
			}
		}
		ReturnModel result = new ReturnModel();
		try {
			YaoxintongAdmin yaoxintongAdmin = adminRepository.findByAdminname(adminname);
			result.setResult(true);
			result.setDatum(yaoxintongAdmin);
		} catch (Exception e) {
			result.setResult(false);
		}
		System.out.println("----------------");
		System.out.println(result.getDatum().toString());

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ReturnModel delete( String operatorName, String name,
			HttpServletResponse response, HttpServletRequest request) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		ReturnModel result = new ReturnModel();
		if (StringUtil.isEmpty(operatorName) ||StringUtil.isEmpty(name)) {
			result.setResult(false);
			logger.info("operatorName="+operatorName +" name="+name);
			return result;
		}
		YaoxintongAdmin i = adminRepository.findByAdminname(operatorName);
		if (i == null) {
			result.setResult(false);
			result.setReason("未查找到操作人员的注册信息");
			return result;
		}
		String str=i.getType();
		if(!str.equals("1")) {
			result.setResult(false);
			result.setReason("操作超出权限");
			logger.info("i.getType()="+i.getType());
			return result;
		}
		YaoxintongAdmin j = adminRepository.findByAdminname(name);
		if (j == null) {
			result.setResult(false);
			result.setReason("未查找到该管理员");
			return result;
		}

		try {
			adminRepository.delete(j.getId());
			result.setResult(true);	
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
			logger.info(e.toString());
		}
		return result;
		
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ReturnModel modify( String operatorName, String name,  String password ,
			@RequestParam(value = "avatar", required = false) String avatar ,  String type, HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result = new ReturnModel();
		logger.info("operatorName="+operatorName+" name="+name+" avatar="+avatar+" type="+type);
		YaoxintongAdmin i,j=null;
		try {
			if (StringUtil.isEmpty(operatorName) || StringUtil.isEmpty(name) 
					|| StringUtil.isEmpty(password ) || StringUtil.isEmpty(password)
					|| StringUtil.isEmpty(type) ) {
				result.setResult(false);
				result.setDatum("必传值为空");
				return result;
			}
			result.setDatum("222222");
			i = adminRepository.findByAdminname(operatorName);
			result.setDatum("333");
			j = adminRepository.findByAdminname(name);
			result.setDatum("444");
			if (i==null || j==null) {
				result.setResult(false);
				result.setDatum("operatorName || name =null");
				return result;
			}
			result.setDatum("555");
			
			if (operatorName.equals(name)) {
//				String decryptPassword = passwordHelper.encryptPassword(password, j.getSalt());
//				if (password.equals("******") || j.getPassword().equals(decryptPassword)){
//
//				}else {
//					PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
//					j.setPassword(encryptPassword.getPassword());// 密码加密
//					j.setSalt(encryptPassword.getSalt());
//				}
				if (password.equals("******") || j.getPassword().equals(password)){

				}else {
					PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
					j.setPassword(encryptPassword.getPassword());// 密码加密
					j.setSalt(encryptPassword.getSalt());
				}

				if (avatar!=null) {
					j.setAvatar(avatar);
				} 
				adminRepository.save(j);
				result.setResult(true);
				result.setDatum(j);
				return result;
			}
			result.setDatum("666");
			if (i.getType().equals("1")) {
				
				if(j.getPassword() != password) {
					PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
					j.setPassword(encryptPassword.getPassword());// 密码加密
					j.setSalt(encryptPassword.getSalt());
				}
				j.setType(type);
				if (avatar!=null) {
					j.setAvatar(avatar);
				} 
				adminRepository.save(j);
				result.setResult(true);
				result.setDatum(j);
				return result;
			}
			result.setResult(false);
			result.setDatum("111111");
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult(false);
			
		}
		return result;
		
	}

}
