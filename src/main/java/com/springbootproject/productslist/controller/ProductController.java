package com.springbootproject.productslist.controller;

import com.springbootproject.productslist.dto.ProductDTO;
import com.springbootproject.productslist.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API CRUD Operation",
        description = "CREATE, READ, UPDATE and DELETE operation for product REST API "
)

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
        //getAllProducts
    @Operation(
            summary = "Fetch all products",
            description = "REST API to Fetch all products"
    )
    @GetMapping
      public List<ProductDTO> getAllProduct(){
          return productService.getAllProducts();
      }

      //get product by id
      @Operation(
              summary = "Fetch  product by product id",
              description = "REST API to Fetch product by product id"
      )
        @GetMapping("/{id}")
        public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
        }

        //createProduct
        @Operation(
                summary = "Create  product",
                description = "REST API to create product"
        )
        @ApiResponse(
                responseCode = "201",
                description = "CREATED"
        )
        @PreAuthorize("hasAuthority('ROLE_SELLER')")
        @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createdProduct =  productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct , HttpStatus.CREATED);
    }

        //updateProduct
        @Operation(
                summary = "Update product by productId",
                description = "REST API to update product by productId"
        )
        @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id , @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }
        //delete product
        @Operation(
                summary = "Delete  product by productId",
                description = "REST API to delete product by productid"
        )
        @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
