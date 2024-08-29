package dev.jatin.projectscaler2024.service;

import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product getProductById(Long id) throws InvalidProductIdException;

    Page<Product> getAllProducts(int pageNumber,int pageSize,String sortDir);

    Product createProduct(Product product);

    Product replaceProduct(Long id, ProductDto productdto);

    Product updateProduct(Long id, ProductDto productdto);

    void deleteProductId(Long id) throws InvalidProductIdException;
}
