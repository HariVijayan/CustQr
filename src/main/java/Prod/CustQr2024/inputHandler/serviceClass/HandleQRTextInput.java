package Prod.CustQr2024.inputHandler.serviceClass;

import org.springframework.stereotype.Component;

@Component
public class HandleQRTextInput {

	public String formatTextInput(String textInput) throws StringValidationException {
		if ((textInput.equalsIgnoreCase("NA")) || textInput == null || textInput.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		} else {
			return textInput;
		}
	}

	public String formatURLInput(String urlInput) throws StringValidationException {
		if ((urlInput.equalsIgnoreCase("NA")) || urlInput == null || urlInput.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		} else {
			return urlInput;
		}
	}

	public String formatWifiInput(String wifiName, String wifiEncryption, String wifiPassword)
			throws StringValidationException {
		if ((wifiName.equalsIgnoreCase("NA")) || wifiName == null || wifiName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		} else {
			if (wifiEncryption.equalsIgnoreCase("nopass")) {
				return "WIFI:T:nopass;S:" + wifiName + ";;\r\n";
			} else if (wifiEncryption.equalsIgnoreCase("wep")) {
				if ((wifiPassword.equalsIgnoreCase("NA")) || wifiPassword == null || wifiPassword.length() == 0) {
					throw new StringValidationException("Required Input Can't be Empty");
				}
				return "WIFI:T:WEP;S:" + wifiName + ";P:" + wifiPassword + ";;\r\n";
			} else if (wifiEncryption.equalsIgnoreCase("wpa")) {
				if ((wifiPassword.equalsIgnoreCase("NA")) || wifiPassword == null || wifiPassword.length() == 0) {
					throw new StringValidationException("Required Input Can't be Empty");
				}
				return "WIFI:T:WPA;S:" + wifiName + ";P:" + wifiPassword + ";;\r\n";
			} else if (wifiEncryption.equalsIgnoreCase("wpa2")) {
				if ((wifiPassword.equalsIgnoreCase("NA")) || wifiPassword == null || wifiPassword.length() == 0) {
					throw new StringValidationException("Required Input Can't be Empty");
				}
				return "WIFI:T:WPA2;S:" + wifiName + ";P:" + wifiPassword + ";;\r\n";
			} else {
				if ((wifiPassword.equalsIgnoreCase("NA")) || wifiPassword == null || wifiPassword.length() == 0) {
					throw new StringValidationException("Required Input Can't be Empty");
				}
				return "WIFI:T:WPA3;S:" + wifiName + ";P:" + wifiPassword + ";;\r\n";
			}
		}
	}

