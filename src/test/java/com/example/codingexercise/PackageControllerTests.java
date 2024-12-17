package com.example.codingexercise;

import com.example.codingexercise.dto.PackageRequest;
import com.example.codingexercise.dto.PackageDTO;
import com.example.codingexercise.service.PackageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PackageControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PackageService packageService;

	@Test
	void testCreatePackage() throws Exception {
		when(packageService.createPackage(any(PackageRequest.class)))
				.thenReturn(new PackageDTO(1L, "Test Package", "Test Description", null));

		String packageJson = """
				{
					"name": "Test Package",
					"description": "Test Description",
					"productIds": ["VqKb4tyj9V6i"]
				}
				""";

		mockMvc.perform(post("/api/packages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(packageJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Test Package"))
				.andExpect(jsonPath("$.description").value("Test Description"));
	}

	@Test
	void testGetPackage() throws Exception {
		when(packageService.getPackage(eq(1L), eq("USD")))
				.thenReturn(new PackageDTO(1L, "Test Package", "Test Description", null));

		mockMvc.perform(get("/api/packages/1")
				.param("currency", "USD"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Test Package"))
				.andExpect(jsonPath("$.description").value("Test Description"));
	}

	@Test
	void testUpdatePackage() throws Exception {
		PackageDTO updatedPackage = new PackageDTO(1L, "Updated Package", "Updated Description", null);
		when(packageService.updatePackage(eq(1L), any(PackageRequest.class)))
				.thenReturn(updatedPackage);

		String packageJson = """
				{
					"name": "Updated Package",
					"description": "Updated Description",
					"productIds": ["VqKb4tyj9V6i"]
				}
				""";

		mockMvc.perform(put("/api/packages/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(packageJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Updated Package"))
				.andExpect(jsonPath("$.description").value("Updated Description"));
	}

	@Test
	void testDeletePackage() throws Exception {
		mockMvc.perform(delete("/api/packages/1"))
				.andExpect(status().isNoContent());
	}

	@Test
	void testListPackages() throws Exception {
		when(packageService.listPackages(eq("USD")))
				.thenReturn(Collections.singletonList(new PackageDTO(1L, "Test Package", "Test Description", null)));

		mockMvc.perform(get("/api/packages")
				.param("currency", "USD"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Test Package"))
				.andExpect(jsonPath("$[0].description").value("Test Description"));
	}
}
