package br.com.compassuol.sp.challenge.msproducts.controller;

import br.com.compassuol.sp.challenge.msproducts.dto.ProductDTO;
import br.com.compassuol.sp.challenge.msproducts.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Product Service - ProductController",
        description = "ProductController exposes REST APIs for Product Service"
)
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Operation(
            summary = "Create Product",
            description = "Create Product is used to save a product into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO savedProduct = productService.createProduct(productDTO);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Products",
            description = "Get All Products is used to get all products from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "linesPerPage", defaultValue = "20") int linesPerPage,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy

    ){
        List<ProductDTO> productDTOs = productService.getAllProducts(page,linesPerPage,direction,orderBy);

        return ResponseEntity.ok(productDTOs);
    }
    @Operation(
            summary = "Get One Product",
            description = "Get One Product is used to get one product by Id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 CREATED"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductById(id);

        return ResponseEntity.ok(productDTO);
    }

    @Operation(
            summary = "Update Product",
            description = "Update Product is used to update a product and save it into the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id){
        ProductDTO updateProduct = productService.updateProduct(productDTO, id);

        return ResponseEntity.ok(updateProduct);
    }

    @Operation(
            summary = "Delete Product",
            description = "Delete Product is used to delete a product by Id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product with id "+id+" was delete successfully");
    }
}
