package br.com.compassuol.sp.challenge.msproducts.service.impl;

import br.com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.sp.challenge.msproducts.entity.Product;
import br.com.compassuol.sp.challenge.msproducts.exception.ResourceNotFoundException;
import br.com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import br.com.compassuol.sp.challenge.msproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);

    }

    @Override
    public List<ProductDTO> getAllProducts(int page, int linesPerPage, String direction, String orderBy) {
        Sort sort = Sort.by(direction.equalsIgnoreCase("ASC") ?
                Sort.Direction.DESC : Sort.Direction.ASC, orderBy);
        Pageable pageable = PageRequest.of(page,linesPerPage,sort);
        List<Product> products = productRepository.findAll(pageable).toList();
        return products.stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        product.setId(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        Product updateProduct = productRepository.save(product);
        return mapToDTO(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        productRepository.delete(product);
    }




    private ProductDTO mapToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
    private Product mapToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
