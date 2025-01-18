package Prod.CustQr2024.inputHandler.serviceClass;

import org.springframework.stereotype.Component;

@Component
public class HandleSizeInput {

	public int parseQrCodeSize(String qrCodeSize) {
		if ("NA".equalsIgnoreCase(qrCodeSize)) {
			return 500;
		}
		int size = Integer.parseInt(qrCodeSize);
		return Math.max(250, Math.min(size, 5000));
	}

	public int parseQrCodeMargin(String qrMargin) {
		if ("NA".equalsIgnoreCase(qrMargin)) {
			return 10;
		}
		int margin = Integer.parseInt(qrMargin);
		margin = Math.max(5, Math.min(margin, 20));
		if (margin % 5 == 0) {
			return margin;
		} else {
			int lower = margin - (margin % 5);
			int upper = lower + 5;
			return (margin - lower < upper - margin) ? lower : upper;
		}
	}
}
