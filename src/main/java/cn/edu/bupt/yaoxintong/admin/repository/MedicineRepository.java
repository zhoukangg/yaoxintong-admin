package cn.edu.bupt.yaoxintong.admin.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.edu.bupt.yaoxintong.admin.pojo.Medicine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MedicineRepository extends JpaRepository<Medicine, String> {

    Medicine findById(String id);

    @Modifying
    @Transactional
    @Query("update Medicine set authentication=1 where id= ?1")
    int authentication(String id);
}
