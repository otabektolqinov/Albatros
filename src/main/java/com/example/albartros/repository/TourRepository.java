package com.example.albartros.repository;

import com.example.albartros.model.Tours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tours, Long> {
    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<Tours> findByIdAndDeletedAtIsNull(Long id);

    List<Tours> findAllByDeletedAtIsNull();
}
