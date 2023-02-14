package kr.or.ddit2.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl  implements IOTPService{

	@Override
	public String generateSecretKey() {
		
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		Base32 base32 = new Base32();
		
		return base32.encodeToString(bytes);
	}

	@Override
	public String getGoogleAutenticatorBarCode(String secretKey, String account, String issuer) {
		 try {
		        return "otpauth://totp/"
		                + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
		                + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
		                + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
		    } catch (UnsupportedEncodingException e) {
		        throw new IllegalStateException(e);
		    }
	}



}
