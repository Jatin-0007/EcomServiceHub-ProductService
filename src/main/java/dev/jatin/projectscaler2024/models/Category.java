package dev.jatin.projectscaler2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="msc_category")  // custom name for ur table
public class Category extends BaseModel {
//@Id
    //private Long id;
    private String title;
}
