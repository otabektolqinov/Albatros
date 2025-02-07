package com.example.albartros.repository;

import com.example.albartros.model.Facts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactRepository extends JpaRepository<Facts, Long> {
    Optional<Facts> findByIdAndDeletedAtIsNull(Long id);
    boolean existsByIdAndDeletedAtIsNull(Long id);
    List<Facts> findAllByDeletedAtIsNull();
}
