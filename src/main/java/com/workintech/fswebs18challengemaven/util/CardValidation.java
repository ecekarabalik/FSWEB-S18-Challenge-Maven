package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardValidationException;

public class CardValidation {

    public static void validate(Card card) {
        // Null kontrolü
        if (card == null) {
            throw new CardValidationException("Card null olamaz");
        }

        // JOKER ise hem value hem color null olmalı
        if (card.getType() == Type.JOKER) {
            if (card.getValue() != null || card.getColor() != null) {
                throw new CardValidationException("JOKER kartın value ve color değeri olmamalıdır");
            }
            return; // Diğer kurallara bakmaya gerek yok
        }

        // JOKER değilse:
        boolean hasValue = card.getValue() != null;
        boolean hasType = card.getType() != null;

        // Hem type hem value olamaz
        if (hasValue && hasType) {
            throw new CardValidationException("Bir kartın hem type hem value değeri olamaz");
        }

        // En az bir tanesi olmalı
        if (!hasValue && !hasType) {
            throw new CardValidationException("Bir kartın type veya value değerinden en az biri olmalı");
        }

        // Color JOKER olmayan kart için null olamaz (mantıksal tercih)
        if (card.getColor() == null) {
            throw new CardValidationException("JOKER olmayan kartlarda color boş olamaz");
        }
    }
}
