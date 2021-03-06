package blackjack.domain;

import blackjack.domain.card.CardDeck;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Users {
    private final int INITIAL_DRAW_CARD_NUMBER = 2;

    private final List<User> users = new ArrayList<>();

    public Users(Dealer dealer, List<String> players) {
        this.users.add(dealer);
        this.users.addAll(players.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public void initialHit(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            gerUsers()
                    .forEach(user -> user.hit(cardDeck.drawCard()));
        }
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    public List<User> gerUsers() {
        return Collections.unmodifiableList(this.users);
    }

    public User getDealer() {
        return this.users.stream()
                .filter(User::isDealer)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("딜러가 존재하지 않습니다."));
    }

    public Map<User, Result> checkResult(int dealerScore) {
        return users.stream()
                .filter(User::isPlayer)
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> ((Player) player).getResult(dealerScore),
                        (key1, key2) -> key1,
                        LinkedHashMap::new
                ));
    }
}