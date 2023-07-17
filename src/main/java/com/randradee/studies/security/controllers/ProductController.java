package com.randradee.studies.security.controllers;

import com.randradee.studies.security.domain.ProductRequestDTO;
import com.randradee.studies.security.domain.ProductResponseDTO;
import com.randradee.studies.security.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts(){
        return ResponseEntity.ok().body(this.productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<String> postProduct(@RequestBody @Valid ProductRequestDTO body){
        this.productService.createProduct(body);
        return ResponseEntity.ok().body("Produto salvo com sucesso.");
    }
}
