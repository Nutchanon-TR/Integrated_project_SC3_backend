package sit.int221.sc3_server.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sit.int221.sc3_server.entity.Product;
import sit.int221.sc3_server.repository.BrandRepository;
import sit.int221.sc3_server.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceV2 {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Page<Product> getAllProduct(List<String> filterBrands, Integer page, Integer size, String sortField, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Direction directionId = Sort.Direction.ASC;
        //No filter
        if (filterBrands == null || filterBrands.isEmpty()) {
            System.out.println("Non Filter By brand Id");
            return productRepository.findAll(PageRequest.of(page, size,Sort.by(direction, sortField).and(Sort.by(directionId, "id"))));
        }
        //Filter by BrandName
        else {
            System.out.println("Filter By brand Id filterBrands: "+filterBrands);
            return productRepository.findByBrand_NameIn(filterBrands, PageRequest.of(page, size, Sort.by(direction, sortField).and(Sort.by(directionId, "id"))));
        }
    }
}