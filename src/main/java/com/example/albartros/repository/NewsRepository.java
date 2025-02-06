package com.example.albartros.repository;

import com.example.albartros.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByIdAndDeletedAtIsNull(Long id);

    List<News> findAllByDeletedAtIsNull();
}
