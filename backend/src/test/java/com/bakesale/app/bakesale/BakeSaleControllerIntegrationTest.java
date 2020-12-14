package com.bakesale.app.bakesale;

import com.bakesale.app.BaseIntegrationTest;
import com.bakesale.app.bakesale.model.BakeSaleDTO;
import com.bakesale.app.item.model.ItemDTO;
import com.bakesale.app.item.model.ItemType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BakeSaleControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void whenBakeSaleDoesNotExist_thenReturn404() throws Exception {
        mockMvc.perform(get(String.format("/v1/bake-sales/%s", UUID.randomUUID())))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateBakeSaleAndGet() throws Exception {
        BakeSaleDTO bakeSaleDTO = new BakeSaleDTO();
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Brownie");
        itemDTO.setPrice(new BigDecimal("0.65"));
        itemDTO.setAvailable(48);
        itemDTO.setImageURL("https://comidinhasdochef.com/?attachment_id=23940");
        itemDTO.setType(ItemType.CLOTHING);
        bakeSaleDTO.setItems(Set.of(itemDTO));

        String response =
                mockMvc.perform(
                        post("/v1/bake-sales")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(bakeSaleDTO)))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        bakeSaleDTO =
                mapper.readValue(response, BakeSaleDTO.class);
        assertNotNull(bakeSaleDTO.getId());

        response = mockMvc.perform(get(String.format("/v1/bake-sales/%s", bakeSaleDTO.getId())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        bakeSaleDTO =
                mapper.readValue(response, BakeSaleDTO.class);
        assertEquals(1, bakeSaleDTO.getItems().size());
    }
}
