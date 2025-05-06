package model.games;

import java.util.Comparator;

import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import model.cards.Card;
import model.cards.NewWarGameCardComparator;


/**
 * Cet évaluateur s'appuie sur un comparateur de cartes (Comparator)
 * i.e. la méthode compare() définie dans classe NewWarGameCardComparator
 * qui compare les valeurs des cartes et en cas d'égalité, leur couleur
 * 
 * @author francoise.perrin
 */
public class NewWarGameEvaluator extends AbstractGameEvaluator implements IGameEvaluator {

	private final Comparator<Card> comparator = new NewWarGameCardComparator();

	@Override
	protected final Card max(ICardsCollection gamingMat) {
		if (gamingMat == null || gamingMat.isEmpty()) {
			return null;
		}

		Card maxCard = null;

		for (Card card : gamingMat) {
			if (!card.isRevealed()) card.reveal(); // sécurité

			if (maxCard == null || comparator.compare(card, maxCard) > 0) {
				maxCard = card;
			}
		}

		return maxCard;
	}

	@Override
	protected final int comparaison(Card card, Card maxCard) {
		if (card == null || maxCard == null) {
			return 0;
		}

		if (!card.isRevealed()) card.reveal();
		if (!maxCard.isRevealed()) maxCard.reveal();

		return comparator.compare(card, maxCard);
	}
}