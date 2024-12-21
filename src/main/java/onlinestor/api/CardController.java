package onlinestor.api;

import lombok.RequiredArgsConstructor;
import onlinestor.service.CardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;


}
