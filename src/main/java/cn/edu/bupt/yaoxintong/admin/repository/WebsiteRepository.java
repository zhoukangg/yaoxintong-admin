package cn.edu.bupt.yaoxintong.admin.repository;

import cn.edu.bupt.yaoxintong.admin.pojo.Website;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface WebsiteRepository extends JpaRepository<Website, String> {
    @Query(value = "select id,corporate_name,website,website_status from authentication_dianshang  /* #pageable# */",
            countQuery = "select count(id) from authentication_dianshang", nativeQuery = true)
    Page<Website> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update Website dianshang set dianshang.website_status=?2 where dianshang.id=?1")
    int updateWebsiteStatus(String id, Integer status);

    Website findById(String id);
}
