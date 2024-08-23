package dev.jatin.projectscaler2024.repository;

import dev.jatin.projectscaler2024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category save(Category category);
}
