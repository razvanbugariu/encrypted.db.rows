package com.bug.encrypted.data.service;

import com.bug.encrypted.data.entity.Card;
import com.bug.encrypted.data.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }
}
