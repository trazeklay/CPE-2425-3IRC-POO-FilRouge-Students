package model.cards;

import java.util.*;

import allShared.ICardsCollection;


/**
 * Objet qui contient l'ensemble des cartes de la main d'un joueur
 * 
 * Il est capable d'exécuter les traitements communs à toutes 
 * les collections de cartes (ajouter, supprimer, mélanger, trier, etc.)
 * et en plus révéler, cacher, jouer une carte
 * 
 * @author francoise.perrin
 */
public class Hand extends AbstractCardsCollection {

	public Hand() {
		super();
	}

	public Hand(Collection<Card> collection) {
		super(collection);
	}

	public Hand(ICardsCollection iCardsCollection) {
		super(iCardsCollection);
	}

	public final Card playCard(int index) {
		Card card = removeCard(index);
		if (card != null) {
			card.reveal();
		}
		return card;
	}

	public final boolean revealCard(int index) {
		if (index < 0 || index >= cards.size()) return false;
		cards.get(index).reveal();
		return true;
	}

	public final boolean hideCard(int index) {
		if (index < 0 || index >= cards.size()) return false;
		cards.get(index).hide();
		return true;
	}
}