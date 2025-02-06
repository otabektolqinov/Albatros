package com.example.albartros.repository;

import com.example.albartros.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findByIdAndDeletedAtIsNull(Long id);

    List<Destination> findAllByDeletedAtIsNull();
}
