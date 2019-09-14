package cn.edu.bupt.yaoxintong.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongAdmin;

public interface AdminRepository extends JpaRepository<YaoxintongAdmin, String>,PagingAndSortingRepository<YaoxintongAdmin, String>  {
	YaoxintongAdmin findByAdminname(String adminname);
}
