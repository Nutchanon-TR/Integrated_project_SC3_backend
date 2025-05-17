package sit.int221.sc3_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.sc3_server.DTO.CreateBrandDTO;
import sit.int221.sc3_server.DTO.UpdateBrandDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.repository.BrandRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class BrandServices {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Brand was found with id: " + id));
    }

    public Brand createBrand(CreateBrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        brand.setWebsiteUrl(dto.getWebsiteUrl());
        brand.setCountryOfOrigin(dto.getCountryOfOrigin());
        brand.setIsActive(true);
        brand.setCreatedOn(ZonedDateTime.now().toInstant());
        brand.setUpdatedOn(ZonedDateTime.now().toInstant());
        return brandRepository.save(brand);
    }

    public Brand updateBrand(int id, UpdateBrandDTO dto) {
        Brand brand = getBrandById(id);
        brand.setName(dto.getName());
        brand.setWebsiteUrl(dto.getWebsiteUrl());
        brand.setCountryOfOrigin(dto.getCountryOfOrigin());
        brand.setIsActive(dto.getIsActive());
        brand.setUpdatedOn(ZonedDateTime.now().toInstant());
        return brandRepository.save(brand);
    }

}
