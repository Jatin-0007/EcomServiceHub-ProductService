package dev.jatin.projectscaler2024.Controller;

import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Product;
import dev.jatin.projectscaler2024.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
private ProductService productService;

    ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {

        Product product = productService.getProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK); //using responce entity to give a httpstatus code

       // Using ResponseEntity allows you to: Explicitly set the HTTP status code that will be returned to the client.

      //  int a = 1/0; // to check the exception is handelled this is for arthematic exception
        //return null;

    }

    @GetMapping("/")
    public Page<Product> getAllProducts(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize,@Param("sortDir") String sortDir) throws InvalidProductIdException {
        return  productService.getAllProducts(pageNumber, pageSize,sortDir);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDto productdto) {
         return productService.replaceProduct(id,productdto);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productdto) {
        return productService.updateProduct(id,productdto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws InvalidProductIdException {
        productService.deleteProductId(id);

    }

}
