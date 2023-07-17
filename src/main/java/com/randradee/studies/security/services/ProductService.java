package com.randradee.studies.security.services;

import com.randradee.studies.security.domain.product.Product;
import com.randradee.studies.security.domain.product.ProductRequestDTO;
import com.randradee.studies.security.domain.product.ProductResponseDTO;
import com.randradee.studies.security.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductResponseDTO> getProducts(){
        return this.productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
    }


    public void createProduct(ProductRequestDTO body) {
        Product produto = new Product();
        BeanUtils.copyProperties(body, produto);
        this.productRepository.save(produto);
    }
}
