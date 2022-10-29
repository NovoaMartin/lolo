package utils;

import character.Player;

public interface Interactable {

	void interactWith(Player p);
	boolean canInteract();
}
