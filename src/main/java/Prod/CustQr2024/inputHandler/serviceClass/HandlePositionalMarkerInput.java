package Prod.CustQr2024.inputHandler.serviceClass;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Prod.CustQr2024.generateQr.serviceClass.ImageLoader;
import Prod.CustQr2024.generateQr.serviceClass.ReplaceColor;

@Component
public class HandlePositionalMarkerInput {

	private final ImageLoader imageLoader;
	private final ReplaceColor replaceColor;

	@Autowired
	public HandlePositionalMarkerInput(ImageLoader imageLoader, ReplaceColor replaceColor) {
		this.imageLoader = imageLoader;
		this.replaceColor = replaceColor;
	}

	public String getQrPatternPath(String imageType) {

		String qrBodyPath;

		if (!"Regular QR Pattern".equalsIgnoreCase(imageType)) {
			qrBodyPath = "Body Pattern/Light Mode/" + imageType + ".png";
		} else {
			qrBodyPath = "Body Pattern/Light Mode/Box.png";
		}
		return qrBodyPath;
	}

	public String getOuterPositionalMarkerPath(String imageType) {

		String outerPositionalMarkerPath;

		if (!"Regular QR Pattern".equalsIgnoreCase(imageType)) {
			outerPositionalMarkerPath = "Positional Marker/Outer Marker/Light Mode/" + imageType + ".png";
		} else {
			outerPositionalMarkerPath = "Positional Marker/Outer Marker/Light Mode/Square.png";
		}
		return outerPositionalMarkerPath;
	}

	public String getInnerPositionalMarkerPath(String imageType) {

		String innerPositionalMarkerPath;

		if (!"Regular QR Pattern".equalsIgnoreCase(imageType)) {
			innerPositionalMarkerPath = "Positional Marker/Inner Marker/Light Mode/" + imageType + ".png";
		} else {
			innerPositionalMarkerPath = "Positional Marker/Inner Marker/Light Mode/Box.png";
		}
		return innerPositionalMarkerPath;
	}

	public BufferedImage getColorReplacedImage(String imagePath, Color replacingColor) throws IOException {
		BufferedImage image = imageLoader.loadImage(imagePath);
		replaceColor.replaceColor(image, new Color(0, 0, 0, 255), replacingColor);
		return image;
	}
}
