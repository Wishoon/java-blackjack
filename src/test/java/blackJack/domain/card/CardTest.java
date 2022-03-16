package blackJack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createValidCard() {
        assertThat(Card.valueOf(Suit.SPADE, Denomination.KING)).isNotNull();
    }

    @Test
    @DisplayName("카드 숫자에 따라 점수가 반환되는지 테스트")
    void checkScoreByDenomination() {
        Card card = Card.valueOf(Suit.SPADE, Denomination.KING);
        assertThat(card.getScore()).isEqualTo(10);
    }
}
