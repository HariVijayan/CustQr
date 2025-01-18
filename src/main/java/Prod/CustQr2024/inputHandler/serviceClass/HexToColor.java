package Prod.CustQr2024.inputHandler.serviceClass;

import org.springframework.stereotype.Component;

@Component
public class HexToColor {

	public int[] HexToColorConversion(String hex) {
		int r = 0, g = 0, b = 0;

		hex = hex.replace("#", "");
		if (!hex.isEmpty()) {
			r = Integer.valueOf(hex.substring(0, 2), 16);
			g = Integer.valueOf(hex.substring(2, 4), 16);
			b = Integer.valueOf(hex.substring(4, 6), 16);
		}
		return new int[] { r, g, b };
	}

}
