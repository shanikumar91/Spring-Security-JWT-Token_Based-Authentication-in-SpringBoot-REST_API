package com.springbootproject.productslist.service;

import com.springbootproject.productslist.dto.ProductDTO;
import com.springbootproject.productslist.entity.Category;
import com.springbootproject.productslist.entity.Product;
import com.springbootproject.productslist.exception.CategoryNotFoundException;
import com.springbootproject.productslist.mapper.ProductMapper;
import com.springbootproject.productslist.repository.CategoryRepository;
import com.springbootproject.productslist.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    public ProductDTO createProduct(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new CategoryNotFoundException("Category id "+productDTO.getCategoryId()+" not found"));
      //DTO -> Entity
      Product product =  ProductMapper.toProductEntity(productDTO,category);
        product = productRepository.save(product);

       // Entity -> DTO
       return ProductMapper.toProductDTO(product);
    }

    // get All products
      public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
      }
      // get product by id
      public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
        return ProductMapper.toProductDTO(product);
      }

      // update product
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
        Category category= categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    // Delete products
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product "+id+" has been deleted!";
    }
}
