package com.example.codingexercise;

import com.example.codingexercise.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class PackageControllerTests {

	private final TestRestTemplate restTemplate;
    private final PackageRepository packageRepository;
    private final MockMvc mockMvc;

    @Autowired
    PackageControllerTests(TestRestTemplate restTemplate, PackageRepository packageRepository, MockMvc mockMvc) {
		this.restTemplate = restTemplate;
        this.packageRepository = packageRepository;
        this.mockMvc = mockMvc;
    }

//    @Test
//    void createPackage() {
//		ResponseEntity<SeeIfItsUnused> created = restTemplate.postForEntity("/packages", new PackageRequest(null, "Test Name", "Test Desc", List.of("prod1")), PackageRequest.class);
//        assertEquals(HttpStatus.OK, created.getStatusCode(), "Unexpected status code");
//        SeeIfItsUnused createdBody = created.getBody();
//        assertNotNull(createdBody, "Unexpected body");
//        assertEquals("Test Name", createdBody.getName(), "Unexpected name");
//        assertEquals("Test Desc", createdBody.getDescription(), "Unexpected description");
//        assertEquals(List.of("prod1"), createdBody.getProductIds(), "Unexpected products");
//
//        SeeIfItsUnused productPackage = packageRepository.get(createdBody.getId());
//        assertNotNull(productPackage, "Unexpected package");
//        assertEquals(createdBody.getId(), productPackage.getId(), "Unexpected id");
//        assertEquals(createdBody.getName(), productPackage.getName(), "Unexpected name");
//        assertEquals(createdBody.getDescription(), productPackage.getDescription(), "Unexpected description");
//        assertEquals(createdBody.getProductIds(), productPackage.getProductIds(), "Unexpected products");
//    }
//
//    @Test
//    void getPackage() {
//        SeeIfItsUnused productPackage = packageRepository.create("Test Name 2", "Test Desc 2", List.of("prod2"));
//        ResponseEntity<SeeIfItsUnused> fetched = restTemplate.getForEntity("/packages/{id}", SeeIfItsUnused.class, productPackage.getId());
//        assertEquals(HttpStatus.OK, fetched.getStatusCode(), "Unexpected status code");
//        SeeIfItsUnused fetchedBody = fetched.getBody();
//        assertNotNull(fetchedBody, "Unexpected body");
//        assertEquals(productPackage.getId(), fetchedBody.getId(), "Unexpected id");
//        assertEquals(productPackage.getName(), fetchedBody.getName(), "Unexpected name");
//        assertEquals(productPackage.getDescription(), fetchedBody.getDescription(), "Unexpected description");
//        assertEquals(productPackage.getProductIds(), fetchedBody.getProductIds(), "Unexpected products");
//    }
//
//    @Test
//    void listPackages() {
//        SeeIfItsUnused productPackage1 = packageRepository.create("Test Name 1", "Test Desc 1", List.of("prod1"));
//        SeeIfItsUnused productPackage2 = packageRepository.create("Test Name 2", "Test Desc 2", List.of("prod2"));
//
//        ResponseEntity<Object> fetched = restTemplate.getForEntity("/packages", Object.class);
//        assertEquals(HttpStatus.OK, fetched.getStatusCode(), "Unexpected status code");
//    }
//
//    @Test
//    public void testCreatePackage() throws Exception {
//        // Create a mock package JSON
//        String packageJson = "{ \"name\": \"Test Package\", \"description\": \"A test package\", \"products\": [] }";
//
//        mockMvc.perform(post("/api/packages")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(packageJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Test Package"));
//    }

}
