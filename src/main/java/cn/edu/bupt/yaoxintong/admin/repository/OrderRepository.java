package cn.edu.bupt.yaoxintong.admin.repository;

import cn.edu.bupt.yaoxintong.admin.pojo.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "select u.username,o.* from user_order o left join yaoxintong_user u on o.user_id=u.id  /* #pageable# */",
            countQuery = "select count(id) from user_order", nativeQuery = true)
    Page<Order> getOrders(Pageable pageable);
}
