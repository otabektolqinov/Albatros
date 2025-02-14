package com.example.albartros.repository;

import com.example.albartros.model.AbbreviationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbbreviationCategoryRepository extends JpaRepository<AbbreviationCategory, Long> {
    Optional<AbbreviationCategory> findByIdAndDeletedAtIsNull(Long id);
}
