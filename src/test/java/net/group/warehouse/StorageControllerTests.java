package net.group.warehouse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.group.warehouse.persistence.model.Storage;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StorageControllerTests {
    @Autowired
    private Validator validator;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void invalidStorageNameShouldFailValidation() {
        Storage storage = new Storage();
        // name is more than 50 chars
        storage.setName("AaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaaA");
        storage.setCapacity(5);
        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void invalidStorageCapacityShouldFailValidation() {
        Storage storage = new Storage();
        storage.setCapacity(null);
        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void validStorageNameShouldNotFailValidation() {
        Storage storage = new Storage();
        // name is less than 50 chars
        storage.setName("AaaaaaaaaaAaaaaaaaaaAaaaaaaaaaAaaaaaaaaa");
        storage.setCapacity(5);
        Set<ConstraintViolation<Storage>> violations = validator.validate(storage);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void getAllStorages() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/storages"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        List<Storage> storages = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(10, storages.size());
    }

    @Test
    void findStoragesById() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/storages/1"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        Storage storage = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(1, storage.getId());

    }

    @Test
    void createAndSaveStorage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/storages")
                        .content("{\"name\":\"name24545\",\"capacity\":\"5\"}")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/storages"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        List<Storage> storageModels = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(11, storageModels.size());
    }


    @Test
    void deleteStorageById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/storages/delete/2"))
                .andExpect(status().isOk());
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/storages"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = res.getResponse().getContentAsString();
        List<Storage> storageModels = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        assertEquals(10, storageModels.size());
    }

}
