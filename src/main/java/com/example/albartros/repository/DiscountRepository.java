package com.example.albartros.repository;

import com.example.albartros.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<Discount> findByIdAndDeletedAtIsNull(Long id);

    List<Discount> findAllByDeletedAtIsNull();
}
