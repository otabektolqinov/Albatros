package com.example.albartros.repository;

import com.example.albartros.enums.UserRole;
import com.example.albartros.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByIdAndDeletedAtIsNull(Long id);

    Optional<User> findByIdAndDeletedAtIsNull(Long id);

    List<User> findAllByDeletedAtIsNull();

    List<User> findAllByRoleAndDeletedAtIsNull(UserRole userRole);

}
