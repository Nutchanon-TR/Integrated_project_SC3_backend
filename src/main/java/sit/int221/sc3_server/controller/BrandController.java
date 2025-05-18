package sit.int221.sc3_server.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sit.int221.sc3_server.DTO.BrandDetailDTO;
import sit.int221.sc3_server.DTO.CreateBrandDTO;
import sit.int221.sc3_server.DTO.UpdateBrandDTO;

import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.service.BrandServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v1")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class BrandController {
    @Autowired
    private BrandServices brandServices;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDetailDTO>> getAllBrands() {
        List<Brand> brand = brandServices.getAllBrand();
        List<BrandDetailDTO> brandDTOS = listMapper.mapList(brand, BrandDetailDTO.class, modelMapper);
        return ResponseEntity.ok(brandDTOS);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandDetailDTO> getBrandById(@PathVariable int id) {
        BrandDetailDTO brandDTO = brandServices.getBrandDetailById(id);
        return ResponseEntity.ok(brandDTO);
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandDetailDTO> createBrand(@RequestBody CreateBrandDTO createBrandDTO) {
        Brand brand = brandServices.createBrand(createBrandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(brand, BrandDetailDTO.class));
    }


    @PutMapping("/brands/{id}")
    public ResponseEntity<BrandDetailDTO> updateBrand(@PathVariable int id, @RequestBody UpdateBrandDTO updateBrandDTO) {
        BrandDetailDTO brand = brandServices.updateBrand(id, updateBrandDTO);
        return ResponseEntity.ok(modelMapper.map(brand, BrandDetailDTO.class));
    }


    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Object> deleteBrand(@PathVariable int id) {
        brandServices.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

}