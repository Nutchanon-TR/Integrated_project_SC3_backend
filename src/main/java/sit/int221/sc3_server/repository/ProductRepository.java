package sit.int221.sc3_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    List<Product> findAllByOrderByCreatedOnDesc();
}