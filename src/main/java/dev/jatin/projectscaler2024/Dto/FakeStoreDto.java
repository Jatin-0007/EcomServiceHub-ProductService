package dev.jatin.projectscaler2024.Dto;

import dev.jatin.projectscaler2024.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
