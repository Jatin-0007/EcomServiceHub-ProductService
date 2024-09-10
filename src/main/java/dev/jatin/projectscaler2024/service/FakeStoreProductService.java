package dev.jatin.projectscaler2024.service;

import dev.jatin.projectscaler2024.Dto.FakeStoreDto;
import dev.jatin.projectscaler2024.Dto.ProductDto;
import dev.jatin.projectscaler2024.exceptions.InvalidProductIdException;
import dev.jatin.projectscaler2024.models.Category;
import dev.jatin.projectscaler2024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;


    public FakeStoreProductService(RestTemplate restTemplate,RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public Product convertFakestoreDtoToProduct(FakeStoreDto fakeStoreDto) {

        Product product = new Product();
        product.setId(fakeStoreDto.getId());
        product.setDescription(fakeStoreDto.getDescription());
        product.setImage(fakeStoreDto.getImage());
        product.setPrice(fakeStoreDto.getPrice());
        product.setTitle(fakeStoreDto.getTitle());

        Category category = new Category();
        category.setTitle(fakeStoreDto.getTitle());
        product.setCategory(category);
        return product;

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
                                                                    //Table name             product id
        Product product =(Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+id);

        if(product!=null){
            //Cache Hit
            return product;
        }


        FakeStoreDto fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreDto.class);

        if(fakeStoreDto == null){
            throw new InvalidProductIdException(id,"Invalid Product Id Passed");

        }

        product = convertFakestoreDtoToProduct(fakeStoreDto);

        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+id,product);

        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber,int pageSize,String sortDir) {

        FakeStoreDto[] fakeStoreDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreDto fakeStoreDto : fakeStoreDtos){

            products.add(convertFakestoreDtoToProduct(fakeStoreDto));


        }

        return new PageImpl<>(products);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
       // RequestCallback requestCallback = restTemplate.httpEntityCallback(productdto, ProductDto.class);
    //    HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor<>(ProductDto.class, restTemplate.getMessageConverters());

      //  ProductDto productDto2= restTemplate.execute("https://fakestoreapi.com/products", HttpMethod.POST, requestCallback, responseExtractor);

       // return convertproductDtoToProduct(productDto2);

    }

    @Override
    public Product replaceProduct(Long id, ProductDto productdto) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productdto, ProductDto.class);
        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor(ProductDto.class, restTemplate.getMessageConverters());

        ProductDto productdto1  = restTemplate.execute("https://fakestoreapi.com/products/"+ id, HttpMethod.PUT, requestCallback, responseExtractor);


        return convertproductDtoToProduct(productdto1) ;
    }

    @Override
    public Product updateProduct(Long id,ProductDto productdto) {

        RequestCallback requestCallback = restTemplate.httpEntityCallback(productdto, ProductDto.class);
        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor<>(ProductDto.class, restTemplate.getMessageConverters());

        ProductDto productdto3 = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertproductDtoToProduct(productdto3);

    }

    @Override
    public void deleteProductId(Long id) throws InvalidProductIdException{

        FakeStoreDto fakeStoreDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreDto.class);

        if(fakeStoreDto == null){
            throw new InvalidProductIdException(id,"Invalid Product Id Passed");

        }

        restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.DELETE, null, null);

    }
}
