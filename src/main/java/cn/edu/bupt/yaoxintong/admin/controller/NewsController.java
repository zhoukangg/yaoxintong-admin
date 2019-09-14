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

import cn.edu.bupt.yaoxintong.admin.pojo.News;
import cn.edu.bupt.yaoxintong.admin.repository.NewsRepository;
import cn.edu.bupt.yaoxintong.admin.util.Constant;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import cn.edu.bupt.yaoxintong.admin.util.StringUtil;

@RestController
@RequestMapping(value = "/news")
public class NewsController {

	@Autowired
	NewsRepository newsRepository;
	
	@RequestMapping(value = "/readAll", method = RequestMethod.POST)
	public ReturnModel readAll(HttpServletResponse response, HttpServletRequest request) {
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");

		ReturnModel result = new ReturnModel();
		result.setResult(true);
		result.setDatum(newsRepository.findAll());
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
		result.setDatum(newsRepository.findAll(pageable));
		return result;
		
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public ReturnModel read(String id,HttpServletResponse response, HttpServletRequest request) {	
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		ReturnModel result = new ReturnModel();
		try {
			if (!StringUtil.isEmpty(id)) {
				News news = newsRepository.findById(id);
				result.setResult(true);
				result.setDatum(news);
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
	public ReturnModel delete(String id,HttpServletResponse response, HttpServletRequest request) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		ReturnModel result = new ReturnModel();
		try {
			if (!StringUtil.isEmpty(id)) {
				News news = newsRepository.findById(id);
				if (news!=null) {
					newsRepository.delete(id);
					result.setReason("删除成功");
					result.setResult(true);
				} else {
					result.setReason("未查找到该新闻");
					result.setResult(false);
				}				
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
	public ReturnModel register(String content, String url, String img,
			 HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result = new ReturnModel();
		if(StringUtil.isEmpty(content) || StringUtil.isEmpty(url)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}

		// 设置新闻信息
		News news = new News();
		String id = UUID.randomUUID().toString();
		news.setId(id);
		news.setUrl(url);
		news.setImg(img);
		news.setContent(content);
		Date time=new Date();
		news.setTime(time);
		
		try {
			newsRepository.save(news);
			result.setDatum(news);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ReturnModel modify(  
			 String id,
			 String content, String url, String img,
			HttpServletResponse response, HttpServletRequest request ) {
		
		// 解决Ajax跨域请求问题
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		ReturnModel result = new ReturnModel();
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(content) || StringUtil.isEmpty(url)) {
			result.setResult(false);
			result.setReason(Constant.REASON_UNKNOW);
			return result;
		}
		
		News news = newsRepository.findById(id);
		// case not registered
		if (news == null) {
			result.setResult(false);
			result.setReason("新闻不存在");
			return result;
		}
		
		// 设置新闻信息
		news.setUrl(url);
		news.setImg(img);
		news.setContent(content);
		Date time=new Date();
		news.setTime(time);
		
		try {
			newsRepository.save(news);
			result.setDatum(news);
			result.setResult(true);
		} catch (Exception e) {
			result.setResult(false);
		}
		return result;
	}
}
