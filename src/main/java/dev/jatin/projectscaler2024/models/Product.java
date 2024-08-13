package dev.jatin.projectscaler2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
  //@Id //primary key annotation
 //  private Long id;
    private String title;
   private double price;
   @ManyToOne
   private Category category;
    private String description;
    private String image;
}

/*

  1     ->    1
product ---- category  ===> M:1
   M     <-   1
 ----------------------
 M              1
 */