	public String formatEmailInput(String emailId, String emailSubject, String emailBody)
			throws StringValidationException {
		if ((emailId.equalsIgnoreCase("NA")) || emailId == null || emailId.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (emailSubject.equalsIgnoreCase("NA")) {
			emailSubject = "";
		}
		if (emailBody.equalsIgnoreCase("NA")) {
			emailBody = "";
		}

		return "mailto:" + emailId + "?subject=" + emailSubject + "&body=" + emailBody;
	}

	public String formatSMSInput(String smsPhoneNumber, String smsMessage) throws StringValidationException {
		if ((smsPhoneNumber.equalsIgnoreCase("NA")) || smsPhoneNumber == null || smsPhoneNumber.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (smsMessage.equalsIgnoreCase("NA")) {
			smsMessage = "";
		}

		return "SMSTO:" + smsPhoneNumber + ":" + smsMessage;
	}

	public String formatPlaystoreAppInput(String appId) throws StringValidationException {
		if ((appId.equalsIgnoreCase("NA")) || appId == null || appId.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		return "market://details?id=" + appId;
	}

	public String formatAppstoreAppInput(String appName, String appId) throws StringValidationException {
		if ((appName.equalsIgnoreCase("NA")) || appName == null || appName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((appId.equalsIgnoreCase("NA")) || appId == null || appId.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}

		return "itms-apps://itunes.apple.com/app/" + appName + "/id" + appId;
	}

	public String formatLocationInfoInput(String latitude, String longitude) throws StringValidationException {
		if ((latitude.equalsIgnoreCase("NA")) || latitude == null || latitude.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((longitude.equalsIgnoreCase("NA")) || longitude == null || longitude.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}

		return "geo:" + latitude + "," + longitude;
	}

	public String formatMeCardInput(String firstName, String lastName, String nickName, String birthday,
			String phoneNumber, String emailId, String address, String website, String notes)
			throws StringValidationException {
		if ((firstName.equalsIgnoreCase("NA")) || firstName == null || firstName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((lastName.equalsIgnoreCase("NA")) || lastName == null || lastName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (nickName.equalsIgnoreCase("NA")) {
			nickName = "";
		}
		if (birthday.equalsIgnoreCase("NA")) {
			birthday = "";
		}
		if ((phoneNumber.equalsIgnoreCase("NA")) || phoneNumber == null || phoneNumber.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (emailId.equalsIgnoreCase("NA")) {
			emailId = "";
		}
		String addressPersonalStreet, addressPersonalTown, addressPersonalState, addressPersonalPin,
				addressPersonalCountry = "";

		String[] addressPersonalArr;

		if (address.equalsIgnoreCase("NA")) {
			addressPersonalStreet = "";
			addressPersonalTown = "";
			addressPersonalState = "";
			addressPersonalPin = "";
			addressPersonalCountry = "";
		} else {
			addressPersonalArr = address.split(",");
			addressPersonalStreet = addressPersonalArr[0];
			addressPersonalTown = addressPersonalArr[1];
			addressPersonalState = addressPersonalArr[2];
			addressPersonalPin = addressPersonalArr[3];
			addressPersonalCountry = addressPersonalArr[4];
		}
		if (website.equalsIgnoreCase("NA")) {
			website = "";
		}
		if (notes.equalsIgnoreCase("NA")) {
			notes = "";
		}
		return "MECARD:N:" + lastName + "," + firstName + ";NICKNAME:" + nickName + ";BDAY:" + birthday + ";TEL:"
				+ phoneNumber + ";EMAIL:" + emailId + "ADR;TYPE=HOME:;;" + addressPersonalStreet + ";"
				+ addressPersonalTown + ";" + addressPersonalState + ";" + addressPersonalPin + ";"
				+ addressPersonalCountry + "\r\n" + ";URL:" + website + ";NOTE:" + notes;
	}

	public String formatvCardInput(String prefix, String firstName, String middleName, String lastName, String suffix,
			String nickName, String birthday, String phoneNumberPersonal, String phoneNumberWork,
			String emailIdPersonal, String emailIdWork, String addressPersonal, String addressWork, String organization,
			String title, String websiteWork, String websiteFacebook, String websiteTwitter, String websiteInsta,
			String websiteLinkedIn, String websiteGitHub, String notes) throws StringValidationException {
		if (prefix.equalsIgnoreCase("NA")) {
			prefix = "";
		}
		if ((firstName.equalsIgnoreCase("NA")) || firstName == null || firstName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (middleName.equalsIgnoreCase("NA")) {
			middleName = "";
		}
		if ((lastName.equalsIgnoreCase("NA")) || lastName == null || lastName.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (suffix.equalsIgnoreCase("NA")) {
			suffix = "";
		}
		if (nickName.equalsIgnoreCase("NA")) {
			nickName = "";
		}
		if (birthday.equalsIgnoreCase("NA")) {
			birthday = "";
		}
		if ((phoneNumberPersonal.equalsIgnoreCase("NA")) || phoneNumberPersonal == null
				|| phoneNumberPersonal.length() == 0) {
			throw new StringValidationException("");
		}
		if (phoneNumberWork.equalsIgnoreCase("NA")) {
			phoneNumberWork = "";
		}
		if (emailIdPersonal.equalsIgnoreCase("NA")) {
			emailIdPersonal = "";
		}
		if (emailIdWork.equalsIgnoreCase("NA")) {
			emailIdWork = "";
		}

		String addressPersonalStreet, addressPersonalTown, addressPersonalState, addressPersonalPin,
				addressPersonalCountry = "";

		String[] addressPersonalArr;

		if (addressPersonal.equalsIgnoreCase("NA")) {
			addressPersonalStreet = "";
			addressPersonalTown = "";
			addressPersonalState = "";
			addressPersonalPin = "";
			addressPersonalCountry = "";
		} else {
			addressPersonalArr = addressPersonal.split(",");
			addressPersonalStreet = addressPersonalArr[0];
			addressPersonalTown = addressPersonalArr[1];
			addressPersonalState = addressPersonalArr[2];
			addressPersonalPin = addressPersonalArr[3];
			addressPersonalCountry = addressPersonalArr[4];
		}

		String addressWorkStreet, addressWorkTown, addressWorkState, addressWorkPin, addressWorkCountry = "";

		String[] addressWorkArr;

		if (addressWork.equalsIgnoreCase("NA")) {
			addressWorkStreet = "";
			addressWorkTown = "";
			addressWorkState = "";
			addressWorkPin = "";
			addressWorkCountry = "";
		} else {
			addressWorkArr = addressWork.split(",");
			addressWorkStreet = addressWorkArr[0];
			addressWorkTown = addressWorkArr[1];
			addressWorkState = addressWorkArr[2];
			addressWorkPin = addressWorkArr[3];
			addressWorkCountry = addressWorkArr[4];
		}

		if (organization.equalsIgnoreCase("NA")) {
			organization = "";
		}
		if (title.equalsIgnoreCase("NA")) {
			title = "";
		}
		if (websiteWork.equalsIgnoreCase("NA")) {
			websiteWork = "";
		}
		if (websiteFacebook.equalsIgnoreCase("NA")) {
			websiteFacebook = "";
		}
		if (websiteTwitter.equalsIgnoreCase("NA")) {
			websiteTwitter = "";
		}
		if (websiteInsta.equalsIgnoreCase("NA")) {
			websiteInsta = "";
		}
		if (websiteLinkedIn.equalsIgnoreCase("NA")) {
			websiteLinkedIn = "";
		}
		if (websiteGitHub.equalsIgnoreCase("NA")) {
			websiteGitHub = "";
		}
		if (notes.equalsIgnoreCase("NA")) {
			notes = "";
		}
		return "BEGIN:VCARD\r\n" + "VERSION:4.0\r\n" + "FN:" + firstName + " " + middleName + " " + lastName + "\r\n"
				+ "N:" + lastName + ";" + firstName + ";" + middleName + ";" + prefix + ";" + suffix + "\r\n"
				+ "NICKNAME:" + nickName + "\r\n" + "BDAY:" + birthday + "\r\n" + "TEL;TYPE=HOME,VOICE:"
				+ phoneNumberPersonal + "\r\n" + "TEL;TYPE=WORK,VOICE:" + phoneNumberWork + "\r\n" + "EMAIL;TYPE=HOME:"
				+ emailIdPersonal + "\r\n" + "EMAIL;TYPE=WORK:" + emailIdWork + "\r\n" + "ADR;TYPE=HOME:;;"
				+ addressPersonalStreet + ";" + addressPersonalTown + ";" + addressPersonalState + ";"
				+ addressPersonalPin + ";" + addressPersonalCountry + "\r\n" + "ADR;TYPE=WORK:;;" + addressWorkStreet
				+ ";" + addressWorkTown + ";" + addressWorkState + ";" + addressWorkPin + ";" + addressWorkCountry
				+ "\r\n" + "ORG:" + organization + "\r\n" + "TITLE:" + title + "\r\n" + "URL;TYPE=Personal:"
				+ websiteWork + "\r\n" + "URL;TYPE=Facebook:" + websiteFacebook + "\r\n" + "URL;TYPE=Twitter:"
				+ websiteTwitter + "\r\n" + "URL;TYPE=Instagram:" + websiteInsta + "\r\n" + "URL;TYPE=LinkedIn:"
				+ websiteLinkedIn + "\r\n" + "URL;TYPE=GitHub:" + websiteGitHub + "\r\n" + "NOTE:" + notes + "\r\n"
				+ "END:VCARD\r\n";
	}

	public String formatDialPhoneNumberInput(String dialPhoneNumber) throws StringValidationException {
		if ((dialPhoneNumber.equalsIgnoreCase("NA")) || dialPhoneNumber == null || dialPhoneNumber.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		return "tel:" + dialPhoneNumber;
	}

	public String formatCalendarEventInput(String eventSummary, String eventDescription, String eventStartDate,
			String eventStartTime, String eventEndDate, String eventEndTime, String eventLocation)
			throws StringValidationException {
		if ((eventSummary.equalsIgnoreCase("NA")) || eventSummary == null || eventSummary.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (eventDescription.equalsIgnoreCase("NA")) {
			eventDescription = "";
		}
		if ((eventStartDate.equalsIgnoreCase("NA")) || eventStartDate == null || eventStartDate.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((eventStartTime.equalsIgnoreCase("NA")) || eventStartTime == null || eventStartTime.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((eventEndDate.equalsIgnoreCase("NA")) || eventEndDate == null || eventEndDate.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if ((eventEndTime.equalsIgnoreCase("NA")) || eventEndTime == null || eventEndTime.length() == 0) {
			throw new StringValidationException("Required Input Can't be Empty");
		}
		if (eventLocation.equalsIgnoreCase("NA")) {
			eventLocation = "";
		}
		eventStartDate = eventStartDate.replace("-", "");
		eventEndDate = eventEndDate.replace("-", "");
		return "BEGIN:VCALENDAR\r\n" + "VERSION:2.0\r\n" + "BEGIN:VEVENT\r\n" + "SUMMARY:" + eventSummary + "\r\n"
				+ "DTSTART:" + eventStartDate + "T" + eventStartTime + "\r\n" + "DTEND:" + eventEndDate + "T"
				+ eventEndTime + "\r\n" + "LOCATION:" + eventLocation + "\r\n" + "DESCRIPTION:" + eventDescription
				+ "\r\n" + "END:VEVENT\r\n" + "END:VCALENDAR\r\n";
	}
}

class StringValidationException extends Exception {
	private static final long serialVersionUID = 1L;

	public StringValidationException(String message) {
		super(message);
	}
}
