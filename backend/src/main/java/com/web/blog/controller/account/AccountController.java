package com.web.blog.controller.account;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.blog.dao.user.UserSignup;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.EmailCheck;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.Users;
import com.web.blog.service.EmailService;
import com.web.blog.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {
	final private String profileImgPath = "/backend/src/main/resources/profile/";

	@Autowired
	UserSignup userSignup;

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;


	@PostMapping("/login")
	public Object login(@Valid @RequestBody Users loginData, HttpServletRequest req) {

		Users result = userService.login(loginData);
		if (result == null) {
			return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
		}

		HttpSession session = req.getSession();
		session.setAttribute("uid", result.getUid());
		return new ResponseEntity<String>(result.getUid(), HttpStatus.OK);
	}

//	Get 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@Autowired
	JavaMailSender sender;

	// 회원가입
	@PostMapping("/user")
	@ApiOperation(value = "회원가입")
	public Object signup(
			@Valid @RequestBody SignupRequest request/* , @RequestParam("profileImg")MultipartFile profileImg */) {

		// ======== 프로필 이미지 관련 추가 코드 ========
//        if (profileImg != null) {
//            String fileName = saveImg(profileImg, profileImgPath);
//            if (fileName.isEmpty()) // 새로 생성된 이미지 이름이 없는 경우 = 에러 발생!
//                return new ResponseEntity<>("fail", HttpStatus.CONFLICT);
//            request.setImage(fileName);
//        }
		// ======== 프로필 이미지 관련 추가 코드 ========
		if(userSignup.emailselect(request.getEmail()) == null) {
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
		}
		EmailCheck ec = userSignup.emailselect(request.getEmail());
		if (ec.getConfirm() == 1) {
			if(userSignup.selectUid(request.getUid()) != null) {
				return new ResponseEntity<>("false", HttpStatus.FAILED_DEPENDENCY);
			}
			if(userSignup.selectEmail(request.getEmail()) != null) {
				return new ResponseEntity<>("false", HttpStatus.CONFLICT);
			}
			if (userSignup.insert(request)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("false", HttpStatus.CONFLICT);
			}
		}
		else
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
	}

	@PostMapping("/user/emailsend")
	@ApiOperation(value = "이메일보내기")
	public Object emailCheck(@Valid @RequestBody String to) {
		String[] a = to.split(":");
		a = a[1].split("\"");
		to = a[1];
		String html = "http://i3c201.p.ssafy.io:8081/api/user/emailOk";
		
		if(userSignup.emailselect(a[1]) != null && userSignup.emailselect(a[1]).getConfirm() == 1) {
			return new ResponseEntity<>("false", HttpStatus.CONFLICT);
		}
		try {
			if(userSignup.emailselect(a[1]) != null) {
				userSignup.emaildelete(a[1]);
			}
			emailService.sendSimpleMessage(a[1], "[이메일인증] SSAFY ESCAPE NO.1 인증 링크", html);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (MessagingException e) {
			e.printStackTrace();
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/emailOk")
	@ApiOperation(value = "이메일확인링크")
	public Object emailsend(String email, String code) {
		EmailCheck emailcheck = userSignup.emailselect(email);
		if (emailcheck.getCode().equals(code)) {
			userSignup.emailupdate(new EmailCheck(emailcheck.getEmail(), emailcheck.getCode(), 1));
			
			return new ResponseEntity<>("이메일 인증이 완료되었습니다.", HttpStatus.OK);
		} else
			return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/user/uid")
	@ApiOperation(value = "유저id -> 객체 반환")
	public Object useruid(@RequestParam(value = "uid") String uid) {
		SignupRequest user = userSignup.selectUid(uid);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/user/searchPw")
	public Object sendEmailAction(@Valid @RequestBody String email)
			throws Exception {
		
		String[] a = email.split(":");
		a = a[1].split("\"");
		email = a[1];
		
		SignupRequest user = userSignup.selectEmail(email);
		if(user == null) {
			return new ResponseEntity<>("false", HttpStatus.I_AM_A_TEAPOT);
		}
		try {
			MimeMessage msg = sender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(msg, true, "UTF-8");

			messageHelper.setSubject(user.getUid() + "님 SSAFY ESCAPE No.1 비밀번호 찾기 메일입니다.");
			messageHelper.setText("비밀번호는 " + user.getPassword() + "입니다.\n감사합니다.");
			messageHelper.setTo(user.getEmail());
			msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			sender.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	// 회원탈퇴
	@DeleteMapping("/user")
	@ApiOperation(value = "회원탈퇴")
	public Object dropout(@RequestBody String uid) {
		if (userSignup.delete(uid))
			return new ResponseEntity<>("success", HttpStatus.OK);
		else
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 회원수정
	@PostMapping("/user/update")
	@ApiOperation(value = "회원수정 정보 제공")
	public Object updateInfo(@Valid @RequestBody String uid) {
		SignupRequest user = userSignup.selectUid(uid);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// 회원수정
	@PutMapping("/user")
	@ApiOperation(value = "회원수정")
	public Object update(@Valid @RequestBody SignupRequest request) {

		System.out.println(request.getImage());
		SignupRequest ifin = userSignup.selectUid(request.getUid());
		
		if (ifin == null) {
			return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
		} else if (userSignup.update(request)) {
			userSignup.uploadImg(request);
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("false", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}