package at.refugeescode.cup.endpoint;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
public class CoinEndpoint {

    private Boolean coin;

    @GetMapping
    Boolean getCoin() {
        return coin;
    }

    @PutMapping
    void putCoin() {
        coin = true;
    }

    @DeleteMapping
    void removeCoin() {
        coin = false;
    }
}
