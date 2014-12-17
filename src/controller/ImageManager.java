/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageManager {
	public static ImageIcon deleteIMG = new ImageIcon(loadImg(new File("res/delete.png")).getScaledInstance(10, 10, 0));
	
	public ImageManager(){
	}
	
	public static Image loadImg(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
