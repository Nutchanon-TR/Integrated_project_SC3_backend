package sit.int221.sc3_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.service.BrandServices;

import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v1")
//@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class BrandController {
    @Autowired
    private BrandServices brandServices;

        @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands(){
        var brand = brandServices.getAllBrand();
        return ResponseEntity.ok(brand);
    }
}
