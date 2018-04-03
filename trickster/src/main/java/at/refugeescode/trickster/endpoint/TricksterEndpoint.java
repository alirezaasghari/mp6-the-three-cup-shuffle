package at.refugeescode.trickster.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController

public class TricksterEndpoint {

    private RestTemplate restTemplate;

    @Value("#{'${cup.ports}'.split(',')}")
    private List<String> ports;


    public TricksterEndpoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/play")
    void play() {
        removeCoinFromCups();
        putCoinInRandomCup();
    }


    private void removeCoinFromCups() {
        ports.stream()
                .map(port -> "http://localhost:" + port + "/coin")
                .forEach(url -> restTemplate.delete(url));
    }


    private void putCoinInRandomCup() {
        List<String> portNumbers = new ArrayList<>(ports);
        Collections.shuffle(portNumbers);
        String selectedPort = portNumbers.get(0);
        String url = "http://localhost:" + selectedPort + "/coin";
        restTemplate.put(url, null);
    }


    @GetMapping("/choose/{number}")
    Boolean choose(@PathVariable Integer number) {
        String port = ports.get(number - 1);
        String url = "http://localhost:" + port + "/coin";
        ResponseEntity<Boolean> answer = restTemplate.getForEntity(url, Boolean.class);
        return answer.getBody();
    }
}
