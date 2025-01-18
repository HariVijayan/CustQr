package Prod.CustQr2024.controller.serviceClass;

public class CreateQrEndPoints {

	public String[] getCreateQrEndPoints(String viewingMode) {
		String[] serverEndpoints = new String[0];
		if (viewingMode.equalsIgnoreCase("Dark")) {
			serverEndpoints[0] = "/getBodyDarkMode";
		} else {
			serverEndpoints[0] = "/getBodyLightMode";
		}
		return serverEndpoints;
	}
}
