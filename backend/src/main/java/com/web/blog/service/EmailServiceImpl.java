package com.web.blog.service;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.web.blog.dao.user.UserSignup;
import com.web.blog.model.user.EmailCheck;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private UserSignup userSignup;
	
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;

		do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}

		} while (sb.length() < size);
		if (lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}

	// 난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;

	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}

	@Override
	public String sendSimpleMessage(String to, String subject, String text) throws MessagingException {
		String code = getKey(false, 20);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText("안녕하세요 SSAFY ESCAPE NO.1 입니다. 해당 링크를 클릭하시면 인증이 완료됩니다.\n" + text + "?email=" + to + "&code=" + code);
		emailSender.send(message);
		
		Timer m_timer = new Timer();
		TimerTask m_task = new TimerTask() {
			
			@Override
			public void run() {
				if(userSignup.emailselect(to) != null && userSignup.emailselect(to).getConfirm() != 1)
					userSignup.emaildelete(to);
			}
		};
		
		//30분안에 인증 안하면 인증 삭제
		m_timer.schedule(m_task, 1800000);
		userSignup.emailinsert(new EmailCheck(to, code, 0));
		
		return code;
	}

	
	
}