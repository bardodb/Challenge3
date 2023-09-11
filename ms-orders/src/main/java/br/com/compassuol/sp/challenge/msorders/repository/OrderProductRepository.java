package br.com.compassuol.sp.challenge.msorders.repository;

import br.com.compassuol.sp.challenge.msorders.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query("SELECT p FROM OrderProduct p WHERE p.orderId = :orderId")
    List<OrderProduct> findProductIdByOrderId(@Param("orderId") Long orderId);
}
