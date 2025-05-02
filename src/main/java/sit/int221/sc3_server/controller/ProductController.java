package sit.int221.sc3_server.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sc3_server.DTO.SalesItemDetailDTO;
import sit.int221.sc3_server.DTO.salesItemDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.service.ProductServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/itb-mshop/v1")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")
=======
@RequestMapping("/itb-mshop")
// @CrossOrigin(origins = "${app.cors.allowedOrigins}")
>>>>>>> 1e1ab98e0851a70dae63b70acc643e07a9b0fee7
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;
    @GetMapping("/sale-items")
    public ResponseEntity<List<salesItemDTO>> getAllSaleItem(){
        List<Product> productItem = productServices.getAllProduct();
        List<salesItemDTO> productDto = listMapper.mapList(productItem, salesItemDTO.class,modelMapper);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/sale-items/{id}")
    public  ResponseEntity<SalesItemDetailDTO> getSaleItemById(@PathVariable int id){
        return ResponseEntity.ok().body(modelMapper.map(productServices.getProductById(id), SalesItemDetailDTO.class));
    }

}
