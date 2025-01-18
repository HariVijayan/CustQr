package Prod.CustQr2024.decodeQr.serviceClass;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

@Component
public class BWTransform {

	public BufferedImage convertToHighContrastBW(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = new Color(image.getRGB(x, y));
				int gray = (int) (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114);

				int threshold = 128; 
				int binary = gray < threshold ? 255 : 0; 
				Color bwColor = new Color(binary, binary, binary);

				bwImage.setRGB(x, y, bwColor.getRGB());
			}
		}
		return bwImage;
	}

}
