package blackJack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import blackJack.domain.card.Card;

public abstract class Participant {

    private static final String ERROR_MESSAGE_BLANK_NAME = "플레이어의 이름이 존재하지 않습니다.";

    private final String name;
    private final List<Card> cards;

    public Participant(String name) {
        validateName(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_BLANK_NAME);
        }
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }
}