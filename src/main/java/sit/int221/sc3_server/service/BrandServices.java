package sit.int221.sc3_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.sc3_server.DTO.BrandDetailDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.exception.DeleteFailedException;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.util.List;

@Service
public class BrandServices {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No Brand found with id: " + id));
    }

    public void deleteBrand(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Brand not found"));
        boolean hasProduct = productRepository.existsByBrand_Id(brand.getId());
        if (hasProduct) {
            throw new DeleteFailedException("Cannot delete brand because it has products");
        }
        try {
            brandRepository.deleteById(brand.getId());
        } catch (Exception e) {
            throw new RuntimeException("Brand " + id + " cant be deleted due to " + e.getMessage());
        }
    }



}
