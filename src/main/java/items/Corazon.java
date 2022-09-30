//package items;
//
//import Utils.Celda;
//import character.Character;
//import lolo.Mapa;
//
//public class Corazon extends Item{
//
//	public Corazon(Celda pos) {
//		super(pos);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void interactWith(Character character, int direccion, Mapa mapa) {
//		if(character.getClass().getName() == "character.Jugador") {
//			mapa.removeElementPos(this.getPos(), this.getClass().getName());
//			character.tryMove(direccion);
//		}
//
//	}
//
//}
