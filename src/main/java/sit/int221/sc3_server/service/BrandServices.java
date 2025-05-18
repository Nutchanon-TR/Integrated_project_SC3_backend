package sit.int221.sc3_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import sit.int221.sc3_server.DTO.CreateBrandDTO;
import sit.int221.sc3_server.DTO.UpdateBrandDTO;

import sit.int221.sc3_server.DTO.BrandDetailDTO;

import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.exception.DeleteFailedException;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;


@Service
public class BrandServices {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(int id) {
        return brandRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("No Brand found with id: " + id));
    }

    public BrandDetailDTO getBrandDetailById(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No Brand was found with id: " + id));

        BrandDetailDTO dto = modelMapper.map(brand, BrandDetailDTO.class);
        int count = productRepository.countByBrandId(brand.getId());
        dto.setNoOfSaleItems(count);

        return dto;
    }


    public Brand createBrand(CreateBrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        brand.setWebSiteUrl(dto.getWebsiteUrl());
        brand.setCountryOfOrigin(dto.getCountryOfOrigin());

        Boolean isActive = dto.getIsActive();
        brand.setIsActive(isActive != null ? isActive : true);

        Timestamp now = Timestamp.from(ZonedDateTime.now().toInstant());
        brand.setCreatedOn(now);
        brand.setUpdatedOn(now);

        return brandRepository.save(brand);
    }


//    public Brand updateBrand(int id, UpdateBrandDTO dto) {
//        Brand brand = getBrandById(id);
//        brand.setName(dto.getName());
//        brand.setWebSiteUrl(dto.getWebsiteUrl());
//        brand.setCountryOfOrigin(dto.getCountryOfOrigin());
//        brand.setIsActive(dto.getIsActive());
//        Timestamp now = Timestamp.from(ZonedDateTime.now().toInstant());
//        brand.setUpdatedOn(now);
//        return brandRepository.save(brand);
//    }

    public BrandDetailDTO updateBrand(int id, UpdateBrandDTO dtos) {

        if (dtos.getName() == null || dtos.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Brand name is required");
        }

        Brand brand = getBrandById(id);
        brand.setName(dtos.getName());
        brand.setWebSiteUrl(dtos.getWebsiteUrl());
        brand.setCountryOfOrigin(dtos.getCountryOfOrigin());

        Boolean isActive = dtos.getIsActive();
        brand.setIsActive(isActive != null ? isActive : true);

        Timestamp now = Timestamp.from(ZonedDateTime.now().toInstant());
        brand.setUpdatedOn(now);

        // save entity
        brand = brandRepository.save(brand);

        // map to DTO
        BrandDetailDTO dto = modelMapper.map(brand, BrandDetailDTO.class);
        int count = productRepository.countByBrandId(brand.getId());
        dto.setNoOfSaleItems(count);

        return dto;
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
