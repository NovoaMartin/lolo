package utils;

import java.io.File;
import java.util.Scanner;

import lolo.Mapa;

public class FileMap {

	public static Mapa loadMap(String fileMap) {
		Scanner sc = null;
		Mapa mapa = null;
		try {
			
			sc = new Scanner(new File("mapas/" + fileMap));
			mapa = new Mapa(new Celda[sc.nextInt()][sc.nextInt()]);
			sc.nextLine();
			int line = 0;
			while (sc.hasNext()) {
				String str = sc.nextLine();
				for (int i = 0; i < str.length(); i++) {
					char ch = str.charAt(i);
					mapa.addElement(ch, line, i);
				}
				line++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		
		return mapa;
	}
	
}
