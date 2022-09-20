package br.com.example.api.outcoming;

import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.domain.PedidoViagemRepository;
import br.com.example.api.domain.PedidoViagemStatus;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GMapsService {

    @Autowired
    private PedidoViagemRepository pedidoViagemRepository;

    @Value("${app.transporte.domain.googlemaps.apiKey}")
    private String appKey;

    private static final String GMAPS_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}";
    //String chave = "AIzaSyArmrjqeXSuZqkQqxU_DKh__OsyLnVvnRc";

    public Integer buscarDistanciaEntreEnderecos(String origem, String destino){

        RestTemplate template = new RestTemplate();
        String jsonResult =  template.getForObject(GMAPS_TEMPLATE, String.class, origem, destino, appKey);

        JSONArray array = JsonPath.parse(jsonResult).read("$..legs[*].duration.value");

        List<Integer> results = array.stream().map(it -> (Integer) it).collect(Collectors.toList());

        return results.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }
}
