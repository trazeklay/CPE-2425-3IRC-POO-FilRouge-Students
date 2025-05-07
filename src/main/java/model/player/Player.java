package model.player;

import java.util.Iterator;

import allShared.CardsCollectionType;
import allShared.IPlayer;
import controller.CardsCollectionFactory;
import model.cards.Card;
import model.cards.Hand;

/**
 * Classe qui définit le comportement des Joueurs
 * 
 * Discussion Carnet de Bord : pertinence isStillActive() + isHandEmpty() ?
 * 
 * @author francoise.perrin
 */

public class Player implements IPlayer{
	private final String name;
	private final Hand hand;
	private final Hand trickPile;
	private boolean isTrickWinner;
	private boolean isGameWinner;
	
	public Player(String name) {
		this.name = name;
		this.hand = (Hand) CardsCollectionFactory.getCardsCollection(CardsCollectionType.HAND);
		this.trickPile = (Hand) CardsCollectionFactory.getCardsCollection(CardsCollectionType.HAND);
		this.isTrickWinner = false;
		this.isGameWinner = false;
	}
	
	public final void addCardToHand(Card pc) {
		hand.addCard(pc);
	}
	
	public final void addCardToTrickPile(Card pc) {
		trickPile.addCard(pc);
	}
	
	public final Card playCard(int index) {
		return hand.playCard(index);
	}
	
	public final Card removeCardFromHand(int index) {
		return hand.removeCard(index);
	}
	
	public final Card removeCardFromTrickPile(int index) {
		return trickPile.removeCard(index);
	}
	
	public final boolean revealCard(int index) {
		return hand.revealCard(index);
	}
	
	public final boolean hideCard(int index) {
		return hand.hideCard(index);
	}

	public final void switchTrickPileToHand() {
		Card card;
		
		while ((card = trickPile.removeTopCard()) != null) {
			hand.addCard(card);
		}
	}
	
	@Override
	public final String getName() {
		return this.name;
	}
	
	public final boolean isHandEmpty() {
		return hand.isEmpty();
	}

	public final int handSize() {
		return hand.size();
	}
	
	public final boolean isTrickPileEmpty() {
		return trickPile.isEmpty();
	}

	public final int trickPileSize() {
		return trickPile.size();
	}
	
	/**
	 * return true si la main du joueur n'est pas vide
	 */
	@Override
	public final boolean isStillActive() {
		return !hand.isEmpty();
	}

	/**
	 * retourne true si le joueur possède toutes les cartes 
	 * du deck initial 
	 */
	public final boolean hasWonAllCards(int deckSize) {
		return (hand.size() + trickPile.size()) >= deckSize;
	}
	
	@Override
	public final boolean isTrickWinner() {
		return isTrickWinner;
	}

	public final void setTrickWinner(boolean isTrickWinner) {
		this.isTrickWinner = isTrickWinner;
	}

	public final boolean isGameWinner() {
		return isGameWinner;
	}

	public final void setGameWinner(boolean isGameWinner) {
		this.isGameWinner = isGameWinner;
	}

	@Override
	public String toString() {
		return "["+ name + " ** Hand" + hand + " ** trickPile" + trickPile + "]";
	}

	/*
	La comparaison s'effectue
	selon l'ordre alphabétique des noms de joueurs
	 */
	@Override
	public int compareTo(IPlayer arg0) {
		return this.name.compareTo(arg0.getName());
	}

	/**
	 * Toutes les cartes gagnées retournent dans la main du joueur
	 * après avoir été mélangées
	 * * Ecrivez et testez cette méthode de 2 manières :
	 *  1 - en utilisant l'itérator de manière implicite
	 *  2 - en utilisant l'Iterator de manière explicite  
	 */
	public void addWonCardsBackToHand() {
		Iterator<Card> it = trickPile.iterator();
		while (it.hasNext()) {
			hand.addCard(it.next());
		}
		trickPile.clear();
	}
	
}
