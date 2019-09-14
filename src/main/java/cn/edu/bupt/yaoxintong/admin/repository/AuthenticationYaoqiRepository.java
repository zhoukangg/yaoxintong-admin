package cn.edu.bupt.yaoxintong.admin.repository;

import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationDianshang;
import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationYaoqi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthenticationYaoqiRepository extends JpaRepository<AuthenticationYaoqi, String>,PagingAndSortingRepository<AuthenticationYaoqi, String> {
    List<AuthenticationYaoqi> findAuthenticationYaoqiByBusinessId(String businessId);
    AuthenticationYaoqi findById(String id);
}
