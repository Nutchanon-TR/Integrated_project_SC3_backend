package sit.int221.sc3_server.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sc3_server.DTO.BranByIdDTO;
import sit.int221.sc3_server.DTO.BrandDTO;
import sit.int221.sc3_server.DTO.CreateBrandDTO;
import sit.int221.sc3_server.DTO.UpdateBrandDTO;
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
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/brands")
    public ResponseEntity<List<BranByIdDTO>> getAllBrands() {
        List<Brand> brand = brandServices.getAllBrand();
        List<BranByIdDTO> brandDTOS =  listMapper.mapList(brand, BranByIdDTO.class,new ModelMapper());
        return ResponseEntity.ok(brandDTOS);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<BranByIdDTO> getBrandById(@PathVariable int id) {
        Brand brand = brandServices.getBrandById(id);
        BranByIdDTO brandDTO = modelMapper.map(brand, BranByIdDTO.class);
        return ResponseEntity.ok(brandDTO);
    }

    @PostMapping("/brands")
    public ResponseEntity<BranByIdDTO> createBrand(@RequestBody CreateBrandDTO createBrandDTO) {
        Brand brand = brandServices.createBrand(createBrandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(brand, BranByIdDTO.class));
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<BranByIdDTO> updateBrand(@PathVariable int id, @RequestBody UpdateBrandDTO updateBrandDTO) {
        Brand brand = brandServices.updateBrand(id, updateBrandDTO);
        return ResponseEntity.ok(modelMapper.map(brand, BranByIdDTO.class));
    }


}