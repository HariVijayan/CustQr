package Prod.CustQr2024.contrastChecker.serviceClass;

import java.awt.Color;

import org.springframework.stereotype.Component;

@Component
public class ContrastCheck {

	public boolean hasSufficientContrast(Color backgroundColor, Color qrBodyColor) {
		double contrastRatio = getContrastRatio(backgroundColor, qrBodyColor);
		return contrastRatio >= 4.5;
	}

	private static double getContrastRatio(Color color1, Color color2) {
		double luminance1 = getRelativeLuminance(color1);
		double luminance2 = getRelativeLuminance(color2);

		if (luminance1 > luminance2) {
			return (luminance1 + 0.05) / (luminance2 + 0.05);
		} else {
			return (luminance2 + 0.05) / (luminance1 + 0.05);
		}
	}

	private static double getRelativeLuminance(Color color) {
		double[] rgb = new double[3];
		rgb[0] = color.getRed() / 255.0;
		rgb[1] = color.getGreen() / 255.0;
		rgb[2] = color.getBlue() / 255.0;

		for (int i = 0; i < 3; i++) {
			if (rgb[i] <= 0.03928) {
				rgb[i] = rgb[i] / 12.92;
			} else {
				rgb[i] = Math.pow((rgb[i] + 0.055) / 1.055, 2.4);
			}
		}
		return 0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2];
	}

}
