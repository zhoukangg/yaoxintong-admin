package cn.edu.bupt.yaoxintong.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.edu.bupt.yaoxintong.admin.pojo.News;

public interface NewsRepository extends JpaRepository<News,String>{
	News findById(String id);
}
