package com.iqbal.store.controllers;

import com.iqbal.store.dtos.ProductDto;
import com.iqbal.store.mappers.ProductMapper;
import com.iqbal.store.repositories.CategoryRepository;
import com.iqbal.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/api/v2")
@AllArgsConstructor
public class ProductController {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    
    @GetMapping("/products")
    public Iterable<ProductDto> getAllProduct(
            @RequestParam(required = false) Byte categoryId,
            @RequestParam(defaultValue = "id") String sort
    ) {
        if (!Set.of("id", "category").contains(sort)) {
            sort = "id";
        }
        return productRepository.findAll(Sort.by(sort))
                .stream()
                .map(product -> productMapper.toDto(product))
                .toList();
    }
    @GetMapping("/products/{proId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long proId){
        var product = productRepository.findById(proId).orElse(null);

        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder
    )
    {

        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ){
        var cateroryId = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (cateroryId == null){
            return ResponseEntity.badRequest().build();
        }
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productMapper.update(productDto, product);
        product.setCategory(cateroryId);
        productRepository.save(product);
        productDto.setId(product.getId());
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ){
        var product = productRepository.findById(id).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
