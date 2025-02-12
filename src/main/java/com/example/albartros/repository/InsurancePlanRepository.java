package com.example.albartros.repository;

import com.example.albartros.model.InsurancePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Long> {
    Optional<InsurancePlan> findByIdAndDeletedAtIsNull(Long id);

    List<InsurancePlan> findAllByDeletedAtIsNull();

    List<InsurancePlan> findAllByDeletedAtIsNullAndInsuranceCompany_Id(Long id);


}
