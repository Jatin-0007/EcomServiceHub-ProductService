package dev.jatin.projectscaler2024.service;

import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws InvalidProductIdException;

    List<Product> getAllProducts();

    Product createProduct(ProductDto productdto);

    Product replaceProduct(Long id, ProductDto productdto);

    Product updateProduct(Long id, ProductDto productdto);

    void deleteProductById(Long id);
}
