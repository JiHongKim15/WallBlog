package com.web.blog.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface EmailService {
	public String sendSimpleMessage(String to, String subject, String text) throws MessagingException;
}
