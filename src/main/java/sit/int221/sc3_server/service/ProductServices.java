package sit.int221.sc3_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;

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
