package dev.jatin.projectscaler2024.repository;

import dev.jatin.projectscaler2024.models.Product;
import dev.jatin.projectscaler2024.repository.Projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    //Declared Queries
         Optional<Product> findById(Long id);

          Product save(Product product);

          void deleteById(Long id);

          // HQL i.e custom query : class name used here

    @Query("select p.id as id,p.title as title from Product p where p.price>120000 and lower(p.title) like '%pro%'")
    List<ProductWithIdAndTitle> someRandomQuery();

    @Query("select p.id as id,p.title as title from Product p where p.id=:id")
    ProductWithIdAndTitle doSomething(@Param("id") Long id);


    //native Queries : table name used here

    @Query(value = "select * from product p where p.id=52",nativeQuery = true)
    Product doSomethingSQL();






}
