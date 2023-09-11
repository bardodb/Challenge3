package br.com.compassuol.sp.challenge.msproducts.service;

import br.com.compassuol.sp.challenge.msproducts.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);


    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(ProductDTO productDTO, Long id);

    void deleteProduct(Long id);

    List<ProductDTO> getAllProducts(int page, int linesPerPage, String direction, String orderBy);
}
