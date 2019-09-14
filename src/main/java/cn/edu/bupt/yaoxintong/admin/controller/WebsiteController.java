package cn.edu.bupt.yaoxintong.admin.controller;

import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationYaoqi;
import cn.edu.bupt.yaoxintong.admin.pojo.Website;
import cn.edu.bupt.yaoxintong.admin.repository.AuthenticationYaoqiRepository;
import cn.edu.bupt.yaoxintong.admin.repository.WebsiteRepository;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/website")
//@CrossOrigin(origins = "*")
public class WebsiteController {
    @Autowired
    WebsiteRepository websiteRepository;
    @Autowired
    AuthenticationYaoqiRepository authenticationYaoqiRepository;

    //获取网站认证列表
    @RequestMapping(value = "/getwebsiteau", method = RequestMethod.POST)
    public ReturnModel getWebsiteAuthentications(int page, int size, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        ReturnModel returnModel = new ReturnModel();
        Pageable pageable = new PageRequest(page, size);
        try {
            returnModel.setDatum(websiteRepository.findAll(pageable));
        } catch (Exception e) {
            returnModel.setResult(false);
            returnModel.setReason("未知错误");
        }
        returnModel.setResult(true);
        return returnModel;
    }

    //更新网站认证状态
    @RequestMapping(value = "/changestatus", method = RequestMethod.POST)
//    @CrossOrigin
    public ReturnModel changeWebStatus(String id, Integer status, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        ReturnModel returnModel = new ReturnModel();
        try {
            if (websiteRepository.updateWebsiteStatus(id, status) > 0) {
                returnModel.setResult(true);
            } else {
                returnModel.setResult(false);
                System.out.println("更改失败");
            }
        } catch (Exception e) {
            returnModel.setResult(false);
            System.out.println(e.getMessage());
        }
        return returnModel;
    }

    //网站认证
    @RequestMapping(value = "/certify", method = RequestMethod.POST)
//    @CrossOrigin
    public ReturnModel certify(String id,String bussinessName, String website, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("id="+id+";bussinessName="+bussinessName+";website="+website);
        ReturnModel returnModel = new ReturnModel();
        try {
            if(true){
                if (websiteRepository.updateWebsiteStatus(id, 1) > 0) {
                    returnModel.setResult(true);
                    returnModel.setReason("网站认证成功，数据库已更新");
                } else {
                    returnModel.setResult(false);
                    returnModel.setReason("网站认证成功，数据库更新失败");
                }
            }else {
                if (websiteRepository.updateWebsiteStatus(id, 2) > 0) {
                    returnModel.setReason("网站认证失败，数据库已更新");
                    returnModel.setResult(false);
                } else {
                    returnModel.setResult(false);
                    returnModel.setReason("网站认证失败，数据库更新失败");
                }
            }


        } catch (Exception e) {
            returnModel.setResult(false);
            System.out.println(e.getMessage());
        }
        return returnModel;
    }
}
