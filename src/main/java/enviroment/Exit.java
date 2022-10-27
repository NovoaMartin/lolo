package enviroment;

public class Exit extends Enviroment {

	public Exit(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
//    private static final Image closed = new Image("file:src/main/resources/door.png");
//    private static final Image open = new Image("file:src/main/resources/door_open.png");
//
//    ImageView image;
//
//    private int keyCount = 0;
//
//    private boolean isOpen = false;
//
//    public Exit(Celda pos) {
//        super(pos);
//        image = new ImageView();
//        image.setImage(closed);
//        image.setTranslateY(pos.y * 50);
//        image.setTranslateX(pos.x * 50);
//        image.setFitHeight(50);
//        image.setFitWidth(50);
//    }
//
//    @Override
//    public void interactWith(Character character, int direccion, Mapa mapa) {
//        if (character.canWin() && character.hasWinCondition() && isOpen) {
//            character.setPos(pos);
//            character.win();
//        }
//    }
//
//    public void increaseKeyCount(){
//        keyCount++;
//        if (keyCount == 1) {
//            image.setImage(open);
//            isOpen = true;
//        }
//    }
//
//    @Override
//    public Node getRender() {
//        return image;
//    }
}
