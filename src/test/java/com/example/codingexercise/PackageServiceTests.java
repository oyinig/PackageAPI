package com.example.codingexercise;

import com.example.codingexercise.dto.PackageDTO;
import com.example.codingexercise.dto.PackageRequest;
import com.example.codingexercise.gateway.CurrencyGateway;
import com.example.codingexercise.gateway.ProductServiceGateway;
import com.example.codingexercise.gateway.dto.Product;
import com.example.codingexercise.model.Package;
import com.example.codingexercise.repository.PackageRepository;
import com.example.codingexercise.service.PackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PackageServiceTests {

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private CurrencyGateway currencyGateway;

    @Mock
    private ProductServiceGateway productServiceGateway;

    @InjectMocks
    private PackageService packageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Define ProductRequest class within the test file
    private static class ProductRequest {
        private String productId;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

    @Test
    public void testCreatePackage() {
        // Arrange
        PackageRequest pkgRequest = new PackageRequest();
        pkgRequest.setName("Test Package");
        pkgRequest.setDescription("Test Description");
        pkgRequest.setProducts(List.of("VqKb4tyj9V6i"));

        // Create a product and add it to the package request
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("VqKb4tyj9V6i");

        // Instantiate the Product record with the correct constructor arguments
        Product gatewayProduct = new Product("VqKb4tyj9V6i", "Test Product", 100);

        when(productServiceGateway.getProduct("VqKb4tyj9V6i")).thenReturn(gatewayProduct);

        Package savedPackage = new Package();
        savedPackage.setId(1L);
        savedPackage.setName(pkgRequest.getName());
        savedPackage.setDescription(pkgRequest.getDescription());

        when(packageRepository.save(any(Package.class))).thenReturn(savedPackage);

        // Act
        PackageDTO result = packageService.createPackage(pkgRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Test Package", result.name());
        verify(packageRepository, times(1)).save(any(Package.class));
    }

//    @Test
//    public void testGetPackage() {
//        // Arrange
//        Package pkg = new Package();
//        pkg.setId(1L);
//        pkg.setName("Test Package");
//        pkg.setDescription("Test Description");
//        pkg.setPrice(BigDecimal.valueOf(100.0));
//        pkg.setProducts(List.of(new com.example.codingexercise.model.Product()));
//
//
//        when(packageRepository.findById(1L)).thenReturn(Optional.of(pkg));
//        when(currencyGateway.getExchangeRate("EUR")).thenReturn(0.85);
//
//        // Act
//        PackageDTO result = packageService.getPackage(1L, "EUR");
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("Test Package", result.name());
//        verify(currencyGateway, times(1)).getExchangeRate("EUR");
//    }
//
//    @Test
//    public void testUpdatePackage() {
//        // Arrange
//        Package pkg = new Package();
//        pkg.setId(1L);
//        pkg.setName("Old Name");
//        pkg.setDescription("Old Description");
//        pkg.setPrice(BigDecimal.valueOf(100.0));
//
//
//        PackageRequest pkgRequest = new PackageRequest();
//        pkgRequest.setName("New Name");
//        pkgRequest.setDescription("New Description");
//        pkgRequest.setProducts(List.of("VqKb4tyj9V6i"));
//
//        when(packageRepository.findById(1L)).thenReturn(Optional.of(pkg));
//        when(packageRepository.save(any(Package.class))).thenReturn(pkg);
//
//        // Act
//        PackageDTO result = packageService.updatePackage(1L, pkgRequest);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("New Name", result.name());
//        verify(packageRepository, times(1)).save(any(Package.class));
//    }

    @Test
    public void testDeletePackage() {
        // Act
        packageService.deletePackage(1L);

        // Assert
        verify(packageRepository, times(1)).deleteById(1L);
    }
}
