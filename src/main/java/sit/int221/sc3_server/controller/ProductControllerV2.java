package sit.int221.sc3_server.controller;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int221.sc3_server.DTO.PageDTO;
import sit.int221.sc3_server.DTO.SalesItemAllDataDTO;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.service.ProductServiceV2;
import sit.int221.sc3_server.service.ProductServices;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/itb-mshop/v2")
@CrossOrigin(origins = "${app.cors.allowedOrigins}")

public class ProductControllerV2 {
    @Autowired
    private ProductServiceV2 productServiceV2;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("/sale-items")
    public ResponseEntity<PageDTO<SalesItemAllDataDTO>> getAllSaleItem(
            @RequestParam(required = false) List<String> filterBrands,
            @RequestParam Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer size,
            @RequestParam(defaultValue = "id",required = false) String sortField,
            @RequestParam(defaultValue = "asc", required = false) String sortDirection
    ) {
        System.out.println("filterBrands: " + filterBrands);
        System.out.println("sortField: "+sortField);
        Page<Product> products = productServiceV2.getAllProduct(filterBrands, page, size, sortField, sortDirection);
        System.out.println();
        PageDTO<SalesItemAllDataDTO> pageDTO = listMapper.toPageDTO(products, SalesItemAllDataDTO.class, modelMapper);
        return ResponseEntity.ok(pageDTO);
    }
}