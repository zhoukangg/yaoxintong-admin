package cn.edu.bupt.yaoxintong.admin.repository;

import cn.edu.bupt.yaoxintong.admin.pojo.AuthenticationDianshang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthenticationDianshangRepository extends JpaRepository<AuthenticationDianshang, String>,PagingAndSortingRepository<AuthenticationDianshang, String> {

    List<AuthenticationDianshang> findAuthenticationDianshangByBusinessId(String businessId);
}
