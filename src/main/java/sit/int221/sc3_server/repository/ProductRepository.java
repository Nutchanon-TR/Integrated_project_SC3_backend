package sit.int221.sc3_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sc3_server.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByBrand_Id(int brandId);

    int countByBrand_Id(int id);

    Page<Product> findByBrand_NameIn(List<String> brandNames, Pageable pageable);
    boolean existsByModelIgnoreCase(String model);
}