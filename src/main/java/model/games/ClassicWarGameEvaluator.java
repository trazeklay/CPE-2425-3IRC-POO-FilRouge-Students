package model.games;

import allShared.ICardsCollection;
import allShared.IGameEvaluator;
import model.cards.Card;

import java.util.ArrayList;
import java.util.List;


/**
 * Cet évaluateur s'appuie sur la comparaison "naturelle" des cartes
 * i.e. la méthode compareTo() définie dans classe Card qui compare
 * les valeurs des cartes
 * 
 * @author francoise.perrin
 */
public class ClassicWarGameEvaluator extends AbstractGameEvaluator implements IGameEvaluator {

	@Override
	protected final Card max(ICardsCollection gamingMat) {
		List<Card> cards = new ArrayList<>();
		for (Card c : gamingMat) {
			cards.add(c);
		}

		int n = cards.size();
		if (n == 0) return null;
		if (n == 1) return cards.get(0);

		Card secondLast = cards.get(n - 2);
		Card last       = cards.get(n - 1);

		return (last.compareTo(secondLast) >= 0) ? last : secondLast;
	}


	@Override
	protected final int comparaison(Card card, Card maxCard) {
		return card.compareTo(maxCard);
	}
}
