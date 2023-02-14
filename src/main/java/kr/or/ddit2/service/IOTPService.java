package kr.or.ddit2.service;


public interface IOTPService {
	String generateSecretKey();
	String getGoogleAutenticatorBarCode(String secretKey, String account, String issuer);

}
