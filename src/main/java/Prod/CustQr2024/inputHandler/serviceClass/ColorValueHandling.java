package Prod.CustQr2024.inputHandler.serviceClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColorValueHandling {
	private final HexToColor hexToColor;

	@Autowired
	public ColorValueHandling(HexToColor hexToColor) {
		this.hexToColor = hexToColor;
	}

	public int[] handleQRCodePosMarkerColor(String qrCodePosMarkerClr) {
		if ("NA".equalsIgnoreCase(qrCodePosMarkerClr)) {
			return new int[] { 0, 0, 0 };
		} else {
			int[] qrCodePosMarkerColor = hexToColor.HexToColorConversion(qrCodePosMarkerClr);
			return new int[] { qrCodePosMarkerColor[0], qrCodePosMarkerColor[1], qrCodePosMarkerColor[2] };
		}
	}

	public int[] handleQRCodeInnerPosMarkerColor(String qrCodeInnerPosMarkerClr) {
		if ("NA".equalsIgnoreCase(qrCodeInnerPosMarkerClr)) {
			return new int[] { 0, 0, 0 };
		} else {
			int[] qrCodeInnerPosMarkerColor = hexToColor.HexToColorConversion(qrCodeInnerPosMarkerClr);
			return new int[] { qrCodeInnerPosMarkerColor[0], qrCodeInnerPosMarkerColor[1],
					qrCodeInnerPosMarkerColor[2] };
		}
	}

	public int[] handleQRCodeColor(String qrCodeClr) {
		if ("NA".equalsIgnoreCase(qrCodeClr)) {
			return new int[] { 0, 0, 0 };
		} else {
			int[] qrCodeColor = hexToColor.HexToColorConversion(qrCodeClr);
			return new int[] { qrCodeColor[0], qrCodeColor[1], qrCodeColor[2] };
		}
	}

	public int[] handleQRCodeBackground(String qrCodeBg) {
		if ("NA".equalsIgnoreCase(qrCodeBg)) {
			return new int[] { 255, 255, 255 };
		} else {
			int[] qrCodeBackground = hexToColor.HexToColorConversion(qrCodeBg);
			if ((qrCodeBackground[0] == 0) && (qrCodeBackground[1] == 0) && (qrCodeBackground[2] == 0)) {
				qrCodeBackground[0] = 1;
				qrCodeBackground[1] = 1;
				qrCodeBackground[2] = 1;
			}
			return new int[] { qrCodeBackground[0], qrCodeBackground[1], qrCodeBackground[2] };
		}
	}

}
