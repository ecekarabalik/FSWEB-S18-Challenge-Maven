package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin // React / frontend için basit CORS çözümü
public class CardController {

    private final CardRepositoryImpl cardRepository;

    // [GET] /workintech/cards
    @GetMapping
    public List<Card> getAll() {
        log.info("Tüm kartlar isteniyor");
        return cardRepository.findAll();
    }

    // [GET] /workintech/cards/byColor/{color}
    @GetMapping("/byColor/{color}")
    public List<Card> getByColor(@PathVariable String color) {
        log.info("Color ile kartlar isteniyor: {}", color);
        return cardRepository.findByColor(color);
    }

    // [GET] /workintech/cards/byValue/{value}
    @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable Integer value) {
        log.info("Value ile kartlar isteniyor: {}", value);
        return cardRepository.findByValue(value);
    }

    // [GET] /workintech/cards/byType/{type}
    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable String type) {
        log.info("Type ile kartlar isteniyor: {}", type);
        return cardRepository.findByType(type);
    }

    // [POST] /workintech/cards
    @PostMapping
    public Card create(@RequestBody Card card) {
        log.info("Yeni kart oluşturuluyor: {}", card);
        CardValidation.validate(card);
        return cardRepository.save(card);
    }

    // [PUT] /workintech/cards
    @PutMapping
    public Card update(@RequestBody Card card) {
        log.info("Kart güncelleniyor: {}", card);
        CardValidation.validate(card);
        return cardRepository.update(card);
    }

    // [DELETE] /workintech/cards/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Kart siliniyor, id: {}", id);
        cardRepository.remove(id);
    }
}
