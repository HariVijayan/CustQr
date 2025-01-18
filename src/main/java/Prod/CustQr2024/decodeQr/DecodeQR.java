package Prod.CustQr2024.decodeQr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import Prod.CustQr2024.decodeQr.serviceClass.BWTransform;

@Component
public class DecodeQR {

	private final BWTransform bwtransform;

	@Autowired
	public DecodeQR(BWTransform bwtransform) {
		this.bwtransform = bwtransform;
	}

	public String decodeQRCode(String base64Image) throws IOException {
		byte[] imageBytes = Base64.getDecoder().decode(base64Image);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
		BufferedImage image = ImageIO.read(bis);

		if (image == null) {
			return "Invalid image";
		}

		try {
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			hints.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

			Result result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (NotFoundException e) {
			BufferedImage bwImage = bwtransform.convertToHighContrastBW(image);
			try {
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bwImage)));
				Map<DecodeHintType, Object> hints = new HashMap<>();
				hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
				hints.put(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

				Result result = new MultiFormatReader().decode(bitmap, hints);
				return result.getText();
			} catch (NotFoundException ex) {
				return "QR code not found in the image";
			}
		} catch (Exception e) {
			return "Error decoding QR code: " + e.getMessage();
		}
	}
}
