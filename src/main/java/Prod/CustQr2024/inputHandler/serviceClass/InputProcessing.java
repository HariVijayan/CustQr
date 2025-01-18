package Prod.CustQr2024.inputHandler.serviceClass;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.WriterException;

import Prod.CustQr2024.generateQr.GenerateQR;
import Prod.CustQr2024.generateQr.serviceClass.ImageLoader;
import Prod.CustQr2024.generateQr.serviceClass.ReplaceColor;

@Component
public class InputProcessing {

	private final GenerateQR generateQR;
	private final ImageLoader imageLoader;
	private final ReplaceColor replaceColor;
	private final HandleColorInput handlecolorinput;
	private final HandleSizeInput handlesizeinput;
	private final HandleLogoInput handlelogoinput;
	private final HandlePositionalMarkerInput handlepositionalmarkerinput;

	@Autowired
	public InputProcessing(ImageLoader imageLoader, ReplaceColor replaceColor, GenerateQR generateQR,
			HandleColorInput handlecolorinput, HandleSizeInput handlesizeinput, HandleLogoInput handlelogoinput,
			HandlePositionalMarkerInput handlepositionalmarkerinput) {
		this.generateQR = generateQR;
		this.handlecolorinput = handlecolorinput;
		this.handlesizeinput = handlesizeinput;
		this.handlelogoinput = handlelogoinput;
		this.handlepositionalmarkerinput = handlepositionalmarkerinput;
		this.imageLoader = imageLoader;
		this.replaceColor = replaceColor;
	}

	public ModelAndView initializeInputForConversion(String qrInput, String qrSizeValue, String qrMarginValue,
			String qrBodyColor, String qrBackgroundColor, String outerPositionalMarkerTLColor,
			String innerPositionalMarkerTLColor, String outerPositionalMarkerTRColor,
			String innerPositionalMarkerTRColor, String outerPositionalMarkerBLColor,
			String innerPositionalMarkerBLColor, String qrLogoOption, String logoPosition, String logoShape,
			String logoOrientation, MultipartFile customLogoFile, String logoText, String qrPatternType,
			String outerPositionalMarkerType, String innerPositionalMarkerType) {

		String customLogoAsString, qrPatternPath, outerPositionalMarkerPath, innerPositionalMarkerPath;
		String logoDetails[];
		int[] integerColorValues;
		Color[] colorValues;
		int qrSize, qrMargin;
		BufferedImage Logo = null, QrPattern, OuterPositionalMarkerTopLeft, InnerPositionalMarkerTopLeft,
				OuterPositionalMarkerTopRight, InnerPositionalMarkerTopRight, OuterPositionalMarkerBottomLeft,
				InnerPositionalMarkerBottomLeft;

		try {
			qrSize = handlesizeinput.parseQrCodeSize(qrSizeValue);
			qrMargin = handlesizeinput.parseQrCodeMargin(qrMarginValue);
		} catch (Exception e) {
			return qrSizeInputError();
		}

		try {
			integerColorValues = handlecolorinput.getRGBValuesForAllColors(qrBodyColor, qrBackgroundColor,
					outerPositionalMarkerTLColor, innerPositionalMarkerTLColor, outerPositionalMarkerTRColor,
					innerPositionalMarkerTRColor, outerPositionalMarkerBLColor, innerPositionalMarkerBLColor);

			colorValues = handlecolorinput.getColorValuesForAllColors(integerColorValues);
		} catch (Exception e) {
			return qrColorInputError();
		}

		try {
			if (qrLogoOption.equalsIgnoreCase("Custom")) {
				customLogoAsString = Base64.getEncoder().encodeToString(customLogoFile.getBytes());
				byte[] imageBytes = Base64.getDecoder().decode(customLogoAsString);
				ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
				Logo = ImageIO.read(bis);
				logoDetails = handlelogoinput.getLogoDetails(qrLogoOption, logoText);
				logoText = logoDetails[1];
			} else {
				if (!qrLogoOption.equalsIgnoreCase("NA")) {
					logoDetails = handlelogoinput.getLogoDetails(qrLogoOption, logoText);
					Logo = imageLoader.loadImage(logoDetails[0]);
					logoText = logoDetails[1];
					if ("NA".equalsIgnoreCase(qrBackgroundColor)) {
						replaceColor.replaceColor(Logo, new Color(255, 255, 255),
								new Color(integerColorValues[3], integerColorValues[4], integerColorValues[5], 0));
					} else {
						replaceColor.replaceColor(Logo, new Color(255, 255, 255),
								new Color(integerColorValues[3], integerColorValues[4], integerColorValues[5], 255));
					}
					replaceColor.replaceColor(Logo, new Color(0, 0, 0),
							new Color(integerColorValues[0], integerColorValues[1], integerColorValues[2], 255));
				} else {
					logoText = "";
				}
			}
		} catch (Exception e) {
			return qrLogoInputError(e.getMessage());
		}

		try {
			qrPatternPath = handlepositionalmarkerinput.getQrPatternPath(qrPatternType);
			outerPositionalMarkerPath = handlepositionalmarkerinput
					.getOuterPositionalMarkerPath(outerPositionalMarkerType);
			innerPositionalMarkerPath = handlepositionalmarkerinput
					.getInnerPositionalMarkerPath(innerPositionalMarkerType);

			QrPattern = handlepositionalmarkerinput.getColorReplacedImage(qrPatternPath, colorValues[0]);

			OuterPositionalMarkerTopLeft = handlepositionalmarkerinput.getColorReplacedImage(outerPositionalMarkerPath,
					colorValues[2]);
			InnerPositionalMarkerTopLeft = handlepositionalmarkerinput.getColorReplacedImage(innerPositionalMarkerPath,
					colorValues[3]);

			OuterPositionalMarkerTopRight = handlepositionalmarkerinput.getColorReplacedImage(outerPositionalMarkerPath,
					colorValues[4]);
			InnerPositionalMarkerTopRight = handlepositionalmarkerinput.getColorReplacedImage(innerPositionalMarkerPath,
					colorValues[5]);

			OuterPositionalMarkerBottomLeft = handlepositionalmarkerinput
					.getColorReplacedImage(outerPositionalMarkerPath, colorValues[6]);
			InnerPositionalMarkerBottomLeft = handlepositionalmarkerinput
					.getColorReplacedImage(innerPositionalMarkerPath, colorValues[7]);

		} catch (Exception e) {
			return qrCustomShapeError();
		}
		return tryQrConversion(qrInput, qrBackgroundColor, integerColorValues, colorValues, qrSize, qrLogoOption,
				logoPosition, logoShape, logoOrientation, logoText, Logo, qrMargin, QrPattern,
				OuterPositionalMarkerTopLeft, InnerPositionalMarkerTopLeft, OuterPositionalMarkerTopRight,
				InnerPositionalMarkerTopRight, OuterPositionalMarkerBottomLeft, InnerPositionalMarkerBottomLeft);

	}

