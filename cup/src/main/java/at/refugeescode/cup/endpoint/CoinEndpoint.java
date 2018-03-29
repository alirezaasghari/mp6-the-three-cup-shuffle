package at.refugeescode.cup.endpoint;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
public class CoinEndpoint {

    Boolean coin = false;

    @GetMapping
    Boolean GetCoin() {
        return coin;
    }

    @PostMapping
    Boolean putCoin() {
        coin = true;
        return coin;
    }

    @DeleteMapping
    Boolean deleteCoin() {
        coin = false;
        return coin;
    }
}
