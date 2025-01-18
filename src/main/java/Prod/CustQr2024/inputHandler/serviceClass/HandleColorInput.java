package Prod.CustQr2024.inputHandler.serviceClass;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandleColorInput {

	private final ColorValueHandling colorValueHandling;

	@Autowired
	public HandleColorInput(ColorValueHandling colorValueHandling) {
		this.colorValueHandling = colorValueHandling;
	}

	public int[] getRGBValuesForAllColors(String qrCodeClr, String qrCodeBg, String qrposmarkertlcolor,
			String qrinnerposmarkertlcolor, String posmarkertrcolor, String innerposmarkertrcolor,
			String posmarkerblcolor, String innerposmarkerblcolor) {

		int[] qrColor = colorValueHandling.handleQRCodeColor(qrCodeClr);
		int colorRedValue = qrColor[0];
		int colorGreenValue = qrColor[1];
		int colorBlueValue = qrColor[2];

		int[] qrBackground = colorValueHandling.handleQRCodeBackground(qrCodeBg);
		int backgroundRedValue = qrBackground[0];
		int backgroundGreenValue = qrBackground[1];
		int backgroundBlueValue = qrBackground[2];

		int[] qrPositionalMarkerTL = colorValueHandling.handleQRCodePosMarkerColor(qrposmarkertlcolor);
		int positionalMarkerTLColorRedValue = qrPositionalMarkerTL[0];
		int positionalMarkerTLColorGreenValue = qrPositionalMarkerTL[1];
		int positionalMarkerTLColorBlueValue = qrPositionalMarkerTL[2];

		int[] qrinnerPositionalMarkerTL = colorValueHandling.handleQRCodeInnerPosMarkerColor(qrinnerposmarkertlcolor);
		int innerpositionalMarkerTLColorRedValue = qrinnerPositionalMarkerTL[0];
		int innerpositionalMarkerTLColorGreenValue = qrinnerPositionalMarkerTL[1];
		int innerpositionalMarkerTLColorBlueValue = qrinnerPositionalMarkerTL[2];

		int[] qrPositionalMarkerTR = colorValueHandling.handleQRCodePosMarkerColor(posmarkertrcolor);
		int positionalMarkerTRColorRedValue = qrPositionalMarkerTR[0];
		int positionalMarkerTRColorGreenValue = qrPositionalMarkerTR[1];
		int positionalMarkerTRColorBlueValue = qrPositionalMarkerTR[2];

		int[] qrinnerPositionalMarkerTR = colorValueHandling.handleQRCodeInnerPosMarkerColor(innerposmarkertrcolor);
		int innerpositionalMarkerTRColorRedValue = qrinnerPositionalMarkerTR[0];
		int innerpositionalMarkerTRColorGreenValue = qrinnerPositionalMarkerTR[1];
		int innerpositionalMarkerTRColorBlueValue = qrinnerPositionalMarkerTR[2];

		int[] qrPositionalMarkerBL = colorValueHandling.handleQRCodePosMarkerColor(posmarkerblcolor);
		int positionalMarkerBLColorRedValue = qrPositionalMarkerBL[0];
		int positionalMarkerBLColorGreenValue = qrPositionalMarkerBL[1];
		int positionalMarkerBLColorBlueValue = qrPositionalMarkerBL[2];

		int[] qrinnerPositionalMarkerBL = colorValueHandling.handleQRCodeInnerPosMarkerColor(innerposmarkerblcolor);
		int innerpositionalMarkerBLColorRedValue = qrinnerPositionalMarkerBL[0];
		int innerpositionalMarkerBLColorGreenValue = qrinnerPositionalMarkerBL[1];
		int innerpositionalMarkerBLColorBlueValue = qrinnerPositionalMarkerBL[2];

		int colorValues[] = { colorRedValue, colorGreenValue, colorBlueValue, backgroundRedValue, backgroundGreenValue,
				backgroundBlueValue, positionalMarkerTLColorRedValue, positionalMarkerTLColorGreenValue,
				positionalMarkerTLColorBlueValue, innerpositionalMarkerTLColorRedValue,
				innerpositionalMarkerTLColorGreenValue, innerpositionalMarkerTLColorBlueValue,
				positionalMarkerTRColorRedValue, positionalMarkerTRColorGreenValue, positionalMarkerTRColorBlueValue,
				innerpositionalMarkerTRColorRedValue, innerpositionalMarkerTRColorGreenValue,
				innerpositionalMarkerTRColorBlueValue, positionalMarkerBLColorRedValue,
				positionalMarkerBLColorGreenValue, positionalMarkerBLColorBlueValue,
				innerpositionalMarkerBLColorRedValue, innerpositionalMarkerBLColorGreenValue,
				innerpositionalMarkerBLColorBlueValue };

		return colorValues;

	}

	public Color[] getColorValuesForAllColors(int[] integerColorValues) {
		Color qrPatternColor = new Color(integerColorValues[0], integerColorValues[1], integerColorValues[2]);

		Color qrBackgroundColor = new Color(integerColorValues[3], integerColorValues[4], integerColorValues[5]);

		Color positionalMarkerTLColor = new Color(integerColorValues[6], integerColorValues[7], integerColorValues[8]);
		Color innerPositionalMarkerTLColor = new Color(integerColorValues[9], integerColorValues[10],
				integerColorValues[11]);

		Color positionalMarkerTRColor = new Color(integerColorValues[12], integerColorValues[13],
				integerColorValues[14]);
		Color innerPositionalMarkerTRColor = new Color(integerColorValues[15], integerColorValues[16],
				integerColorValues[17]);

		Color positionalMarkerBLColor = new Color(integerColorValues[18], integerColorValues[19],
				integerColorValues[20]);
		Color innerPositionalMarkerBLColor = new Color(integerColorValues[21], integerColorValues[22],
				integerColorValues[23]);

		Color[] colorValues = { qrPatternColor, qrBackgroundColor, positionalMarkerTLColor,
				innerPositionalMarkerTLColor, positionalMarkerTRColor, innerPositionalMarkerTRColor,
				positionalMarkerBLColor, innerPositionalMarkerBLColor };
		return colorValues;
	}
}
