package sit.int221.sc3_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.sc3_server.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}