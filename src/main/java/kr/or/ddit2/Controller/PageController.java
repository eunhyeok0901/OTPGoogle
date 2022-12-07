package kr.or.ddit2.Controller;

import java.security.SecureRandom;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.taimos.totp.TOTP;
import kr.or.ddit2.service.IOTPService;

@Controller
@RequestMapping("/otp")
public class PageController {

	@Inject
	private IOTPService service;

	private static Logger logger = LoggerFactory.getLogger(PageController.class);
	
	final String user_id = "admin";
	final String user_pw = "1234";
	
	@GetMapping("/form")
	public String loginForm() {
		return "firstLogin";
	}
	
	
	@PostMapping("/login.do")
	public String login(String id, String pw, ModelMap model) {
		logger.info("login");
		logger.info("id = " + id);
		logger.info("pw = " + pw);
		
		String goPage = "";
		
		//만약에 1차 로그인 성공 하면 2차 인증 서비스 가기
		if(id.equals(user_id) && pw.equals(user_pw)) {
			//인증키 생성  
			String secretKey = service.generateSecretKey();
			logger.info("secretKey :" + secretKey); // QVJPHOBZAP4SDONM3OGZ5TFDXJTIVKBZ
			
			String hexKey = getTOTPCode(secretKey); 
			logger.info("hexKey :" + hexKey);  //883530
			
			//google Authenicator 사용
			String lastCode = null;
			while(true) {
				String code = getTOTPCode(secretKey);
				if(!code.equals(lastCode)) {
					System.out.println(code);
				}
				lastCode = code;
				try {
					Thread.sleep(1000);
				}catch (Exception e) {
					
				}
			}
			
			
			
		}else {  // 1차 인증 실패시 다시 login 가기
			goPage = "firstLogin";
			model.addAttribute("msg", "로그인 실패");
		}
		
		return goPage;
	}


	private static String getTOTPCode(String secretKey) {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey);
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}
}
