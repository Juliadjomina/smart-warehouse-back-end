package net.group.warehouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.group.warehouse.persistence.model.Product;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Validator validator;
    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void invalidProductNameShouldFailValidation() {
        Product product = new Product();
        product.setName("AaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaA");
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidSerialNumberShouldFailValidation() {
        Product product = new Product();
        product.setSerialNumber("AaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaA");
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidMaterialShouldFailValidation() {
        Product product = new Product();
        product.setMaterial("AaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaA");
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void noNameShouldFailValidation() {
        Product product = new Product();
        product.setName(null);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    void getAllProducts() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        List<Product> products = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(10, products.size());
    }

    @Test
    void findProductById() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        Product product = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(1, product.getId());

    }

    @Test
    void deleteProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete/10"))
                .andExpect(status().isOk());
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        List<Product> productModels = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(9, productModels.size());
    }
}
