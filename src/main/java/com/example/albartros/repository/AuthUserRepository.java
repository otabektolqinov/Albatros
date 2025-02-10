package com.example.albartros.repository;

import com.example.albartros.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsernameAndDeletedAtIsNull(String username);

    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<AuthUser> findByIdAndDeletedAtIsNull(Long id);

    List<AuthUser> findAllByDeletedAtIsNull();


}
