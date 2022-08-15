package br.com.example.api.interfaces;

import br.com.example.api.domain.Passageiro;
import br.com.example.api.domain.PassageiroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping( path = "/passageiros", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassageiroAPI {

    private PassageiroRepository passageiroRepository;

    @GetMapping
    public List<Passageiro> listarTodos(){
        return passageiroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Passageiro buscarPorId(@PathVariable("id") Long id){
        return passageiroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Passageiro criarPassageiro(@RequestBody Passageiro passageiro){
        return passageiroRepository.save(passageiro);
    }

    @PutMapping("/{id}")
    public Passageiro atualizarPassageiro(@PathVariable("id") Long id, @RequestBody Passageiro passageiro){
        Passageiro passageiroSalvo = buscarPorId(id);
        passageiroSalvo.setNome(passageiro.getNome());
        return passageiroRepository.save(passageiroSalvo);
    }

    @PatchMapping("/{id}")
    public Passageiro atualizarPassageiroEscolhendoOsAtributos(@PathVariable("id") Long id, @RequestBody Passageiro passageiro){
        Passageiro passageiroSalvo = buscarPorId(id);
        passageiroSalvo.setNome(Optional.ofNullable(passageiro.getNome()).orElse(passageiroSalvo.getNome()) );
        return passageiroRepository.save(passageiroSalvo);
    }

    @DeleteMapping("/{id}")
    public void excluirPassageiro(@PathVariable("id") Long id){
        passageiroRepository.delete(buscarPorId(id));
    }
}


















