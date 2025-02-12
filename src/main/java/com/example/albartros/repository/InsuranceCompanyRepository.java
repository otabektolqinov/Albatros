package com.example.albartros.repository;

import com.example.albartros.model.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long> {
    Optional<InsuranceCompany> findByIdAndDeletedAtIsNull(Long id);

    List<InsuranceCompany> findAllByDeletedAtIsNull();
}
