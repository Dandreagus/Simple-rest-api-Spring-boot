package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public List<ProductModel> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return productService.createProduct(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        return productService.deleteProduct(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public ProductModel getProduct(@PathVariable("id") long id) {
           return productService.getProduct(id);
        }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyStock(@PathVariable("id") long id, @RequestParam("stock") int stock) {
        productService.modifyStock(id, stock);
    }

    @RequestMapping(value = "/sales/{category}", method = RequestMethod.GET)
    public void productOnSale(@PathVariable("category") String category, @RequestParam("sales") int sales) {
        productService.productOnSale(category, sales);
    }

}

