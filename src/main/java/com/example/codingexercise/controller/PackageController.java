package com.example.codingexercise.controller;

import com.example.codingexercise.dto.PackageRequest;
import com.example.codingexercise.dto.PackageDTO;
import com.example.codingexercise.model.Package;
import com.example.codingexercise.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping
    public ResponseEntity<PackageDTO> createPackage(@RequestBody PackageRequest pkg) {
        PackageDTO createdPackage = packageService.createPackage(pkg);
        return ResponseEntity.ok(createdPackage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageDTO> getPackage(
            @PathVariable Long id,
            @RequestParam(defaultValue = "USD") String currency) {
        PackageDTO pkg = packageService.getPackage(id, currency);
        return ResponseEntity.ok(pkg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable Long id, @RequestBody Package pkg) {
        Package updatedPackage = packageService.updatePackage(id, pkg);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PackageDTO>> listPackages(
            @RequestParam(defaultValue = "USD") String currency) {
        List<PackageDTO> packages = packageService.listPackages(currency);
        return ResponseEntity.ok(packages);
    }
}