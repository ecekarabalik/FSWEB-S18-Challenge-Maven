package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.exceptions.CardNotFoundException;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager entityManager;

    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findByColor(String color) {
        try {
            TypedQuery<Card> query = entityManager.createQuery(
                    "SELECT c FROM Card c WHERE c.color = :color", Card.class);
            query.setParameter("color", Color.valueOf(color.toUpperCase()));
            return query.getResultList();
        } catch (Exception e) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery(
                "SELECT c FROM Card c WHERE c.value = :value", Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        try {
            TypedQuery<Card> query = entityManager.createQuery(
                    "SELECT c FROM Card c WHERE c.type = :type", Card.class);
            query.setParameter("type", Type.valueOf(type.toUpperCase()));
            return query.getResultList();
        } catch (Exception e) {
            throw new CardException("Card not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    public Card remove(Long id) {
        Card card = entityManager.find(Card.class, id);

        if (card == null) {
            throw new CardNotFoundException("Id " + id + " ile card bulunamadı");
        }

        entityManager.remove(card);
        return card; // ✔️ ZORUNLU
    }
}
