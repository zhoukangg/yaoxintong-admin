package cn.edu.bupt.yaoxintong.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongUser;
 
public interface UserRepository extends JpaRepository<YaoxintongUser, String>,PagingAndSortingRepository<YaoxintongUser, String>  {
	YaoxintongUser findById(String id);
	YaoxintongUser findByUsername(String username);

}
