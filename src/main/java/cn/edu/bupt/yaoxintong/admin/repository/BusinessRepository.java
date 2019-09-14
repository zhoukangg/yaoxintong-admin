package cn.edu.bupt.yaoxintong.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.edu.bupt.yaoxintong.admin.pojo.YaoxintongBusiness;

public interface BusinessRepository extends JpaRepository<YaoxintongBusiness, String>,PagingAndSortingRepository<YaoxintongBusiness, String>  {

	YaoxintongBusiness findById(String id);
	YaoxintongBusiness findByUsername(String username);
	YaoxintongBusiness findByBusinessLicense(String businessLicense);
}
