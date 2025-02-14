package com.example.albartros.repository;

import com.example.albartros.model.InsurancePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsurancePurchaseRepository extends JpaRepository<InsurancePurchase, Long> {

    Optional<InsurancePurchase> findByBookings_id(Long id);

    Optional<InsurancePurchase> findByIdAndDeletedAtIsNull(Long id);
}
