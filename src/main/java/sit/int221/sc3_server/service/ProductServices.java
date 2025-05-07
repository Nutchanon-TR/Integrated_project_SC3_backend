package sit.int221.sc3_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sc3_server.DTO.SalesItemAllDataDTO;
import sit.int221.sc3_server.DTO.SalesItemDetailDTO;
import sit.int221.sc3_server.DTO.SalesItemCreateAndUpdate;
import sit.int221.sc3_server.DTO.SalesItemDetailDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.time.Instant;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;


    public List<Product> getAllProduct() {
        return productRepository.findAll();

//        return productRepository.findAllByOrderByCreatedOnDesc();

    }

//    public Product getProductById(int id) {
//        return productRepository.findById(id).orElseThrow(
//                () -> new ItemNotFoundException("SaleItem not found for this id :: " + id)
//        );
//    }
    public Product getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("SaleItem not found for this id :: " + id));
        if (product.getDescription() != null) {

            String cleaned = product.getDescription().replaceAll("[\\n\\r\\u00A0\\u200B]", "").trim();
            product.setDescription(cleaned);
        }
        return product;
    }



    public  Product createProduct(SalesItemCreateAndUpdate salesItemCreateAndUpdate){
        if(productRepository.existsById(salesItemCreateAndUpdate.getId())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"SaleItem "+ salesItemCreateAndUpdate.getId() + "already exists ");
        }
        int brandId = salesItemCreateAndUpdate.getBrand().getId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));

        Product product =  modelMapper.map(salesItemCreateAndUpdate,Product.class );
        product.setBrand(brand);
       return productRepository.saveAndFlush(product);
    }


    public Product updateProduct(int id, SalesItemCreateAndUpdate newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product ID not found"));
        Product updated = modelMapper.map(newProduct, Product.class);
        updated.setId(existing.getId());
//        updated.setCreatedOn(existing.getCreatedOn());
//        updated.setUpdatedOn(Instant.now());
        return productRepository.saveAndFlush(updated);
    }

}
