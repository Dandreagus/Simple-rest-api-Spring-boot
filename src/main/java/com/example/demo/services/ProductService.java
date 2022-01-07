package com.example.demo.services;

import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import com.sun.net.httpserver.Authenticator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> getProducts() {
        return (List<ProductModel>) productRepository.findAll();
    }

    public ProductModel getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public ProductModel createProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        Optional<ProductModel> productModel = productRepository.findById(id);
        if (productModel.isPresent()) {
            productRepository.deleteById(id);
            return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Error>(HttpStatus.NOT_FOUND);
        }
    }

    public void modifyStock(Long id, int stock) {
        ProductModel product = productRepository.findById(id).get();
        if (product != null) {
            product.setStock(stock);
            productRepository.save(product);
        }
    }

    public void productOnSale(String category, int percentage) {
        Iterable<ProductModel> productRepositoryAll = productRepository.findAll();
        for (ProductModel productModel : productRepositoryAll) {
            if(StringUtils.equals(category, productModel.getCategory())) {
                int oldPrice = productModel.getPrice();
                int sale = (percentage * productModel.getPrice()) / 100;
                productModel.setPrice(oldPrice - sale);
                productRepository.save(productModel);
            }
        }
    }

}
