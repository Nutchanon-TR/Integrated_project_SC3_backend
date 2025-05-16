package sit.int221.sc3_server.service;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sit.int221.sc3_server.DTO.SaleItemCreateDTO;
import sit.int221.sc3_server.DTO.SalesItemAllDataDTO;
import sit.int221.sc3_server.DTO.SalesItemCreateAndUpdate;
import sit.int221.sc3_server.entity.Brand;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.exception.CreateFailedException;
import sit.int221.sc3_server.exception.ItemNotFoundException;
import sit.int221.sc3_server.exception.UpdateFailedException;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.time.Instant;
import java.time.LocalDateTime;
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

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product createProduct(SaleItemCreateDTO dto) {
        int brandId = dto.getBrand().getId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ItemNotFoundException("Brand with ID " + brandId + " not found."));
        Product product = modelMapper.map(dto, Product.class);
        product.setBrand(brand);
//        product.setCreatedOn(Instant.now());
//        product.setUpdatedOn(Instant.now());
//        product.setCreatedOn(LocalDateTime.now());
//        product.setUpdatedOn(LocalDateTime.now());
        try {
            return productRepository.saveAndFlush(product);
        } catch (Exception e) {
            throw new CreateFailedException(
                    "Cannot create SaleItem due to: " + e.getMessage()
            );
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SalesItemAllDataDTO createProduct02(SaleItemCreateDTO dto) {
        int brandId = dto.getBrand().getId();
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new ItemNotFoundException("Brand with ID " + brandId + " not found."));

        Product product = modelMapper.map(dto, Product.class);
        product.setBrand(brand);

        try {
            Product savedProduct = productRepository.saveAndFlush(product);
            SalesItemAllDataDTO responseDto = modelMapper.map(savedProduct, SalesItemAllDataDTO.class);
            responseDto.setBrandName(brand.getName()); // ตั้งค่าเพิ่มจาก brand entity
            return responseDto;
        } catch (Exception e) {
            throw new CreateFailedException(
                    "Cannot create SaleItem due to: " + e.getMessage()
            );
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product updateProduct(int id, SalesItemCreateAndUpdate newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));

        Brand brand =  brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));

        try {
//            Product updated = modelMapper.map(newProduct, Product.class);
                existing.setBrand(brand);
                modelMapper.map(newProduct,existing);

//            updated.setId(existing.getId());
//            updated.setCreatedOn(existing.getCreatedOn());

//            LocalDateTime now = existing.getCreatedOn();
//            LocalDateTime plusYear = now.plusYears(543);
//            updated.setCreatedOn(plusYear);

//            updated.setUpdatedOn(LocalDateTime.now());
//            System.out.println(LocalDateTime.now());
//            updated.setUpdatedOn(Instant.now());
            return productRepository.saveAndFlush(existing);
        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated" + e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SalesItemAllDataDTO updateProduct03(int id, SalesItemCreateAndUpdate newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));

        Brand brand = brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));

        try {
            // อัปเดตค่า field ทีละตัวใน existing object
            existing.setModel(newProduct.getModel());
            existing.setDescription(newProduct.getDescription());
            existing.setPrice(newProduct.getPrice());
            existing.setQuantity(newProduct.getQuantity());
            existing.setRamGb(newProduct.getRamGb());
            existing.setScreenSizeInch(newProduct.getScreenSizeInch());
            existing.setStorageGb(newProduct.getStorageGb());
            existing.setColor(newProduct.getColor());
            existing.setBrand(brand); // ต้อง set brand object ให้ถูกต้อง

            Product saved = productRepository.saveAndFlush(existing);

            // map กลับไป DTO เพื่อส่งกลับให้ Controller
            SalesItemAllDataDTO resultDto = modelMapper.map(saved, SalesItemAllDataDTO.class);
            resultDto.setBrandName(brand.getName());
            return resultDto;

        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated: " + e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SalesItemAllDataDTO updateProduct02(int id, @Valid SaleItemCreateDTO newProduct){
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));
        System.out.println("Before Save:");
        System.out.println("createdOn = " + existing.getCreatedOn());
        System.out.println("updatedOn = " + existing.getUpdatedOn());
        Brand brand = brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));
        existing.setModel(newProduct.getModel());
        existing.setDescription(newProduct.getDescription());
        existing.setPrice(newProduct.getPrice());
        existing.setQuantity(newProduct.getQuantity());
        existing.setRamGb(newProduct.getRamGb());
        existing.setScreenSizeInch(newProduct.getScreenSizeInch());
        existing.setStorageGb(newProduct.getStorageGb());
        existing.setColor(newProduct.getColor());
        existing.setBrand(brand);
//            modelMapper.map(newProduct, existing);


        try{
//            return productRepository.saveAndFlush(updated);
            Product updated = productRepository.saveAndFlush(existing);
            SalesItemAllDataDTO result = modelMapper.map(updated, SalesItemAllDataDTO.class);
            result.setBrandName(updated.getBrand().getName());
            return  result;
        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated" + e.getMessage());
        }
    }
    
    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product ID not found"));
        productRepository.deleteById(id);
        return product;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product updateProduct05(int id, @Valid SaleItemCreateDTO newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));
        Brand brand = brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));

        existing.setModel(newProduct.getModel());
        existing.setDescription(newProduct.getDescription());
        existing.setPrice(newProduct.getPrice());
        existing.setQuantity(newProduct.getQuantity());
        existing.setRamGb(newProduct.getRamGb());
        existing.setScreenSizeInch(newProduct.getScreenSizeInch());
        existing.setStorageGb(newProduct.getStorageGb());
        existing.setColor(newProduct.getColor());
        existing.setBrand(brand);

        try {
            return productRepository.saveAndFlush(existing); // คืน entity
        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated: " + e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SalesItemAllDataDTO updateProduct04(int id, @Valid SaleItemCreateDTO newProduct) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Sale Item Not Found by Id"));
        Brand brand = brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new ItemNotFoundException("Brand not found"));

        // ใช้ helper method เพื่อจัดการ business logic
        processAndUpdateProduct(existing, newProduct, brand);

        try {
            Product updated = productRepository.saveAndFlush(existing);
            SalesItemAllDataDTO result = modelMapper.map(updated, SalesItemAllDataDTO.class);
            result.setBrandName(updated.getBrand().getName());
            return result;
        } catch (Exception e) {
            throw new UpdateFailedException("SaleItem " + id + " not updated: " + e.getMessage());
        }
    }
    private void processAndUpdateProduct(Product existing, SaleItemCreateDTO newProduct, Brand brand) {
        // จัดการและ validate data
        Integer quantity = newProduct.getQuantity();
        if (quantity == null || quantity <= 0) {
            quantity = 1;
        }

        String color = newProduct.getColor();
        if (color != null && color.trim().isEmpty()) {
            color = null;
        } else if (color != null) {
            color = color.trim();
        }

        String model = newProduct.getModel();
        if (model != null) {
            model = model.trim();
        }

        String description = newProduct.getDescription();
        if (description != null) {
            description = description.trim();
        }

        // Update existing product
        existing.setModel(model);
        existing.setDescription(description);
        existing.setPrice(newProduct.getPrice());
        existing.setQuantity(quantity);
        existing.setRamGb(newProduct.getRamGb());
        existing.setScreenSizeInch(newProduct.getScreenSizeInch());
        existing.setStorageGb(newProduct.getStorageGb());
        existing.setColor(color);
        existing.setBrand(brand);
//        existing.setUpdatedOn(LocalDateTime.now());
        existing.setUpdatedOn(Instant.now());
    }
}
