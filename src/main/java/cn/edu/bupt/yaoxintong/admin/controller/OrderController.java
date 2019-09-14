package cn.edu.bupt.yaoxintong.admin.controller;

import cn.edu.bupt.yaoxintong.admin.repository.OrderRepository;
import cn.edu.bupt.yaoxintong.admin.util.ReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReturnModel getOrders(int page, int size, HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Pageable pageable = new PageRequest(page, size);

        ReturnModel returnModel = new ReturnModel();
        try {
            returnModel.setDatum(orderRepository.getOrders(pageable));
            returnModel.setResult(true);
        } catch (Exception e) {
            returnModel.setResult(false);
            returnModel.setReason("未知错误");
            System.out.println(e.getMessage());
        }
        return returnModel;
    }
}
