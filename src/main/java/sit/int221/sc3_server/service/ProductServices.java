package sit.int221.sc3_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.sc3_server.DTO.SalesItemDetailDTO;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
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


    public SalesItemDetailDTO createProduct(SalesItemDetailDTO salesItemDetailDTO){
        if(productRepository.existsById(salesItemDetailDTO.getId())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"SaleItem "+ salesItemDetailDTO.getId() + "already exists ");
        }
    Product product = modelMapper.map(salesItemDetailDTO, Product.class);
        return modelMapper.map(productRepository.saveAndFlush(product),SalesItemDetailDTO.class);
    }

}
