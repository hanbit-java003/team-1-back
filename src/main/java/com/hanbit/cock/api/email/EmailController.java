package com.hanbit.cock.api.email;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hanbit.cock.api.vo.MemberVO;

@RestController
@RequestMapping("/api")
public class EmailController {


	// Email 인증 추가.
	@RequestMapping("/email")
	public Map emailAuth(@RequestParam("email") String email,
			HttpServletResponse response, HttpServletRequest request)throws Exception {
		
		String authNum;
		
		MemberVO memberVO = new MemberVO();
		
		email = request.getParameter("email");
		
		memberVO.setEmail(email);
		
		authNum  = RandomNum();

		sendEmail(email.toString(), authNum);

		Map map = new HashMap();
		map.put("email",email);
		map.put("authNum",authNum);

		return map;
	}


	// sendEmail 메소드
	private void sendEmail(String email, String authNum) {
		String host = "smtp.gmail.com"; // smtp 서버
		String subject = "CockCock 인증번호 전달";
		String fromName= "CockCock 관리자";
		String from = "김콕콕@cockcock.com"; // 보내는 메일
		String to1 = email;

		String content = "인증번호[" + authNum + "]";

		try{
			Properties props = new Properties();
			// G-Mail SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLCocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");

			/*javax.mail.Session  mailSession = (javax.mail.Session) session.getInstance(props,
					new javax.mail.Authenticator() {
				protected javax.mail.PasswordAuthentication getpasswordAutentication(){
					return new javax.mail.PasswordAuthentication("jmkjmk7410@gmail.com", "ansrl1042");
				}
			});*/
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("jmkjmk7410@gmail.com", "ansrl1042");
				}
			};
			
			Session session = Session.getDefaultInstance(props,auth);
			

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B")));// 보내는 사람 설정
			InternetAddress[] address1 = {new InternetAddress(to1)};
			msg.setRecipients(Message.RecipientType.TO, address1); // 받는 사람 설정1
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html;charset=euc-kr"); // 내용설정 (HTML // 형식)

			Transport.send(msg); // 메일 보내기

		}catch (MessagingException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}




	// 난수 발생시키는 메소드
	private String RandomNum() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i <= 6; i++) {
			int n = (int) (Math.random() * 10);
			buffer.append(n);
		}
		return buffer.toString();
	}
}
