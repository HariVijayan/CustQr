package Prod.CustQr2024.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Prod.CustQr2024.decodeQr.DecodeQR;

@Controller
class GetMappingURL {
	@GetMapping("/")
	public String mainPage() {
		return "createqr";
	}

	@GetMapping("/index.html")
	public String indexpage() {
		return "createqr";
	}

	@GetMapping("/index")
	public String index() {
		return "createqr";
	}

	@GetMapping("/createqr.html")
	public String createqr() {
		return "createqr";
	}

	@GetMapping("/createqr")
	public String createqrpage() {
		return "createqr";
	}

	@GetMapping("/about.html")
	public String about() {
		return "about";
	}

	@GetMapping("/about")
	public String aboutpage() {
		return "about";
	}

	@GetMapping("/faq.html")
	public String faq() {
		return "faq";
	}

	@GetMapping("/faq")
	public String faqpage() {
		return "faq";
	}

	@GetMapping("/decode.html")
	public String decode() {
		return "decode";
	}

	@GetMapping("/decode")
	public String decodepage() {
		return "decode";
	}

	@GetMapping("/inputFormat.html")
	public String input() {
		return "inputFormat";
	}

	@GetMapping("/inputFormat")
	public String inputpage() {
		return "inputFormat";
	}
}

@RestController
public class URLEndpoint {

	private final DecodeQR decodeQR;

	@Autowired
	public URLEndpoint(DecodeQR decodeQR) {
		this.decodeQR = decodeQR;
	}

	@PostMapping("/decodeQR")
	public ResponseEntity<String> decodeQRCode(@RequestParam("todecode") MultipartFile file) {
		try {
			String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
			String decodedText = decodeQR.decodeQRCode(base64Image);
			return ResponseEntity.ok(decodedText);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing the QR code.");
		}
	}

	@PostMapping("/initializeAllCreateQr")
	public String[] initializeEndpointsCreateQr(@RequestParam("viewingMode") String mode) {
		if (mode.equalsIgnoreCase("Dark")) {
			return new String[] { "/getBodyDarkMode", "/getPositionalMarker", "/CheckContrast", "/getInput" };
		}
		return new String[] { "/getBodyLightMode", "/getPositionalMarker", "/CheckContrast", "/getInput" };
	}

	@PostMapping("/getBodyLightMode")
	public ModelAndView getCreateQRBodyLightMode() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/createqrbody :: createQrBodyLightMode");
		return modelAndView;
	}

	@PostMapping("/getBodyDarkMode")
	public ModelAndView getCreateQRBodyDarkMode() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/createqrbody :: createQrBodyDarkMode");
		return modelAndView;
	}

	@PostMapping("/getBodyDecodeQr")
	public ModelAndView getDecodeQRBody() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/decodeqrbody :: decodeQrBody");
		return modelAndView;
	}

	@PostMapping("/getBody404Error")
	public ModelAndView get404ErrorBody() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/404ErrorBody :: errorBody");
		return modelAndView;
	}

	@PostMapping("/getBodyAbout")
	public ModelAndView getAboutBody() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/aboutBody :: aboutBody");
		return modelAndView;
	}

	@PostMapping("/getBodyFaq")
	public ModelAndView getFaqBody() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fragments/Body/faqBody :: faqBody");
		return modelAndView;
	}

	@PostMapping("/getPositionalMarker")
	public ModelAndView getPositionalMarker(@RequestParam("mode") String viewingMode) {
		ModelAndView modelAndView = new ModelAndView();
		if (viewingMode.equalsIgnoreCase("Dark")) {
			modelAndView.setViewName("fragments/createqr/positionalMarker :: positionalMarkerDivDarkMode");
			return modelAndView;
		} else {
			modelAndView.setViewName("fragments/createqr/positionalMarker :: positionalMarkerDivLightMode");
			return modelAndView;
		}
	}

	@PostMapping("/getInput")
	public ModelAndView getInputFields(@RequestParam("qrType") String qrType) {
		ModelAndView modelAndView = new ModelAndView();
		switch (qrType) {
		case "Text":
			modelAndView.setViewName("fragments/createqr/qrinputs :: textInput");
			break;
		case "URL":
			modelAndView.setViewName("fragments/createqr/qrinputs :: urlInput");
			break;
		case "Wi-Fi":
			modelAndView.setViewName("fragments/createqr/qrinputs :: wifiInput");
			break;
		case "Email":
			modelAndView.setViewName("fragments/createqr/qrinputs :: emailInput");
			break;
		case "SMS":
			modelAndView.setViewName("fragments/createqr/qrinputs :: smsInput");
			break;
		case "Playstore App":
			modelAndView.setViewName("fragments/createqr/qrinputs :: playstoreInput");
			break;
		case "Appstore App":
			modelAndView.setViewName("fragments/createqr/qrinputs :: appstoreInput");
			break;
		case "Location Info":
			modelAndView.setViewName("fragments/createqr/qrinputs :: locationInput");
			break;
		case "Personal Contact":
			modelAndView.setViewName("fragments/createqr/qrinputs :: mecardInput");
			break;
		case "Business Contact":
			modelAndView.setViewName("fragments/createqr/qrinputs :: vcardInput");
			break;
		case "Dial Phone Number":
			modelAndView.setViewName("fragments/createqr/qrinputs :: dialphnoInput");
			break;
		case "Calendar Event":
			modelAndView.setViewName("fragments/createqr/qrinputs :: eventInput");
			break;
		default:
			modelAndView.setViewName("fragments/createqr/qrinputs :: textInput");
			break;
		}
		return modelAndView;
	}
}