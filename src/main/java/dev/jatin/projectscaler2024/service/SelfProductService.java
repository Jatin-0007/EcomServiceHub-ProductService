package dev.jatin.projectscaler2024.service;

import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Category;
import dev.jatin.projectscaler2024.models.Product;
import dev.jatin.projectscaler2024.repository.CategoryRepo;
import dev.jatin.projectscaler2024.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        if (optionalProduct.isEmpty()) {
            return null;
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(ProductDto productdto) {
        Product product = convertproductDtoToProduct(productdto);

        Category category = product.getCategory();
        if (category.getId() == null) {
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        Product savedProduct = productRepo.save(product);


        return savedProduct;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productdto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productdto) {
       return null;

    }

   @Override
    public void deleteProductById(Long id) {

    }
}