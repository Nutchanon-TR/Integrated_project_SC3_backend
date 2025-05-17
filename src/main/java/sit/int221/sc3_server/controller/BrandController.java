package sit.int221.sc3_server.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sc3_server.DTO.BrandDTO;
import sit.int221.sc3_server.DTO.BrandDetailDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.service.BrandServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v1")
//@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class BrandController {
    @Autowired
    private BrandServices brandServices;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<Brand> brand = brandServices.getAllBrand();
        List<BrandDTO> brandDTOS = listMapper.mapList(brand, BrandDTO.class, modelMapper);
        return ResponseEntity.ok(brandDTOS);
    }



//    @GetMapping("/brands/{id}")
//    public ResponseEntity<BrandDetailDTO> getBrandById02(@PathVariable int id){
//        return ResponseEntity.ok(brandServices.getBrandById02(id));
//    }



    @GetMapping("/brands/{id}")
    public ResponseEntity<BrandDetailDTO> getBrandById(@PathVariable int id) {
        Brand brand = brandServices.getBrandById(id);
        BrandDetailDTO brandDTO = modelMapper.map(brand, BrandDetailDTO.class);
        brandDTO.setNoOfSaleItems(brandDTO.getProducts().size());
        return ResponseEntity.ok(brandDTO);
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Object> deleteBrand(@PathVariable int id) {
        brandServices.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}