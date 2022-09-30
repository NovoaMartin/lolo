package lolo;

import java.util.ArrayList;
import java.util.List;

import Utils.Celda;
import character.Character;
import character.Jugador;
import enviroment.Enviroment;
import items.Cofre;
import items.Item;

public class Mapa {
	private int width;
	private int height;
	private int cantCorazones;
	private boolean puertaAbierta;
	
	private List<Character> characters;
	private Item[][] items;
	private Enviroment[][] enviroments;
	private Cofre cofre;
	private Celda puerta;
	
	public Mapa(int w, int h, Cofre cofre, Celda puerta) {

		this.width = w;
		this.height = h;
		this.puerta = puerta;
		this.cofre = cofre;

		characters = new ArrayList<Character>(2);
		items = new Item[w][h];
		enviroments = new Enviroment[w][h];
		cantCorazones = 0;
		puertaAbierta = false;

	}

	public void addCharacter(Character c) {
		characters.add(c);
	}

	public void addItem(Item i) {
		if(i.getClass().getName().contains("Corazon")){
			cantCorazones++;
		}
		items[i.getPos().x][i.getPos().y] = i;
	}

	public void addEnviroment(Enviroment e) {
		enviroments[e.getPos().x][e.getPos().y] = e;
	}
	
	public void removeElementPos(Celda pos, String type) {
		if(type.contains("item")) {
			if(type.contains("Corazon") && --cantCorazones == 0) {
				cofre.abrirCofre();
			}
			items[pos.x][pos.y] = null;
		} else {
			enviroments[pos.x][pos.y] = null;
		}
	}
	
	public void tryMove(Character character, int direccion) {
		Celda target = character.getPos().translate(direccion);
		if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height
				/*|| characters.stream().anyMatch(c -> c.getPos().equals(target) && c != character)*/) {
			return;
		}
		if (items[target.x][target.y] != null) {
			items[target.x][target.y].interactWith(character, direccion, this);
		} else if (enviroments[target.x][target.y] != null) {
			enviroments[target.x][target.y].interactWith(character, direccion, this);
		} else if(cofre.getPos().equals(target) && cofreAbierto()){
			cofre.interactWith(character, direccion, this);
		} else if(puerta.equals(target) && puertaAbierta) {
			((Jugador)character).ganar();
			character.setPos(target);
		} else {
			character.setPos(target);
		}
	}

	public boolean tryMove(Enviroment enviroment, int direccion) {
		Celda target = enviroment.getPos().translate(direccion);
		if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height
				/*|| characters.stream().anyMatch(c -> c.getPos().equals(target))*/) {
			return false;
		}
		if (items[target.x][target.y] == null && enviroments[target.x][target.y] == null) {
			enviroments[enviroment.getPos().x][enviroment.getPos().y] = null;
			enviroment.setPos(target);
			enviroments[enviroment.getPos().x][enviroment.getPos().y] = enviroment;
			return true;
		}
		return false;
	}
	

	public int corazonesRestantes() {
		return cantCorazones;
	}
	
	public void abrirPuerta() {
		puertaAbierta = true;
	}
	
	public boolean puertaAbierta() {
		return puertaAbierta;
	}
	
	public boolean cofreAbierto() {
		return cofre.cofreAbierto();
	}
}
