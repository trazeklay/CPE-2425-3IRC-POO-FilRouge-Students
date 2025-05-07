package model.games;

import java.util.List;

import allShared.ICardsCollection;
import allShared.IGame;
import allShared.IGameEvaluator;
import model.cards.Card;
import model.player.Player;

/**
 * Implémentation générique d'un jeu de Bataille.
 *
 * Règles communes :
 * - Distribution initiale : on donne chaque carte du deck, une à une,
 *   en alternance à chaque joueur.
 * - Chaque joueur joue la carte en tête de sa main (index 0).
 * - Fin de partie : lorsqu'un joueur possède toutes les cartes.
 *
 * Les variantes (Classic, NewWar) fournissent leur propre évaluateur
 * via getGameEvaluator().
 *
 * @author francoise.perrin
 */
public abstract class AbstractWarGame extends AbstractGame implements IGame {

	/**
	 * Constructeur : délègue à AbstractGame, qui appelle dealCardsFromDeck().
	 */
	public AbstractWarGame(List<String> playersNames, ICardsCollection deck) {
		super(playersNames, deck);
	}

	/**
	 * Chaque implémentation fournit son évaluateur de pli.
	 */
	protected abstract IGameEvaluator getGameEvaluator();

	/**
	 * Distribution des cartes : on retire chaque carte du deck et on l'attribue
	 * tour à tour à chaque joueur. Si le deck est vide, on s'arrête.
	 *
	 * @param nbCards nombre de cartes à distribuer (généralement la taille du deck)
	 */
	@Override
	protected final void dealCardsFromDeck(int nbCards) {
		for (int i = 0; i < nbCards * players.size(); i++) {
			if (deck.isEmpty()) break;

			Player player = players.get(i % players.size());
			Card card = deck.removeTopCard();

			if (card != null) player.addCardToHand(card);
		}
	}

	/**
	 * La partie se termine dès qu'un joueur possède toutes les cartes initiales.
	 * On marque alors ce joueur comme gagnant.
	 *
	 * @return true si un joueur a gagné la partie
	 */
	@Override
	public final boolean isGameEnd() {
		for (Player p : players) {
			if (p.hasWonAllCards(initDeckSize)) {
				p.setGameWinner(true);
				return true;
			}
		}
		return false;
	}
}
