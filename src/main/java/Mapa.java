import java.util.ArrayList;

public class Mapa {
    private int width;
    private int height;

    private ArrayList<Character> characters;
    private Item[][] items;
    private Enviroment[][] enviroments;

    public void tryMove(Character character, int direccion) {
        Celda target = character.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height || characters.stream().anyMatch(c -> c.getPos().equals(target) && c != character)) {
            return;
        }
        if (items[target.x][target.y] != null) {
            items[target.x][target.y].interactWith(character, direccion, this);
            if (!items[target.x][target.y].isValid()) {
                items[target.x][target.y] = null;
            }
        } else if (enviroments[target.x][target.y] != null) {
            enviroments[target.x][target.y].interactWith(character, direccion);
        } else {
            character.setPos(target);
        }
    }

    public boolean tryMove(Enviroment enviroment, int direccion) {
        Celda target = enviroment.getPos().translate(direccion);
        if (target.x < 0 || target.x >= width || target.y < 0 || target.y >= height || characters.stream().anyMatch(c -> c.getPos().equals(target))) {
            return false;
        }
        if (items[target.x][target.y] == null && enviroments[target.x][target.y] == null) {
            enviroment.setPos(target);
            return true;
        }
        return false;
    }

}
