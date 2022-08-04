package br.com.example.api.interfaces;

import br.com.example.api.domain.Motorista;
import br.com.example.api.domain.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MotoristaAPI {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @GetMapping("/motoristas")
    public List<Motorista> listaMotorista(){
        return motoristaRepository.findAll();
    }

    @GetMapping("/motoristas/{id}")
    public Motorista buscaPorId(@PathVariable("id") Long id){
        return motoristaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
