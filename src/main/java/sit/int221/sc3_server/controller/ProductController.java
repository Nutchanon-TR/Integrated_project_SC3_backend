package sit.int221.sc3_server.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sc3_server.DTO.*;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.service.BrandServices;
import sit.int221.sc3_server.service.ProductServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v1")
//@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("/sale-items")
    public ResponseEntity<List<SalesItemDTO>> getAllSaleItem() {
        List<Product> productItem = productServices.getAllProduct();
        List<SalesItemDTO> productDto = listMapper.mapList(productItem, SalesItemDTO.class, modelMapper);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/sale-items/{id}")
    public ResponseEntity<SalesItemDetailDTO> getSaleItemById(@PathVariable int id) {
        return ResponseEntity.ok().body(modelMapper.map(productServices.getProductById(id), SalesItemDetailDTO.class));
    }

    @PostMapping("/sale-items")
    public ResponseEntity<SalesItemAllDataDTO> createSaleItem(@RequestBody @Valid SaleItemCreateDTO saleItemCreateDTO) {
        Product product = productServices.createProduct(saleItemCreateDTO);
        SalesItemAllDataDTO responseDto = modelMapper.map(product, SalesItemAllDataDTO.class);
        responseDto.setBrandName(product.getBrand().getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/sale-items/{id}")
    public ResponseEntity<SalesItemDetailDTO> updateSaleItem(@PathVariable int id, @RequestBody @Valid SaleItemCreateDTO productDto){
        Product product = productServices.updateProduct(id, productDto);

        var dto = modelMapper.map(product,SalesItemDetailDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/sale-items/{id}")
    public ResponseEntity<SalesItemDetailDTO> deleteSaleItem(@PathVariable int id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
