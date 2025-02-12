package com.example.albartros.repository;

import com.example.albartros.model.CoverageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverageDetailsRepository extends JpaRepository<CoverageDetails, Long> {
    Optional<CoverageDetails> findByIdAndDeletedAtIsNull(Long id);
}
