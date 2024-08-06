package dev.jatin.projectscaler2024.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
