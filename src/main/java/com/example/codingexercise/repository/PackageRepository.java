package com.example.codingexercise.repository;

import com.example.codingexercise.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    // Additional query methods can be defined here if needed
}
