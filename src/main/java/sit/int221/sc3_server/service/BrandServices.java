package sit.int221.sc3_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.repository.BrandRepository;

import java.util.List;

@Service
public class BrandServices {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrand() {

        return brandRepository.findAll();
    }
}
