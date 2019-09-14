package cn.edu.bupt.yaoxintong.admin.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper;
import cn.edu.bupt.yaoxintong.admin.componet.PasswordHelper.PasswordAndSalt;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongUser;
import cn.edu.bupt.yaoxintong.admin.repository.UserRepository;
import cn.edu.bupt.yaoxintong.admin.util.Constant;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import cn.edu.bupt.yaoxintong.admin.util.StringUtil;
import cn.edu.bupt.yaoxintong.admin.util.Logger;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordHelper passwordHelper;
	private static final Logger logger = Logger.getInstance();
	
	@RequestMapping(value = "/pagingAndSorting", method = RequestMethod.POST)
	public ReturnModel pagingAndSorting(int page, int size, HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		Pageable pageable=new PageRequest(page, size);
		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(userRepository.findAll(pageable));
		return result;
		
	}
	
	@RequestMapping(value = "/readAll", method = RequestMethod.POST)
	public ReturnModel readAll(HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(userRepository.findAll());
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
				YaoxintongUser yaoxintongUser = userRepository.findByUsername(username);
				result.setResult(true);
				result.setDatum(yaoxintongUser);
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
		
		ReturnModel result = new ReturnModel();
		try {
			if (!StringUtil.isEmpty(username)) {
				YaoxintongUser yaoxintongUser = userRepository.findByUsername(username);
				userRepository.delete(yaoxintongUser.getId());
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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ReturnModel register(
			 String username,

		     String password,

		     String sex,

		     String status,
 
		     String details,
			HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		System.out.println("进入/register ： " + sex);
		
		ReturnModel result = new ReturnModel();
		if(
				StringUtil.isEmpty(username) || StringUtil.isEmpty(password)
				|| StringUtil.isEmpty(sex) || StringUtil.isEmpty(status)
				|| StringUtil.isEmpty(details) ) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		YaoxintongUser user = userRepository.findByUsername(username);
		// case already registered
		if (user != null) {
			result.setResult(false);
			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
			return result;
		}

		PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
		// 设置用户信息
		user=new YaoxintongUser();
		String id = UUID.randomUUID().toString();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(encryptPassword.getPassword());// 密码加密
		user.setSalt(encryptPassword.getSalt());
		user.setRegisterTime(new Date());
		user.setSex(sex);
		user.setStatus(status);
		user.setDetails(details);
		
		try {
			userRepository.save(user);
			result.setDatum(user);
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

		     String password,

		     String sex,

		     String status,
 
			HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result = new ReturnModel();
		logger.info("======进入了UserController的/modify方法，参数：originalusername = " + originalusername + " username = " + username 
				+ " password = " + password  + " sex = " + sex + " status = " + status);
		if(StringUtil.isEmpty(originalusername) ||StringUtil.isEmpty(username) || StringUtil.isEmpty(password)
			|| StringUtil.isEmpty(sex) || StringUtil.isEmpty(status)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		YaoxintongUser user = userRepository.findByUsername(username);
		// case already registered
		if (user != null && (!username.equals(originalusername))) {
			result.setResult(false);
			result.setReason(Constant.REASON_USERNAME_IS_EXIST);
			return result;
		}
		user = userRepository.findByUsername(originalusername);
		// case  no registered
		if (user == null) {
			result.setResult(false);
			result.setReason("不存在");
			return result;
		}

		// 设置用户信息
		user.setUsername(username);

		if (password.equals("******") || user.getPassword().equals(password)){

		}else {
			PasswordAndSalt encryptPassword = passwordHelper.encryptPassword(password);
			user.setPassword(encryptPassword.getPassword());// 密码加密
			user.setSalt(encryptPassword.getSalt());
		}
		user.setStatus(status);
		user.setSex(sex);
		logger.info("======进入了UserController的/modify方法，返回值sex = " + sex);
		try {
			userRepository.save(user);
			result.setDatum(user);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
		}
		logger.info("======进入了UserController的/modify方法，返回值result = " + user.getStatus());
		return result;
		
	}
}
