package com.example.albartros.repository;

import com.example.albartros.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<Agency> findByIdAndDeletedAtIsNull(Long id);

    List<Agency> findAllByDeletedAtIsNull();
}
