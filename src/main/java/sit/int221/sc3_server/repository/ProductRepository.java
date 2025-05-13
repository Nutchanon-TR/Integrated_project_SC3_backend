package sit.int221.sc3_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sc3_server.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    List<Product> findAllByOrderByCreatedOnDesc();
}