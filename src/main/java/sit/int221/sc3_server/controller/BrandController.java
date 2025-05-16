package sit.int221.sc3_server.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.sc3_server.DTO.BrandDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.service.BrandServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v1")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class BrandController {
    @Autowired
    private BrandServices brandServices;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<Brand> brand = brandServices.getAllBrand();
        List<BrandDTO> brandDTOS =  listMapper.mapList(brand, BrandDTO.class,new ModelMapper());
        return ResponseEntity.ok(brandDTOS);
    }
}