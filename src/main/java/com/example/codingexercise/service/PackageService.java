package com.example.codingexercise.service;

import com.example.codingexercise.gateway.CurrencyGateway;
import com.example.codingexercise.model.Package;
import com.example.codingexercise.model.Product;
import com.example.codingexercise.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.codingexercise.gateway.ProductServiceGateway;
import java.math.BigDecimal;
import java.util.List;
import com.example.codingexercise.dto.*;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private CurrencyGateway currencyGateway;

    public PackageDTO createPackage(PackageRequest pkgRequest) {
        // Create new package first
        Package pkg = new Package();
        pkg.setName(pkgRequest.getName());
        pkg.setDescription(pkgRequest.getDescription());

        // Fetch and create products with package reference
        List<Product> products = pkgRequest.getProducts().stream()
            .map(ProductServiceGateway::getProduct)
            .map(gatewayProduct -> {
                Product product = new Product();
                product.setProductId(gatewayProduct.id());
                product.setName(gatewayProduct.name());
                product.setPrice(BigDecimal.valueOf(gatewayProduct.usdPrice()));
                product.setPackage(pkg);  // Set the package reference
                return product;
            })
            .toList();

        pkg.setProducts(products);
        
        // Save and return the package
        packageRepository.save(pkg);
        return toDTO(pkg);
    }

    public PackageDTO getPackage(Long id, String currency) {
        Package pkg = packageRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Package not found"));
        
        if (!"USD".equals(currency)) {
            Double rate = currencyGateway.getExchangeRate(currency);
            convertPrices(pkg, rate);
        }
        
        return toDTO(pkg);
    }

    public Package updatePackage(Long id, Package pkg) {
        // Logic to update package
        if (packageRepository.existsById(id)) {
            pkg.setId(id);
            return packageRepository.save(pkg);
        }
        return null;
    }

    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }

    public List<PackageDTO> listPackages(String currency) {
        List<Package> packages = packageRepository.findAll();
        
        if (!"USD".equals(currency)) {
            Double rate = currencyGateway.getExchangeRate(currency);
            packages.forEach(pkg -> convertPrices(pkg, rate));
        }
        
        return packages.stream().map(this::toDTO).toList();
    }

    private void convertPrices(Package pkg, Double rate) {
        pkg.setPrice(pkg.getPrice().multiply(BigDecimal.valueOf(rate)));
        pkg.getProducts().forEach(product -> 
            product.setPrice(product.getPrice().multiply(BigDecimal.valueOf(rate)))
        );
    }

    private PackageDTO toDTO(Package pkg) {
        List<ProductDTO> productDTOs = pkg.getProducts().stream()
            .map(product -> new ProductDTO(
                product.getId(),
                product.getProductId(),
                product.getName(),
                product.getPrice()
            ))
            .toList();

        return new PackageDTO(
            pkg.getId(),
            pkg.getName(),
            pkg.getDescription(),
            productDTOs
        );
    }
} 