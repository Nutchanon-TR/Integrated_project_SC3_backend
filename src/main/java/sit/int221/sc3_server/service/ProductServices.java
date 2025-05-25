package sit.int221.sc3_server.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sit.int221.sc3_server.DTO.SaleItemCreateDTO;
import sit.int221.sc3_server.DTO.SalesItemAllDataDTO;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.exception.CreateFailedException;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.exception.UpdateFailedException;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;

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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product createProduct(SaleItemCreateDTO dto) {
        int brandId = dto.getBrand().getId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ItemNotFoundException("Brand with ID " + brandId + " not found."));

        String model = dto.getModel().trim();
        boolean isDuplicate = productRepository.existsByModelIgnoreCase(model);

        if (isDuplicate) {
            throw new CreateFailedException("Cannot create SaleItem: model '" + model + "' already exists.");
        }

        Product product = modelMapper.map(dto, Product.class);
        product.setBrand(brand);

        try {
            return productRepository.saveAndFlush(product);
        } catch (Exception e) {
            throw new CreateFailedException(
                    "Cannot create SaleItem due to: " + e.getMessage()
            );
        }
    }

    public Product updateProduct(int id, SaleItemCreateDTO newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));

        Brand brand = brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found with ID."));

        if (newProduct.getQuantity() == null || newProduct.getQuantity() < 0) {
            newProduct.setQuantity(1);
        }

        try {
            existing.setModel(newProduct.getModel());
            existing.setBrand(brand);
            existing.setDescription(newProduct.getDescription());
            existing.setPrice(newProduct.getPrice());
            existing.setRamGb(newProduct.getRamGb());
            existing.setScreenSizeInch(newProduct.getScreenSizeInch());
            existing.setQuantity(newProduct.getQuantity());
            existing.setStorageGb(newProduct.getStorageGb());
            existing.setColor(newProduct.getColor());


            return productRepository.save(existing);
        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated: " + e.getMessage());

        }
    }

    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product ID not found"));
        productRepository.deleteById(id);
        return product;
    }

}
