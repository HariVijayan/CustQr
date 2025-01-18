package Prod.CustQr2024.generateQr.serviceClass;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

@Component
public class ReplaceColor {
	public void replaceColor(BufferedImage image, Color targetColor, Color replacementColor) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y) == targetColor.getRGB()) {
					image.setRGB(x, y, replacementColor.getRGB());
				}
			}
		}
	}
}
