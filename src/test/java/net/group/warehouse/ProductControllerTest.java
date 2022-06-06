package net.group.warehouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.group.warehouse.persistence.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
