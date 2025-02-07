package com.example.albartros.repository;

import com.example.albartros.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByIdAndDeletedAtIsNull(Long id);
    boolean existsByIdAndDeletedAtIsNull(Long id);
    List<Hotel> findAllByDeletedAtIsNull();
}
