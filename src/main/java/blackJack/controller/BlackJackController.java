package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(Deck.create(), getParticipants());

        defaultRound(blackJackGame);
        additionalRound(blackJackGame);

        OutputView.printGameResult(blackJackGame.getParticipants());
        OutputView.printWinOrLoseResult(blackJackGame.getDealer(),
                BlackJackGameResult.ofGameResult(blackJackGame.getDealer(), blackJackGame.getPlayers()));
    }

    private void defaultRound(BlackJackGame blackJackGame) {
        blackJackGame.defaultDistributeCards();
        OutputView.printInitCardResult(blackJackGame.getParticipants());
    }

    private void additionalRound(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            additionalPlayerTurn(blackJackGame, player);
        }
        additionalDealerTurn(blackJackGame);
    }

    private void additionalPlayerTurn(BlackJackGame blackjackGame, Player player) {
        while (blackjackGame.isAvailableDistributeCard(player) && blackjackGame.isApproveDrawCard(getYesOrNo(player))) {
            blackjackGame.distributeCard(player);
            OutputView.printNowHoldCardInfo(player);
        }
    }

    private void additionalDealerTurn(BlackJackGame blackjackGame) {
        while (blackjackGame.isAvailableDistributeCard(blackjackGame.getDealer())) {
            blackjackGame.distributeCard(blackjackGame.getDealer());
        }
        OutputView.printDealerReceiveCardCount(blackjackGame.getDealer());
    }

    private Participants getParticipants() {
        try {
            return Participants.fromNames(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            return getParticipants();
        }
    }

    private YesOrNo getYesOrNo(Player player) {
        try {
            return YesOrNo.find(InputView.inputOneMoreCard(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getYesOrNo(player);
        }
    }
}
