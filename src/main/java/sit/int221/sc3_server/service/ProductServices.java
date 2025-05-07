package sit.int221.sc3_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
//        return productRepository.findAllByOrderByCreatedOnDesc();
    }

    public Product getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("SaleItem not found for this id :: " + id));
        if (product.getDescription() != null) {
            String cleaned = product.getDescription().replaceAll("[\\n\\r\\u00A0\\u200B]", "").trim();
            product.setDescription(cleaned);
        }
        return product;
    }
}
