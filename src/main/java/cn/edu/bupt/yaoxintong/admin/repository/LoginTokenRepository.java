package cn.edu.bupt.yaoxintong.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.edu.bupt.yaoxintong.admin.pojo.LoginToken;

public interface LoginTokenRepository extends JpaRepository<LoginToken, String> {

}
