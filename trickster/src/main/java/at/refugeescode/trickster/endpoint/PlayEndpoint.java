package at.refugeescode.trickster.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/play")
public class PlayEndpoint {

    private RestTemplate restTemplate;
    @Value("#{'${cup.ports}'.split(',')}")
    private List<String> port;

    public PlayEndpoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<String> getPort() {
        List<String> url = port.stream()
                .map(e -> "localhost:" + e + "/coin")
                .peek(e -> System.out.println(e))
                .collect(Collectors.toList());


        url.stream().forEach(e -> System.out.println(e));
        url.stream().forEach(e -> getPost(e));

        return url;
    }

    private String getPost(String e) {
        System.out.println("before rest");
        System.out.println(e);
        restTemplate.postForEntity("localhost:9003/coin", true, Boolean.class);
        System.out.println("after rest");
        return e;
    }


}
