package dev.jatin.projectscaler2024.service;

import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Category;
import dev.jatin.projectscaler2024.models.Product;
import dev.jatin.projectscaler2024.repository.CategoryRepo;
import dev.jatin.projectscaler2024.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.JpaSort.of;

@Service("SelfProductService")
public class SelfProductService implements ProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo,CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product convertproductDtoToProduct(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setTitle(productDto.getTitle());
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct = productRepo.findById(id);

        if (optionalProduct.isEmpty()) throw new InvalidProductIdException(id,"Invalid ID");


        return optionalProduct.get();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize,String sortDir) {
        return productRepo.findAll(PageRequest.of(pageNumber,pageSize,
                sortDir.equals("asc") ? Sort.by("price").ascending() :     // if sortDir=asc do ascending order else do desc.
                        Sort.by("price").descending()));
    }

    @Override
    public Product createProduct(Product product) {
       // Product product = convertproductDtoToProduct(productdto);


      //  Category category = product.getCategory();
//        if (category.getId() == null) {
// first save category then save product as category is a attribute of product and also a class
// this is brute way to save category but optimised it via CASCADE.ALL
//            Category savedCategory = categoryRepo.save(category);
//            product.setCategory(savedCategory);
//        }
        Product savedProduct = productRepo.save(product);


        return savedProduct;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productdto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productdto) {
       Product product = convertproductDtoToProduct(productdto);

       Optional<Product> optionalProduct = productRepo.findById(id);

       if (optionalProduct.isEmpty()) throw new RuntimeException();

       if (product == null) throw new RuntimeException("Invalid Input");

       Product currentProduct = optionalProduct.get();

       if(product.getTitle() != null){
           currentProduct.setTitle(product.getTitle());
       }

       if(product.getDescription() != null){
           currentProduct.setDescription(product.getDescription());
       }

       return productRepo.save(currentProduct);


    }

   @Override
    public void deleteProductId(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty()) throw new InvalidProductIdException(id,"Invalid ID");

        productRepo.deleteById(id);

    }
}
