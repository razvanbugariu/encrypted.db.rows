package com.bug.encrypted.data.controller;

import com.bug.encrypted.data.entity.Card;
import com.bug.encrypted.data.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public List<Card> getAll() {
        return cardService.getAll();
    }

    @PostMapping
    public Card save(@RequestBody Card card) {
        return cardService.save(card);
    }
}
