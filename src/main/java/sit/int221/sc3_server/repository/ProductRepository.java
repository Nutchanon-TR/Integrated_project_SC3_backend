package sit.int221.sc3_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sc3_server.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//    List<Product> findAllByOrderByCreatedOnDesc();
    //List<Product> findAllByBrand_Id(int id);
    boolean existsByBrand_Id(int brandId);
    int countByBrandId(int id);

}