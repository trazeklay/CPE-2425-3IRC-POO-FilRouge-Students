package model.player;

import allShared.IPlayer;
import model.cards.CardRender;

/**
 * Cette classe est un Proxy ou un Adapter [Design Pattern] de la classe Player
 * Ses instances sont utilisées par la View qui
 * n'a accès qu'aux "Getters" de l'objet encapsulé
 * 
 * Cette classe restreint donc le nombre de fonctionnalités existantes de l'objet enveloppé
 * et lui délègue le soin de réaliser les traitements
 * 
 * @author francoise.perrin
 *
 */
public class PlayerRender implements IPlayer, Comparable<IPlayer> {

	private final IPlayer player;
	
	public PlayerRender(IPlayer player) {
		this.player = player;
	}
	
	@Override
	public final String getName() {
		return player.getName();
	}
	
	@Override
	public final boolean isStillActive() {
		return player.isStillActive();
	}

	@Override
	public final boolean isTrickWinner() {
		return player.isTrickWinner();
	}
	

	@Override
	public boolean isGameWinner() {
		return player.isGameWinner();
	}

	@Override
	public String toString() {
		return player.getName();
	}

	@Override
	public int hashCode() {
		return player.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CardRender) {
			return player.equals((IPlayer) obj);
		}

		return false;
	}

	@Override
	public int compareTo(IPlayer o) {
		return player.compareTo(o);
	}


}
