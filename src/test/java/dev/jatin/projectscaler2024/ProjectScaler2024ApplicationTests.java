package dev.jatin.projectscaler2024;

import dev.jatin.projectscaler2024.models.Product;
import dev.jatin.projectscaler2024.repository.ProductRepo;
import dev.jatin.projectscaler2024.repository.Projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProjectScaler2024ApplicationTests {

    @Autowired
    ProductRepo productRepo;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries(){
//        List<ProductWithIdAndTitle> productWithIdAndTitles = productRepo.someRandomQuery();
//
//        for(ProductWithIdAndTitle productWithIdAndTitle : productWithIdAndTitles){
//       System.out.println(productWithIdAndTitle.getId());
//       System.out.println(productWithIdAndTitle.getTitle());     ----
//   }                                                                 |
 //                                                                    |----- HQL Test
    //                                                                 |
//   ProductWithIdAndTitle product = productRepo.doSomething(52L); ----
//        System.out.println(product.getId());
//        System.out.println(product.getTitle());

//        Product product = productRepo.doSomethingSQL(); ----> Native Query Test
        System.out.println("DEBUG");
    }

}
