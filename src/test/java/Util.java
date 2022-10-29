import Utils.Pantalla;

public class Util {

    static boolean done = false;

    public static void initialize() throws InterruptedException {
        if (done) return;
        done = true;
        new Thread(Pantalla::startGame).start();
        Thread.sleep(500);
    }
}
