package Prod.CustQr2024.sessionToken;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class SessionTokenGenerator {

	String secretKey = "jbdsbjhdsbjdsbj-jhdfgbhbhbfjhfdsbjh-hgefvgjdfvdfvjhdsbj";
	private final SecureRandom secureRandom = new SecureRandom();

	public void generateKey() {
		byte[] randomBytes = new byte[64]; 
		secureRandom.nextBytes(randomBytes);

		secretKey = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	}

	public String getSessionToken() {
		return secretKey;
	}

}
