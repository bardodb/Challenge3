package br.com.compassuol.sp.challenge.msorders.repository;

import br.com.compassuol.sp.challenge.msorders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
