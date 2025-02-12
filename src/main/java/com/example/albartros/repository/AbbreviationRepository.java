package com.example.albartros.repository;

import com.example.albartros.model.Abbreviation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbbreviationRepository extends JpaRepository<Abbreviation, Long> {
    List<Abbreviation> findAllByCategory_Id(Long categoryId);

    List<Abbreviation> getAll();
}
