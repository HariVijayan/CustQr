package Prod.CustQr2024.inputHandler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Prod.CustQr2024.inputHandler.serviceClass.HandleQRTextInput;
import Prod.CustQr2024.inputHandler.serviceClass.InputProcessing;

@RestController
public class InputController {
	private final HandleQRTextInput handleqrtextinput;
	private final InputProcessing inputprocessing;

	@Autowired
	public InputController(HandleQRTextInput handleqrtextinput, InputProcessing inputprocessing) {
		this.handleqrtextinput = handleqrtextinput;
		this.inputprocessing = inputprocessing;
	}

	@PostMapping("/ConvertToQRTextInput")
	public ModelAndView generateTextQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String qrtext) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatTextInput(qrtext);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);

	}

	@PostMapping("/ConvertToQRURLInput")
	public ModelAndView generateURLQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String url) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatURLInput(url);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRWi-FiInput")
	public ModelAndView generateWiFiQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String wifiname, @RequestParam String wifienc,
			@RequestParam String wifipass) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatWifiInput(wifiname, wifienc, wifipass);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQREmailInput")
	public ModelAndView generateEmailQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String emailid, @RequestParam String emailsub,
			@RequestParam String emailbody) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatEmailInput(emailid, emailsub, emailbody);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRSMSInput")
	public ModelAndView generateSMSQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String smsphno, @RequestParam String smsmessage)
			throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatSMSInput(smsphno, smsmessage);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRPlaystore AppInput")
	public ModelAndView generatePlaystoreAppQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String playstoreid) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatPlaystoreAppInput(playstoreid);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRAppstore AppInput")
	public ModelAndView generateAppstoreAppQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String appname, @RequestParam String appid)
			throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatAppstoreAppInput(appname, appid);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRLocation InfoInput")
	public ModelAndView generateLocationInfoQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String latitude, @RequestParam String longitude)
			throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatLocationInfoInput(latitude, longitude);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRPersonal ContactInput")
	public ModelAndView generatePersonalContactQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String mcfname, @RequestParam String mclname,
			@RequestParam String mcnickname, @RequestParam String mcbday, @RequestParam String mcphno,
			@RequestParam String mcemail, @RequestParam String mcaddr, @RequestParam String mcurl,
			@RequestParam String mcnotes) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatMeCardInput(mcfname, mclname, mcnickname, mcbday, mcphno, mcemail, mcaddr,
					mcurl, mcnotes);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRBusiness ContactInput")
	public ModelAndView generateBusinessContactQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String vcprefix, @RequestParam String vcfname,
			@RequestParam String vcmname, @RequestParam String vclname, @RequestParam String vcsuffix,
			@RequestParam String vcnickname, @RequestParam String vcbday, @RequestParam String vcphnoper,
			@RequestParam String vcphnowork, @RequestParam String vcemailper, @RequestParam String vcemailwork,
			@RequestParam String vcaddrper, @RequestParam String vcaddrwork, @RequestParam String vcorg,
			@RequestParam String vctitle, @RequestParam String vcurlwork, @RequestParam String vcurlfb,
			@RequestParam String vcurltwitter, @RequestParam String vcurlinsta, @RequestParam String vcurllin,
			@RequestParam String vcurlghub, @RequestParam String vcnotes) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatvCardInput(vcprefix, vcfname, vcmname, vclname, vcsuffix, vcnickname,
					vcbday, vcphnoper, vcphnowork, vcemailper, vcemailwork, vcaddrper, vcaddrwork, vcorg, vctitle,
					vcurlwork, vcurlfb, vcurltwitter, vcurlinsta, vcurllin, vcurlghub, vcnotes);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRDial Phone NumberInput")
	public ModelAndView generateDialPhoneNumberQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String dialphno) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatDialPhoneNumberInput(dialphno);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);
	}

	@PostMapping("/ConvertToQRCalendar EventInput")
	public ModelAndView generateCalendarEventQRCodeAsBase64(@RequestParam String qrbg, @RequestParam String qrclr,
			@RequestParam String qrsize, @RequestParam String qrlgopt, @RequestParam String qrlgpos,
			@RequestParam String qrlgshape, @RequestParam String qrlgorientation, @RequestParam String qrlgtxt,
			@RequestParam String qrmargin, @RequestParam String qrposmarker, @RequestParam String qrposmarkercolor,
			@RequestParam String qrinnerposmarker, @RequestParam String qrinnerposmarkercolor,
			@RequestParam String qrposmarkertrcolor, @RequestParam String qrinnerposmarkertrcolor,
			@RequestParam String qrposmarkerblcolor, @RequestParam String qrinnerposmarkerblcolor,
			@RequestParam String qrbodytype, @RequestParam(value = "logoimg", required = false) MultipartFile custlogo,
			@RequestParam String qrinputtype, @RequestParam String eventsummary, @RequestParam String eventdesc,
			@RequestParam String eventsdate, @RequestParam String eventstime, @RequestParam String eventedate,
			@RequestParam String eventetime, @RequestParam String eventlocation) throws IOException {

		String qrInput;

		try {
			qrInput = handleqrtextinput.formatCalendarEventInput(eventsummary, eventdesc, eventsdate, eventstime,
					eventedate, eventetime, eventlocation);
		} catch (Exception e) {
			return qrTextInputError(e.getMessage());
		}

		return inputprocessing.initializeInputForConversion(qrInput, qrsize, qrmargin, qrclr, qrbg, qrposmarkercolor,
				qrinnerposmarkercolor, qrposmarkertrcolor, qrinnerposmarkertrcolor, qrposmarkerblcolor,
				qrinnerposmarkerblcolor, qrlgopt, qrlgpos, qrlgshape, qrlgorientation, custlogo, qrlgtxt, qrbodytype,
				qrposmarker, qrinnerposmarker);

	}

	public ModelAndView qrTextInputError(String errorInfo) {
		if (errorInfo.equalsIgnoreCase("Required Input Can't be Empty")) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("failureMessage", "Conversion Failed : " + errorInfo);
			modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
			return modelAndView;
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("failureMessage", "Conversion Failed : Input Error");
			modelAndView.setViewName("fragments/createqr/qrOutput :: conversionFailure");
			return modelAndView;
		}
	}
}