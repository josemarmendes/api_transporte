package br.com.example.api.controller;

import br.com.example.api.domain.Passageiro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PassageiroAPITest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init(){

    }


    @Test
    public void deveCriarPassageiro() throws Exception {
        //cenário
        Passageiro passageiro = new Passageiro();
        passageiro.setNome("Joaquim Miguel dos Santos");

        String jsonPassageiro = mapper.writeValueAsString(passageiro);

        //execução
        mockMvc.perform(
                MockMvcRequestBuilders.post("/passageiros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPassageiro))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //verificação
        Assertions.assertEquals(passageiro.getNome(), "Joaquim Miguel dos Santos");
    }

}
