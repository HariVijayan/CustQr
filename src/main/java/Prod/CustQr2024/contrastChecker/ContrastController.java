package Prod.CustQr2024.contrastChecker;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Prod.CustQr2024.contrastChecker.serviceClass.ContrastCheck;
import Prod.CustQr2024.inputHandler.serviceClass.ColorValueHandling;

@RestController
public class ContrastController {

	private final ColorValueHandling colorValueHandling;
	private final ContrastCheck contrastCheck;

	@Autowired
	public ContrastController(ColorValueHandling colorValueHandling, ContrastCheck contrastCheck) {
		this.colorValueHandling = colorValueHandling;
		this.contrastCheck = contrastCheck;
	}

	@PostMapping("/CheckContrast")
	public ModelAndView checkContrast(@RequestParam String qrclrValue, @RequestParam String qrbgValue,
			@RequestParam String posmarkercolor, @RequestParam String innerposmarkercolor,
			@RequestParam String posmarkerTRcolor, @RequestParam String innerposmarkerTRcolor,
			@RequestParam String posmarkerBLcolor, @RequestParam String innerposmarkerBLcolor) {
		int[] qrColorarr = colorValueHandling.handleQRCodeColor(qrclrValue);

		int[] qrBackgroundarr = colorValueHandling.handleQRCodeBackground(qrbgValue);

		int[] posMarkerTLarr = colorValueHandling.handleQRCodePosMarkerColor(posmarkercolor);

		int[] posMarkerTRarr = colorValueHandling.handleQRCodePosMarkerColor(posmarkerTRcolor);

		int[] posMarkerBLarr = colorValueHandling.handleQRCodePosMarkerColor(posmarkerBLcolor);

		int[] innerPosMarkerTLarr = colorValueHandling.handleQRCodeInnerPosMarkerColor(innerposmarkercolor);

		int[] innerPosMarkerTRarr = colorValueHandling.handleQRCodeInnerPosMarkerColor(innerposmarkerTRcolor);

		int[] innerPosMarkerBLarr = colorValueHandling.handleQRCodeInnerPosMarkerColor(innerposmarkerBLcolor);

		Color qrClr = new Color(qrColorarr[0], qrColorarr[1], qrColorarr[2]);
		Color qrBg = new Color(qrBackgroundarr[0], qrBackgroundarr[1], qrBackgroundarr[2]);
		Color posMarkerTL = new Color(posMarkerTLarr[0], posMarkerTLarr[1], posMarkerTLarr[2]);
		Color posMarkerTR = new Color(posMarkerTRarr[0], posMarkerTRarr[1], posMarkerTRarr[2]);
		Color posMarkerBL = new Color(posMarkerBLarr[0], posMarkerBLarr[1], posMarkerBLarr[2]);

		Color innerposMarkerTL = new Color(innerPosMarkerTLarr[0], innerPosMarkerTLarr[1], innerPosMarkerTLarr[2]);
		Color innerposMarkerTR = new Color(innerPosMarkerTRarr[0], innerPosMarkerTRarr[1], innerPosMarkerTRarr[2]);
		Color innerposMarkerBL = new Color(innerPosMarkerBLarr[0], innerPosMarkerBLarr[1], innerPosMarkerBLarr[2]);

		ModelAndView modelAndView = new ModelAndView();
		String[] contrastValues = { "Good", "Good", "Good", "Good", "Good", "Good", "Good" };

		if (!contrastCheck.hasSufficientContrast(qrClr, qrBg)) {
			modelAndView.addObject("contrastQrClr",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastQrClrID", "bt-conqrclrnotok");
			modelAndView.addObject("contrastQrClrButtonType", "disabled");
			contrastValues[0] = "Poor";
		} else {
			modelAndView.addObject("contrastQrClr",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastQrClrID", "bt-conqrclrok");
			modelAndView.addObject("contrastQrClrButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(posMarkerTL, qrBg)) {
			modelAndView.addObject("contrastPosTL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastPosTLID", "bt-conpostlnotok");
			modelAndView.addObject("contrastPosTLButtonType", "disabled");
			contrastValues[1] = "Poor";
		} else {
			modelAndView.addObject("contrastPosTL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastPosTLID", "bt-conpostlok");
			modelAndView.addObject("contrastPosTLButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(innerposMarkerTL, qrBg)) {
			modelAndView.addObject("contrastInnerPosTL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastInnerPosTLID", "bt-coninnerpostlnotok");
			modelAndView.addObject("contrastInnerPosTLButtonType", "disabled");
			contrastValues[2] = "Poor";
		} else {
			modelAndView.addObject("contrastInnerPosTL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastInnerPosTLID", "bt-coninnerpostlok");
			modelAndView.addObject("contrastInnerPosTLButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(posMarkerTR, qrBg)) {
			modelAndView.addObject("contrastPosTR",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastPosTRID", "bt-conpostrnotok");
			modelAndView.addObject("contrastPosTRButtonType", "disabled");
			contrastValues[3] = "Poor";
		} else {
			modelAndView.addObject("contrastPosTR",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastPosTRID", "bt-conpostrok");
			modelAndView.addObject("contrastPosTRButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(innerposMarkerTR, qrBg)) {
			modelAndView.addObject("contrastInnerPosTR",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastInnerPosTRID", "bt-coninnerpostrnotok");
			modelAndView.addObject("contrastInnerPosTRButtonType", "disabled");
			contrastValues[4] = "Poor";
		} else {
			modelAndView.addObject("contrastInnerPosTR",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastInnerPosTRID", "bt-coninnerpostrok");
			modelAndView.addObject("contrastInnerPosTRButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(posMarkerBL, qrBg)) {
			modelAndView.addObject("contrastPosBL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastPosBLID", "bt-conposblnotok");
			modelAndView.addObject("contrastPosBLButtonType", "disabled");
			contrastValues[5] = "Poor";
		} else {
			modelAndView.addObject("contrastPosBL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastPosBLID", "bt-conposblok");
			modelAndView.addObject("contrastPosBLButtonType", "selected");
		}

		if (!contrastCheck.hasSufficientContrast(innerposMarkerBL, qrBg)) {
			modelAndView.addObject("contrastInnerPosBL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
			modelAndView.addObject("contrastInnerPosBLID", "bt-coninnerposblnotok");
			modelAndView.addObject("contrastInnerPosBLButtonType", "disabled");
			contrastValues[6] = "Poor";
		} else {
			modelAndView.addObject("contrastInnerPosBL",
					"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
			modelAndView.addObject("contrastInnerPosBLID", "bt-coninnerposblok");
			modelAndView.addObject("contrastInnerPosBLButtonType", "selected");
		}
		modelAndView.addObject("contrastOverall", "Good");
		modelAndView.addObject("contrastOverallIcon",
				"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
		modelAndView.addObject("contrastOverallIconID", "bt-contotalok");
		modelAndView.addObject("contrastOverallButtonType", "selected");

		modelAndView.addObject("contrastPosTLOverallIcon",
				"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
		modelAndView.addObject("contrastPosTLOverallIconID", "bt-conpostltotalok");
		modelAndView.addObject("contrastPosTLOverallButtonType", "selected");

		modelAndView.addObject("contrastPosTROverallIcon",
				"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
		modelAndView.addObject("contrastPosTROverallIconID", "bt-conpostrtotalok");
		modelAndView.addObject("contrastPosTROverallButtonType", "selected");

		modelAndView.addObject("contrastPosBLOverallIcon",
				"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z\"/></svg>");
		modelAndView.addObject("contrastPosBLOverallIconID", "bt-conposbltotalok");
		modelAndView.addObject("contrastPosBLOverallButtonType", "selected");

		for (int i = 0; i < contrastValues.length; i++) {
			if (contrastValues[i].equalsIgnoreCase("Poor")) {
				modelAndView.addObject("contrastOverall", "Poor");
				modelAndView.addObject("contrastOverallIcon",
						"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
				modelAndView.addObject("contrastOverallIconID", "bt-contotalnotok");
				modelAndView.addObject("contrastOverallButtonType", "disabled");
				if ((i == 1) || (i == 2)) {
					modelAndView.addObject("contrastPosTLOverallIcon",
							"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
					modelAndView.addObject("contrastPosTLOverallIconID", "bt-conpostltotalnotok");
					modelAndView.addObject("contrastPosTLOverallButtonType", "disabled");
				} else if ((i == 3) || (i == 4)) {
					modelAndView.addObject("contrastPosTROverallIcon",
							"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
					modelAndView.addObject("contrastPosTROverallIconID", "bt-conpostrtotalnotok");
					modelAndView.addObject("contrastPosTROverallButtonType", "disabled");
				} else if ((i == 5) || (i == 6)) {
					modelAndView.addObject("contrastPosBLOverallIcon",
							"<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 24 24\" fill=\"#000000\"><path d=\"M0 0h24v24H0z\" fill=\"none\"/><path d=\"M12 2C6.47 2 2 6.47 2 12s4.47 10 10 10 10-4.47 10-10S17.53 2 12 2zm5 13.59L15.59 17 12 13.41 8.41 17 7 15.59 10.59 12 7 8.41 8.41 7 12 10.59 15.59 7 17 8.41 13.41 12 17 15.59z\"/></svg>");
					modelAndView.addObject("contrastPosBLOverallIconID", "bt-conposbltotalnotok");
					modelAndView.addObject("contrastPosBLOverallButtonType", "disabled");
				}
			}
		}
		modelAndView.setViewName("fragments/createqr/contrastvalues :: contrastfragments");
		return modelAndView;
	}
}
