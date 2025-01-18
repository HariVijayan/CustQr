package Prod.CustQr2024.generateQr;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

@Component
public class GenerateQR {

	private Map<EncodeHintType, Object> initializeHintMap() {
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
		return hints;
	}

	private String convertImageToBase64(BufferedImage image, String fileType) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, fileType, baos);
		byte[] imageData = baos.toByteArray();
		return Base64.getEncoder().encodeToString(imageData);
	}

	private void drawLogoText(Graphics2D graphics, int qrMatrixSize, int marginPercentage, int margin,
			Color qrPatternColor, String logoText) {

		int logoTextSize;
		int topMarginLogoText;
		if (marginPercentage == 5) {
			logoTextSize = calculateProportionalValue(35, qrMatrixSize, 800);
			topMarginLogoText = 0;
		} else {
			logoTextSize = calculateProportionalValue(50, qrMatrixSize, 800); // Find logoTextSize value for current
																				// size(qrMatrixSize) with values
																				// similar
																				// to [logoTextSize = 50 for
																				// qrMatrixSize
																				// = 800]
			topMarginLogoText = calculateProportionalValue(10, qrMatrixSize, 800); // Find topMargin for logoText with
																					// values similar to [topMargin = 10
																					// for qrMatrixSize = 800]
		}

		graphics.setColor(qrPatternColor);
		Font textFont = new Font("Noto", Font.PLAIN, logoTextSize);
		graphics.setFont(textFont);

		int logoTextBottomPosition = qrMatrixSize + margin + topMarginLogoText + logoTextSize;

		int logoTextLeftPosition = margin;

		graphics.drawString(logoText, logoTextLeftPosition, logoTextBottomPosition);
	}

	private void drawLogoImage(Graphics2D graphics, int totalQrSize, int qrMatrixSize, int margin, String logoPosition,
			String logoShape, String logoOrientation, BufferedImage Logo) throws IOException {
		int dragValue = calculateProportionalValue(2, qrMatrixSize, 800);// Sometimes the logo doesn't align perfectly
																			// with the qrMatrix, to ensure the logo
																			// covers
																			// the entire bottom - right corner,
																			// dragValue
																			// is added to drag the image into a bigger
																			// size.
		int logoHeight;
		int logoWidth;
		int logoHorizontalPosition;
		int logoVerticalPosition;

		if ("Rectangle".equalsIgnoreCase(logoShape)) {
			if ("Portrait".equalsIgnoreCase(logoOrientation)) {
				logoWidth = calculateProportionalValue(200, qrMatrixSize, 800);
				logoHeight = calculateProportionalValue(400, qrMatrixSize, 800);
			} else {
				logoWidth = calculateProportionalValue(400, qrMatrixSize, 800);
				logoHeight = calculateProportionalValue(200, qrMatrixSize, 800);
			}
		} else {
			logoWidth = calculateProportionalValue(200, qrMatrixSize, 800);
			logoHeight = calculateProportionalValue(200, qrMatrixSize, 800);
		}

		if ("Center".equalsIgnoreCase(logoPosition)) {
			logoHorizontalPosition = (totalQrSize / 2) - (logoWidth / 2);
			logoVerticalPosition = (totalQrSize / 2) - (logoHeight / 2);
		} else {
			logoHorizontalPosition = qrMatrixSize + margin - logoWidth + dragValue;
			logoVerticalPosition = qrMatrixSize + margin - logoHeight + dragValue;
		}

		graphics.drawImage(Logo, logoHorizontalPosition, logoVerticalPosition, logoWidth, logoHeight, null);
	}

	public String generateQRCode(String qrCodeInput, String qrCodeBg, int[] integerColorValues, Color[] colorValues,
			int totalQrsize, String logoname, String logoPosition, String logoShape, String logoOrientation,
			String lgtxt, BufferedImage Logo, int marginPercentage, BufferedImage QrPattern,
			BufferedImage OuterPositionalMarkerTopLeft, BufferedImage InnerPositionalMarkerTopLeft,
			BufferedImage OuterPositionalMarkerTopRight, BufferedImage InnerPositionalMarkerTopRight,
			BufferedImage OuterPositionalMarkerBottomLeft, BufferedImage InnerPositionalMarkerBottomLeft)
			throws WriterException, IOException, customException {

		int margin = (totalQrsize * marginPercentage) / 100;

		int qrMatrixSize = totalQrsize - 2 * margin;

		Map<EncodeHintType, Object> hintMap = initializeHintMap();

		QRCode qrCode = Encoder.encode(qrCodeInput, ErrorCorrectionLevel.H, hintMap);
		ByteMatrix byteMatrix = qrCode.getMatrix();

		int matrixWidth = byteMatrix.getWidth();

		int matrixHeight = byteMatrix.getHeight();

		float moduleSize = (float) qrMatrixSize / matrixWidth;

		int horizontalMargin = margin; 

		int verticalMargin = margin;

		BufferedImage image = new BufferedImage(totalQrsize, totalQrsize, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = image.createGraphics();
		
		if (!"NA".equalsIgnoreCase(qrCodeBg)) {
			graphics.setColor(colorValues[1]);
			graphics.fillRect(0, 0, totalQrsize, totalQrsize);
		} else {
			graphics.setComposite(AlphaComposite.Clear);
			graphics.fillRect(0, 0, totalQrsize, totalQrsize);
			graphics.setComposite(AlphaComposite.Src);
		}

		graphics.setColor(colorValues[0]);

		BufferedImage scaledQrPatternImage = new BufferedImage((int) Math.ceil(moduleSize), (int) Math.ceil(moduleSize),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphicsQrBody = scaledQrPatternImage.createGraphics();
		graphicsQrBody.drawImage(QrPattern.getScaledInstance((int) Math.ceil(moduleSize), (int) Math.ceil(moduleSize),
				Image.SCALE_SMOOTH), 0, 0, null);
		graphicsQrBody.dispose();

		for (int y = 0; y < matrixHeight; y++) {
			for (int x = 0; x < matrixWidth; x++) {
				if (isPositionDetectionPattern(x, y, matrixWidth)) {
					continue;
				}

				if (byteMatrix.get(x, y) == 1) {
					int marginXOffset = Math.round(x * moduleSize);
					int marginYOffset = Math.round(y * moduleSize);
					graphics.drawImage(scaledQrPatternImage, marginXOffset + horizontalMargin,
							marginYOffset + verticalMargin, null);
				}
			}
		}

		drawLogoText(graphics, qrMatrixSize, marginPercentage, margin, colorValues[0], lgtxt);

		if (!("NA").equalsIgnoreCase(logoname)) {
			drawLogoImage(graphics, totalQrsize, qrMatrixSize, margin, logoPosition, logoShape, logoOrientation, Logo);

		}
		drawPositionDetectionPatterns(graphics, OuterPositionalMarkerTopLeft, InnerPositionalMarkerTopLeft,
				OuterPositionalMarkerTopRight, InnerPositionalMarkerTopRight, OuterPositionalMarkerBottomLeft,
				InnerPositionalMarkerBottomLeft, horizontalMargin, verticalMargin, qrMatrixSize, moduleSize);

		graphics.dispose();
		return convertImageToBase64(image, "png");
	}

	private static void drawPositionDetectionPatterns(Graphics2D graphics, BufferedImage outerPositionalMarkerTL,
			BufferedImage innerPositionalMarkerTL, BufferedImage outerPositionalMarkerTR,
			BufferedImage innerPositionalMarkerTR, BufferedImage outerPositionalMarkerBL,
			BufferedImage innerPositionalMarkerBL, int horizontalMargin, int verticalMargin, int qrMatrixSize,
			float moduleSize) throws IOException {
		float patternSize = 7 * moduleSize;
		drawSinglePositionDetectionPattern(graphics, outerPositionalMarkerTL, innerPositionalMarkerTL, horizontalMargin,
				verticalMargin, moduleSize);

		drawSinglePositionDetectionPattern(graphics, outerPositionalMarkerTR, innerPositionalMarkerTR,
				horizontalMargin + qrMatrixSize - patternSize - (moduleSize / 25), verticalMargin, moduleSize);

		drawSinglePositionDetectionPattern(graphics, outerPositionalMarkerBL, innerPositionalMarkerBL, horizontalMargin,
				verticalMargin + qrMatrixSize - patternSize - (moduleSize / 25), moduleSize);
	}

	private static void drawSinglePositionDetectionPattern(Graphics2D graphics, BufferedImage outerPositionalMarker,
			BufferedImage innerPositionalMarker, float offsetX, float offsetY, float moduleSize) throws IOException {
		float patternSize = 7 * moduleSize;

		graphics.drawImage(
				outerPositionalMarker.getScaledInstance((int) patternSize, (int) patternSize, Image.SCALE_SMOOTH),
				Math.round(offsetX), Math.round(offsetY), null);

		float innerPositionalMarkerOffsetX = offsetX + 2 * moduleSize;
		float innerPositionalMarkerOffsetY = offsetY + 2 * moduleSize;
		float innerPositionalMarkerSize = 3 * moduleSize;

		graphics.drawImage(
				innerPositionalMarker.getScaledInstance((int) innerPositionalMarkerSize,
						(int) innerPositionalMarkerSize, Image.SCALE_SMOOTH),
				Math.round(innerPositionalMarkerOffsetX), Math.round(innerPositionalMarkerOffsetY), null);
	}

	private static boolean isPositionDetectionPattern(int x, int y, int matrixWidth) {
		int offset = matrixWidth - 7;
		return (x < 7 && y < 7) || (x >= offset && y < 7) || (x < 7 && y >= offset);
	}

	private int calculateProportionalValue(double initialValue, int newSize, int referenceSize) {
		return (int) ((initialValue * newSize) / referenceSize);
	}

}

class customException extends Exception {
	private static final long serialVersionUID = 1L;

	public customException(String message) {
		super(message);
	}
}
