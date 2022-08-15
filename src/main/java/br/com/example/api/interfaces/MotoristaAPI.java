package br.com.example.api.interfaces;

import br.com.example.api.domain.Motorista;
import br.com.example.api.domain.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
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
    public List<Motorista> listarMotorista(){
        return motoristaRepository.findAll();
    }

    @GetMapping("/motoristas/{id}")
    public Motorista buscarPorId(@PathVariable("id") Long id){
        return motoristaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/motoristas")
    public Motorista criarMotorista(@RequestBody Motorista motorista){
        return motoristaRepository.save(motorista);
    }

    @PutMapping("motoristas/{id}")
    public Motorista atualizarMotorista(@PathVariable("id") Long id, @RequestBody Motorista motorista){
        Motorista motoristaSalvo = buscarPorId(id);
        motoristaSalvo.setNome(motorista.getNome());
        motoristaSalvo.setDataDeNascimento(motorista.getDataDeNascimento());

        return motoristaRepository.save(motoristaSalvo);
    }

    @PatchMapping("motoristas/{id}")
    public Motorista atualizarMotoristaIncremental(@PathVariable("id") Long id, @RequestBody Motorista motorista){
        Motorista motoristaSalvo = buscarPorId(id);
        motoristaSalvo.setDataDeNascimento(Optional.ofNullable(motorista.getDataDeNascimento()).orElse(motoristaSalvo.getDataDeNascimento()));
        motoristaSalvo.setNome(Optional.ofNullable(motorista.getNome()).orElse(motoristaSalvo.getNome()));

        return motoristaRepository.save(motoristaSalvo);
    }

    @DeleteMapping("motoristas/{id}")
    public void excluirMotorista(@PathVariable("id") Long id){
        motoristaRepository.delete(buscarPorId(id));
    }
}
