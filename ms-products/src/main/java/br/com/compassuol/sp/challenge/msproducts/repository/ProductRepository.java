package br.com.compassuol.sp.challenge.msproducts.repository;

import br.com.compassuol.sp.challenge.msproducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
