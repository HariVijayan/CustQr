package Prod.CustQr2024.inputHandler.serviceClass;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Prod.CustQr2024.generateQr.serviceClass.ImageLoader;
import Prod.CustQr2024.generateQr.serviceClass.ReplaceColor;

@Component
public class HandleLogoInput {

	private final ImageLoader imageLoader;
	private final ReplaceColor replaceColor;

	@Autowired
	public HandleLogoInput(ImageLoader imageLoader, ReplaceColor replaceColor) {
		this.imageLoader = imageLoader;
		this.replaceColor = replaceColor;
	}

	public String[] getLogoDetails(String logoname, String lgtxt) {
		String logoPath = "";
		String logotext = "";

		switch (logoname) {
		case "Facebook":
			logoPath = "QR Logos/Facebook.png";
			logotext = "Let's be friends on";
			break;
		case "Twitter":
			logoPath = "QR Logos/Twitter.png";
			logotext = "Follow me on";
			break;
		case "Instagram":
			logoPath = "QR Logos/Instagram.png";
			logotext = "Catch me on";
			break;
		case "LinkedIn":
			logoPath = "QR Logos/LinkedIn.png";
			logotext = "Connect with me on";
			break;
		default:
			logotext = "Made using Cust QR";
			break;
		}

		if (!"NA".equalsIgnoreCase(lgtxt)) {
			logotext = lgtxt;
		}

		return new String[] { logoPath, logotext };
	}

	public BufferedImage getColorReplacedImage(String imagePath, Color replacingColor) throws IOException {
		BufferedImage image = imageLoader.loadImage(imagePath);
		replaceColor.replaceColor(image, new Color(0, 0, 0, 255), replacingColor);
		return image;
	}

}