	public ModelAndView tryQrConversion(String qrCodeInput, String qrCodeBg, int[] integerColorValues,
			Color[] colorValues, int qrCodeSize, String logoname, String logoPosition, String logoShape,
			String logoOrientation, String lgtxt, BufferedImage Logo, int qrmargin, BufferedImage QrPattern,
			BufferedImage OuterPositionalMarkerTopLeft, BufferedImage InnerPositionalMarkerTopLeft,
			BufferedImage OuterPositionalMarkerTopRight, BufferedImage InnerPositionalMarkerTopRight,
			BufferedImage OuterPositionalMarkerBottomLeft, BufferedImage InnerPositionalMarkerBottomLeft) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			String base64Image = "data:image/png;base64," + generateQR.generateQRCode(qrCodeInput, qrCodeBg,
					integerColorValues, colorValues, qrCodeSize, logoname, logoPosition, logoShape, logoOrientation,
					lgtxt, Logo, qrmargin, QrPattern, OuterPositionalMarkerTopLeft, InnerPositionalMarkerTopLeft,
					OuterPositionalMarkerTopRight, InnerPositionalMarkerTopRight, OuterPositionalMarkerBottomLeft,
					InnerPositionalMarkerBottomLeft);
			modelAndView.addObject("qrCodeImage", base64Image);
			return qrConversionSuccess(modelAndView);
		} catch (WriterException e) {
			if (e.getMessage().contains("Data too big")) {
				return qrDataBigException();
			}
			return qrConversionFailure();
		} catch (Exception e) {
			return qrConversionFailure();
		}
	}

	public ModelAndView qrConversionSuccess(ModelAndView modelAndView) {
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionSuccess");
		return modelAndView;
	}

	public ModelAndView qrConversionFailure() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("failureMessage", "Conversion Failed : Error Generating QR");
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}

	public ModelAndView qrColorInputError() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("failureMessage", "Conversion Failed : Error processing Color Inputs");
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}

	public ModelAndView qrSizeInputError() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("failureMessage", "Conversion Failed : Error processing Size Inputs");
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}

	public ModelAndView qrLogoInputError(String errorInfo) {
		ModelAndView modelAndView = new ModelAndView();
		if (errorInfo.equalsIgnoreCase(
				"Cannot invoke \"org.springframework.web.multipart.MultipartFile.getBytes()\" because \"customLogoFile\" is null")) {
			modelAndView.addObject("failureMessage", "Conversion Failed : Custom Logo isn't uploaded");
		} else {
			modelAndView.addObject("failureMessage", "Conversion Failed : Error processing Logo Input");
		}
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}

	public ModelAndView qrCustomShapeError() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("failureMessage", "Conversion Failed : Error processing Custom Shapes");
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}

	public ModelAndView qrDataBigException() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("failureMessage", "Conversion Failed : Input Data Too Big");
		modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
		return modelAndView;
	}
}
